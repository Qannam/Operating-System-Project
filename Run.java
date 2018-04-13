import java.util.Random;

public class Run {

	public static void main(String[] args) {
		DataGeneration d = new  DataGeneration();
		d.generate();
	
		ArrayOfPCB ArrayOfPCB = new ArrayOfPCB("D://Users//Qannam//OS Project//test");
		
		CPU CPU = new CPU(ArrayOfPCB);
		CPU.run();
		
		while(CPU.getFinishQueue().length() > 0){
			PCB p = CPU.getFinishQueue().serve();
			System.out.print("ID:"+p.getID() +";  expected execution:"+p.getCPUTime()+";  memory sizes:"+p.getMemorySize()+"; Running time:"+p.getRunningTime()+";  IO time:"+p.getWaitingTime()+";  normaly:"+p.getIsTerminatedNormally()+";");
			System.out.println();
			System.out.println();
		}
			
		
	}

}
