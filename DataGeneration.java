import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class DataGeneration {
	private int ID ;
	
	public DataGeneration (){
		ID = 0 ;
	}
	
	public int randomCPUTime(){
		Random rand = new Random();
		int max = 512;
		int min =16;
		return rand.nextInt((max - min) + 1) + min; 
	}
	
	public int randomSize(){
		Random rand = new Random();
		int max = 16384;
		int min =16;
		return rand.nextInt((max - min) + 1) + min; 
	}

	public void generate(){
		PrintWriter writer;
		try {
			writer = new PrintWriter("test");
		int count = 0;
		while(count++ < 3000){
			int meorySize = randomSize();
			int CPUTime = randomCPUTime();
			writer.println("ID:" + ID++ +";CPU:" + CPUTime +";SZ:"+meorySize);
		}
		writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}