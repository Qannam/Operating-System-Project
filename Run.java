import java.util.Random;

public class Run {

	public static void main(String[] args) {
		DataGeneration d = new  DataGeneration();
		d.generate();
	
		ArrayOfPCB ArrayOfPCB = new ArrayOfPCB("/Users/Qannam/Documents/workspace/OS_project/test");
		
		CPU CPU = new CPU(ArrayOfPCB);
		CPU.run();
		
		double numberOfJobs = CPU.getFinishQueue().length();
		System.out.println("The number of initially generated jobs stored on the H-disk: "+(int)numberOfJobs);
		
		double sumSize = 0;
		double executionNormally = 0 ;
		double executionAbnormally = 0 ;
		int CPUBoundJobs = 0 ;

		while(CPU.getFinishQueue().length() > 0){
			PCB p = CPU.getFinishQueue().serve();
			
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