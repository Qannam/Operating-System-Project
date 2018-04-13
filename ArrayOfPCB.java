import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArrayOfPCB {
	Queue list ;
	int index; 
	String path;

	public ArrayOfPCB(String path) {
		list = new Queue<PCB>();
		index = 0 ;
		this.path = path;
	}

	public void run() {
		// pass the path to the file as a parameter
		File file = new File(path);
		Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String[] splArray = split(sc.nextLine());
				PCB newPCB = new PCB(splArray[0], "new", splArray[1], splArray[2], "0", "0");
				list.enqueue(newPCB) ;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] split(String s) {
		String[] resultsArr =new String[3];
		String string = s;
		
		String[] spl_array = string.split(";");
		
		String ID=spl_array[0];
		String Sp_ID=ID.split(":")[1];
		
		String CPU=spl_array[1];
		String Sp_CPU=CPU.split(":")[1];
		
		String SZ=spl_array[2];
		String Sp_SZ=SZ.split(":")[1];
		
		resultsArr[0]=Sp_ID;
		resultsArr[1]=Sp_CPU;
		resultsArr[2]=Sp_SZ;
		
		return resultsArr ;
	}

}
