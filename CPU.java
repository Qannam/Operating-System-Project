import java.util.Random;

public class CPU {

	private static ArrayOfPCB ArrayOfPCB;
	
	private PriorityQueue ReadyQueue;
	private PriorityQueue RunQueue;
	private Queue WaitingQueue;
	private Queue FinishQueue;

	private boolean ReadyQueueFlag;
	// if all flags is false that mean there is no process in the RAM or CPU
	private boolean RunQueueFlag;
	private boolean WaitingQueueFlag;

	int clock;

	public CPU(ArrayOfPCB ArrayOfPCB) {
		// 132 Megabytes = 135168 Kilobytes
		ReadyQueue = new PriorityQueue(135168, ArrayOfPCB);
		// -1 that means it has no size or limit
		WaitingQueue = new Queue();
		RunQueue = new PriorityQueue(-1);
		FinishQueue = new Queue();
		ReadyQueueFlag = false;
		RunQueueFlag = false;
		WaitingQueueFlag = false;
		clock = 0;
		this.ArrayOfPCB = ArrayOfPCB;
	}

	public void fillReadyQueue() {
		boolean readyQueueFull = false;
		while (ArrayOfPCB.list.length() > 0 && !readyQueueFull) {
			PCB p = ArrayOfPCB.list.serve();
			
			if( (Integer.parseInt(p.getMemorySize())+ReadyQueue.getSizeSum()) > ReadyQueue.getMaxMemorySize()){
				ArrayOfPCB.list.enqueue(p);
				
				
				readyQueueFull = true;
			}
			else{
				p.setState("ready");
				// here i use "MemorySize" as priority
				ReadyQueue.enqueue(p, Integer.parseInt(p.getMemorySize()));
				ReadyQueueFlag = true;
				
			}
			
		}
		System.out.println("/////Ready length :"+ReadyQueue.length());    //test//

	}

	public void run() {
		ArrayOfPCB.run();
		System.out.println("PCB Array length :"+ArrayOfPCB.list.length());    //test//
		System.out.println();													//test//
		System.out.println();													//test//
		System.out.println();													//test//
		
		fillReadyQueue();

		
		// here i will keep running until all the queues become empty
		while (ReadyQueueFlag || RunQueueFlag || WaitingQueueFlag) {

			
			
			// RunningQueue handling ///////////////////////////////////////////
			while (ReadyQueue.length() > 0 && RunQueue.length() == 0) {
				clock = 0;

				PCB ReadyProcess = ReadyQueue.serve();
				fillReadyQueue();

				ReadyProcess.setState("running");
				
				// here i use "CPUTime" as priority
				RunQueue.enqueue(ReadyProcess, Integer.parseInt(ReadyProcess.getCPUTime()));

				PCB RunProcess = RunQueue.serve();

				while (RunProcess.getState().equals("running")) {
					clock++;
					if (clock % 50 == 0) {

						// here i will put probabilities

						int val = new Random().nextInt(100);

						// The possibility that there are interrupts is 10%
						if (val >= 0 && val < 10) {
							RunProcess.setState("ready");
						}

						// The possibility that there is an IO request is 20%
						else if (val >= 10 && val < 30) {
							RunProcess.setState("waiting");
						}

						// // The possibility that the busy IO device will
						// terminate is 20%
						// else if(val >= 30 && val < 50 ){
						// RunProcess.setState("termnated");
						// }

						// The possibility that the program terminates normally
						// is 5%
						else if (val >= 50 && val < 55) {
							RunProcess.setState("termnated");
							RunProcess.setRunningTime(clock + "");
						}

						// The possibility that the program terminates
						// abnormally is 1%
						else if (val >= 55 && val < 56) {
							RunProcess.setState("termnated");
							RunProcess.setRunningTime(clock + "");
						}

						// ...

					}
					
					if (RunProcess.getState().equals("running")) {
						int newTime = Integer.parseInt(RunProcess.getCPUTime()) + 1;
						// here i will increment the the running time
						RunProcess.setRunningTime(newTime + "");
						// here i will check if the time of the process
						// is finish ?
						if (Integer.parseInt(RunProcess.getRunningTime()) >= Integer.parseInt(RunProcess.getCPUTime()))
							RunProcess.setState("termnated");
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

				// /*
				// * if the state of the process is not "waiting" i will tray to
				// add it to the ready queue
				// * if i cannot because the ready queue is full i will break
				// the loop
				// */
				// if (!ReadyQueue.enqueue(WaitingProcess,
				// Integer.parseInt(WaitingProcess.getMemorySize()))) {
				// WaitingQueue.enqueue(WaitingProcess);
				// break;
				// }

				
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

			// WaitingQueue handling ////////////////////////////
			while (WaitingQueue.length() > 0) {
				clock = 0;

				PCB WaitingProcess = WaitingQueue.serve();

				/*
				 * here i am checking because may the state of the process is
				 * not "waiting" when i can not add it into ready queue because
				 * the ready queue is full
				 */
				if (WaitingProcess.getState().equals("waiting")) {

					// here i will generate random time for the IO
					Random rand = new Random();
					int max = 200;
					int min = 100;
					int ioTime = rand.nextInt((max - min) + 1) + max;

					while (clock < ioTime) {
						clock++;
					}

					WaitingProcess.setState("ready");
					WaitingQueue.enqueue(WaitingProcess);

					// /* if the ready queue is full i will return the process
					// to
					// * the waiting queue and i will break the loob
					// */
					// if (!ReadyQueue.enqueue(WaitingProcess,
					// Integer.parseInt(WaitingProcess.getMemorySize()))) {
					// WaitingQueue.enqueue(WaitingProcess);
					// break;
					// }

				}

				// /*
				// * if the state of the process is not "waiting" i will tray to
				// add it to the ready queue
				// * if i cannot because the ready queue is full i will break
				// the loop
				// */
				// else if (!ReadyQueue.enqueue(WaitingProcess,
				// Integer.parseInt(WaitingProcess.getMemorySize()))) {
				// WaitingQueue.enqueue(WaitingProcess);
				// break;
				// }

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

	public PriorityQueue getRunQueue() {
		return RunQueue;
	}

	public Queue getWaitingQueue() {
		return WaitingQueue;
	}


	
	
	
	
	
}
