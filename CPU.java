import java.util.Random;

public class CPU {

	private static ArrayOfPCB ArrayOfPCB;
	private PriorityQueue JobQueue;
	private PriorityQueue ReadyQueue;
	private Queue RunQueue;
	private Queue WaitingQueue;
	private Queue FinishQueue;

	// if all flags is false that mean there is no process in the RAM or CPU
	private boolean ReadyQueueFlag;
	private boolean RunQueueFlag;
	private boolean WaitingQueueFlag;
	private boolean ArrayOfPCBFlag;

	int clock;

	public CPU(ArrayOfPCB ArrayOfPCB) {
		// -1 that means it has no size or limit
		JobQueue = new PriorityQueue(-1);
		// 132 Megabytes = 135168 Kilobytes
		ReadyQueue = new PriorityQueue(135168, ArrayOfPCB);
		WaitingQueue = new Queue();
		RunQueue = new Queue();
		FinishQueue = new Queue();
		ReadyQueueFlag = false;
		RunQueueFlag = false;
		WaitingQueueFlag = false;
		clock = 0;
		this.ArrayOfPCB = ArrayOfPCB;
	}

	public void fillJobQueue() {
		while (ArrayOfPCB.list.length() > 0 ) {
			PCB p = ArrayOfPCB.list.serve();
			
			// here i use "MemorySize" as priority
			JobQueue.enqueue(p, Integer.parseInt(p.getMemorySize()));	
		}

	}
	
	public void fillReadyQueue() {
		boolean readyQueueFull = false;
		while (JobQueue.length() > 0 && !readyQueueFull) {
			PCB p = JobQueue.serve();
			
			if( (Integer.parseInt(p.getMemorySize())+ReadyQueue.getSizeSum()) > ReadyQueue.getMaxMemorySize()){
				JobQueue.enqueue(p, Integer.parseInt(p.getMemorySize()));
				readyQueueFull = true;
			}
			else{
				p.setState("ready");
				// here i use "CPU Remaining Time" as priority
				ReadyQueue.enqueue(p, Integer.parseInt(p.getRemainingTime()));
				ReadyQueueFlag = true;
				
			}
			
		}

	}

	public void run() {
		ArrayOfPCB.run();
		fillJobQueue();
		fillReadyQueue();

		
		// here i will keep running until all the queues become empty
		while (ReadyQueueFlag || RunQueueFlag || WaitingQueueFlag || ArrayOfPCBFlag ) {

			
			
			// RunningQueue handling ///////////////////////////////////////////
			while (ReadyQueue.length() > 0 && RunQueue.length() == 0) {
				clock = 0;
				
				PCB ReadyProcess = ReadyQueue.serve();
				ReadyProcess.setState("running");
				fillReadyQueue();

				
				
				RunQueue.enqueue(ReadyProcess);

				PCB RunProcess = RunQueue.serve();

				while (RunProcess.getState().equals("running")) {
					clock++;
					if (clock % 50 == 0) {

						// here i will put probabilities

						int val = new Random().nextInt(100);

						// The possibility that there are interrupts is 10%
						if (val >= 0 && val < 10) {
							RunProcess.setState("ready");
							String newRemainingTime = Integer.parseInt(RunProcess.getCPUTime()) - Integer.parseInt(RunProcess.getRunningTime()) +"";
							RunProcess.setRemainingTime(newRemainingTime);
							RunProcess.setTerminatedNormally(false);
						}

						// The possibility that there is an IO request is 20%
						else if (val >= 10 && val < 30) {
							RunProcess.setState("waiting");
							String newRemainingTime = Integer.parseInt(RunProcess.getCPUTime()) - Integer.parseInt(RunProcess.getRunningTime()) +"";
							RunProcess.setRemainingTime(newRemainingTime);
							RunProcess.setTerminatedNormally(false);
						}

						// The possibility that the program terminates normally is 5%
						else if (val >= 50 && val < 55) {
							RunProcess.setState("termnated");
							RunProcess.setRunningTime(clock + "");
							String newRemainingTime = Integer.parseInt(RunProcess.getCPUTime()) - Integer.parseInt(RunProcess.getRunningTime()) +"";
							RunProcess.setRemainingTime(newRemainingTime);
							RunProcess.setTerminatedNormally(true);
						}

						// The possibility that the program terminates abnormally is 1%
						else if (val >= 55 && val < 56) {
							RunProcess.setState("termnated");
							RunProcess.setRunningTime(clock + "");
							String newRemainingTime = Integer.parseInt(RunProcess.getCPUTime()) - Integer.parseInt(RunProcess.getRunningTime()) +"";
							RunProcess.setRemainingTime(newRemainingTime);
							RunProcess.setTerminatedNormally(false);
						}

						// ...

					}
					
					if (RunProcess.getState().equals("running")) {
						int newTime = Integer.parseInt(RunProcess.getRunningTime()) + 1;

						// here i will increment the the running time
						RunProcess.setRunningTime(newTime + "");
						// here i will calculate the the remaining time
						String newRemainingTime = Integer.parseInt(RunProcess.getCPUTime()) - Integer.parseInt(RunProcess.getRunningTime()) +"";
						RunProcess.setRemainingTime(newRemainingTime);
						
						// here i will check if the time of the process is finish ?
						if (Integer.parseInt(RunProcess.getRunningTime()) >= Integer.parseInt(RunProcess.getCPUTime())){
							RunProcess.setState("termnated");
							RunProcess.setTerminatedNormally(true);
						}
					}

					
					// here i will check the state of the process then i will transfer it to the appropriate queue
					
					if (RunProcess.getState().equals("waiting")) {
						WaitingQueue.enqueue(RunProcess);
					}

					if (RunProcess.getState().equals("ready")) {
						ReadyQueue.enqueue(RunProcess , Integer.parseInt(RunProcess.getMemorySize()));
					}

					if (RunProcess.getState().equals("termnated")) {
						FinishQueue.enqueue(RunProcess);
					}
				}
				
				// ((END)) RunningQueue handling
			}

			// here i will check if the Queues is empty or not
			if (RunQueue.length() > 0)
				RunQueueFlag = true;
			else
				RunQueueFlag = false;

			if (WaitingQueue.length() > 0)
				WaitingQueueFlag = true;
			else
				WaitingQueueFlag = false;

			if (ReadyQueue.length() > 0)
				ReadyQueueFlag = true;
			else
				ReadyQueueFlag = false;
			
			if (ArrayOfPCB.list.length() > 0)
				ArrayOfPCBFlag = true;
			else
				ArrayOfPCBFlag = false;
			
			
			

			// WaitingQueue handling ////////////////////////////
			while (WaitingQueue.length() > 0) {
				clock = 0;

				PCB WaitingProcess = WaitingQueue.serve();

					// here i will generate random time for the IO
					Random rand = new Random();
					int max = 200;
					int min = 100;
					int ioTime = rand.nextInt((max - min) + 1) + min;

					while (clock < ioTime && WaitingProcess.getState().equals("waiting")) {
						
						if(clock % 50 == 0){
						// The possibility that the busy IO device will terminate is 20%
							int val = new Random().nextInt(100);
							if(val >= 30 && val < 50 ){
								WaitingProcess.setState("termnated");
							}

						}
						clock++;
					}
					if(WaitingProcess.getState().equals("waiting")){
						
						WaitingProcess.setState("ready");
						int newWaitingTime = Integer.parseInt(WaitingProcess.getWaitingTime())+ clock;
						WaitingProcess.setWaitingTime(newWaitingTime + "");
						
						ReadyQueue.enqueue(WaitingProcess, Integer.parseInt(WaitingProcess.getRemainingTime()));
					}
					else
						if(WaitingProcess.getState().equals("termnated")){
							WaitingProcess.setWaitingTime(clock +"");
							FinishQueue.enqueue(WaitingProcess);
						}
						


				// ((END)) WaitingQueue handling
			}

			// here i will check if the Queues is empty or not
			if (RunQueue.length() > 0)
				RunQueueFlag = true;
			else
				RunQueueFlag = false;

			if (WaitingQueue.length() > 0)
				WaitingQueueFlag = true;
			else
				WaitingQueueFlag = false;

			if (ReadyQueue.length() > 0)
				ReadyQueueFlag = true;
			else
				ReadyQueueFlag = false;
			
			if (ArrayOfPCB.list.length() > 0)
				ArrayOfPCBFlag = true;
			else
				ArrayOfPCBFlag = false;

		}
	}
	
	
	
	
	
	
	public static ArrayOfPCB getArrayOfPCB() {
		return ArrayOfPCB;
	}

	public static void setArrayOfPCB(ArrayOfPCB arrayOfPCB) {
		ArrayOfPCB = arrayOfPCB;
	}

	public Queue getFinishQueue() {
		return FinishQueue;
	}

	public PriorityQueue getReadyQueue() {
		return ReadyQueue;
	}

	public Queue getRunQueue() {
		return RunQueue;
	}

	public Queue getWaitingQueue() {
		return WaitingQueue;
	}


	
	
	
	
	
}