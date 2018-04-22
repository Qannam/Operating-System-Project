import java.util.Random;

public class Run {

	public static void main(String[] args) {
		DataGeneration d = new  DataGeneration();
		d.generate();
	
<<<<<<< HEAD
		ArrayOfPCB ArrayOfPCB = new ArrayOfPCB("/Users/Qannam/Documents/workspace/OS_project/test");
=======
		ArrayOfPCB ArrayOfPCB = new ArrayOfPCB("D://Users//Qannam//OS Project//test");
>>>>>>> a8343177225a645bbb030b586da632d54558bc77
		
		CPU CPU = new CPU(ArrayOfPCB);
		CPU.run();
		
<<<<<<< HEAD
		double numberOfJobs = CPU.getFinishQueue().length();
		System.out.println("The number of initially generated jobs stored on the H-disk: "+(int)numberOfJobs);
		
		double sumSize = 0;
		double executionNormally = 0 ;
		double executionAbnormally = 0 ;
		int CPUBoundJobs = 0 ;

		while(CPU.getFinishQueue().length() > 0){
			PCB p = CPU.getFinishQueue().serve();
=======
		while(CPU.getFinishQueue().length() > 0){
			PCB p = CPU.getFinishQueue().serve();
			System.out.print("ID:"+p.getID() +";  expected execution:"+p.getCPUTime()+";  memory sizes:"+p.getMemorySize()+"; Running time:"+p.getRunningTime()+";  IO time:"+p.getWaitingTime()+";  normaly:"+p.getIsTerminatedNormally()+";");
			System.out.println();
			System.out.println();
		}
>>>>>>> a8343177225a645bbb030b586da632d54558bc77
			
			sumSize += Integer.parseInt(p.getMemorySize());
			
			if(p.getIsTerminatedNormally())
				executionNormally++;
			else
				executionAbnormally++;
			
			if(Integer.parseInt(p.getRunningTime()) > Integer.parseInt(p.getWaitingTime()))
				CPUBoundJobs++;
		}
		System.out.println("The average program size of all jobs: "+(int)(sumSize / numberOfJobs) );
		System.out.println("The average number of jobs that have completed their execution normally: "+executionNormally / numberOfJobs *100 +"%");
		System.out.println("The average number of jobs that have completed their execution abnormally: "+executionAbnormally / numberOfJobs *100+"%");
		System.out.println("The number of CPU bound jobs: "+CPUBoundJobs);

	}

}