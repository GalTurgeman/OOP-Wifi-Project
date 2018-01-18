package Algo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import parameters.INITIAL;
import parameters.Parameters;
import parameters.Wifi;

public class ReadWriteInputAlgo2 {
	/**
	 * Input from the no_gps file
	 */
	static private LinkedList<Wifi> Input_wifi = new LinkedList<Wifi>();
	private FileReader fr ;
	private BufferedReader br;
	private String InputPath;

	/**
	 * Constructor for read Input no gps file .
	 * Enter File Path.
	 * @param InputPath
	 */
	public ReadWriteInputAlgo2(String InputPath ,int n){
		System.out.println("The Algo 2 will be limit by " + n+ " similiar lines.");
		Parameters.setNumberAlgoB(n);
		this.InputPath=InputPath;
		readLine(this.InputPath);
		System.out.println("Done reading input file");
		WritePointInLine();
		System.out.println("Done writing input file");
	}
	/**
	 * Reading each line and add it to the Input_wifi Collection.
	 * Each line is compare to the whole DB.
	 * @param Inputpath
	 */
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
				//TODO : no use anymore!
//				AlgoB tmp = new AlgoB(Input_wifi,Parameters.getNumberAlgoB());
				Input_wifi.clear();
				currentLine = br.readLine();
			}
			fr.close();
			br.close();
		}
		catch (IOException e) {
			System.err.println(e);
		}
	}
	private void WritePointInLine() {
		try {
			fr = new FileReader(INITIAL.getReadPathForAlgoBInput());
			br =new BufferedReader(fr);
			FileWriter fw = new FileWriter(INITIAL.getWritePathForAlgoBInput());
			PrintWriter pw = new PrintWriter(fw);
			String currentLine = br.readLine();
			int i= 0;
			while(currentLine!=null) {
				currentLine = currentLine.replace(",?,?,?,", ","+AlgoB.GEO.get(i)[0]+","+AlgoB.GEO.get(i)[1]+","+AlgoB.GEO.get(i)[2]+",");
				pw.println(currentLine);
				currentLine=br.readLine();
				i++;
			}
			fr.close();
			br.close();
			fw.close();
			pw.close();
		} catch (IOException e) {
			System.out.println(e);
		}	
	}
}
