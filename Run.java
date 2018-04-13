import java.util.Random;

public class Run {

	public static void main(String[] args) {
//		DataGeneration d = new  DataGeneration();
//		ArrayOfPCB a = new ArrayOfPCB();
//		d.generate();
//		
//		
//		a.setPCBArray();
//		for (int i = 0; i < a.index; i++) {
//			System.out.println(a.list[i].getID());
//			System.out.println(a.list[i].getState());
//			System.out.println(a.list[i].getCPUTime());
//			System.out.println(a.list[i].getMemoryTime());
//			
//			System.out.println();
//			System.out.println();
//
//		}
			
//		long clock = System.currentTimeMillis();
//		for(int i = 0 ; i < 1000 ; i++){
//			long time = System.currentTimeMillis();
//            long workTime = time - clock;
//            clock = time;
//            
//            System.out.println(workTime);
//		}
//		int countr = 1;
//		for(int i = 0 ; i < 100 ; i++){
//			if(new Random().nextInt(100) < 25)	{
//				System.out.println(countr++);
//			}
//				
//		}
		
		
		ArrayOfPCB ArrayOfPCB = new ArrayOfPCB("/Users/Qannam/Documents/workspace/OS_project/test.txt");
		CPU CPU = new CPU(ArrayOfPCB);
		CPU.run();
		
	
		
			System.out.println("FinishQueue length: "+CPU.getFinishQueue().length());
			System.out.println("WaitingQueue length: "+CPU.getWaitingQueue().length());
			System.out.println("RunQueue length: "+CPU.getRunQueue().length());
			System.out.println("ReadyQueue length: "+CPU.getReadyQueue().length());
			
		
	}

}
