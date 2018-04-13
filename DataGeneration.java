import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
public class DataGeneration {
	private int ID ;
	private int HDSize;
	private int sizeSum;
	
	public DataGeneration (){
		ID = 0 ;
		HDSize = 524288; // 0.5GB = 524288KB
	}
	
	public int randomCPUTime(){
		Random rand = new Random();
		int max = 512;
		int min =16;
		return rand.nextInt((max - min) + 1) + max; 
	}
	
	public int randomSize(){
		Random rand = new Random();
		int max = 16384;
		int min =16;
		return rand.nextInt((max - min) + 1) + max; 
	}

	public void generate(){
		PrintWriter writer;
		try {
			writer = new PrintWriter("test");
		
		while(sizeSum < HDSize){
			int meorySize = randomSize();
			if((meorySize + sizeSum) > HDSize)
				break;
			int CPUTime = randomCPUTime();
			sizeSum += meorySize;
			
			writer.println("ID:" + ID++ +";CPU:" + CPUTime +";SZ:"+meorySize);
		}
		writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
