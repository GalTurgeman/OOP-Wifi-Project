import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.LinkedList;

public class ReadInputAlgo2 {
	/**
	 * The PI for the line.
	 */
	public double pi;
	/**
	 * Input from the no_gps file
	 */
	static private LinkedList<Wifi> Input_wifi = new LinkedList<Wifi>();
	private FileReader fr ;
	private BufferedReader br;
	private String InputPath;
	
	
	public ReadInputAlgo2(String InputPath){
		this.InputPath=InputPath;
		readLine(this.InputPath);
	}
	private void readLine(String Inputpath){
		Wifi a ;
		try{
			fr = new FileReader(Inputpath);
			br= new BufferedReader(fr);
			String currentLine = br.readLine();
			while(currentLine != null){
				String [] currentLineArr = currentLine.split(",");
				for (int i = 0; i < Integer.parseInt(currentLineArr[5])*4; i+=4) {
					a = new Wifi();
					a.setSSID(currentLineArr[i+6]); 
					a.setMAC(currentLineArr[i+7]);
					a.setChannel(currentLineArr[i+8]);
					a.setRXL(currentLineArr[i+9]);
					Input_wifi.add(a);
				}
				AlgoB tmp = new AlgoB(Input_wifi);
				Input_wifi.clear();
				currentLine = br.readLine();
			}
		}
		catch (IOException e) {
			System.err.println(e);
		}
	}
}
