package Algo;
 import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import IO.CreateDB;
import Main.INITIAL;
import Main.Parameters;
import Main.Wifi;
import de.micromata.opengis.kml.v_2_2_0.Point;


public class AlgoB {
	/**
	 * All the line from DB , Data1,Data2.... 
	 * 
	 */
	private LinkedList<LineAlgo2> InputLines = new LinkedList<LineAlgo2>();
	/**Input from the no_gps file, get from ReadInputAlgo2.
	 * 
	 */
	private LinkedList<Wifi> Input_wifi = new LinkedList<Wifi>();
	/**
	 * 
	 */
	public static LinkedList<double[]> GEO = new LinkedList<double[]>();
	double sumOfPI = 0;
	
	//TODO : No use of this constructor anymore!
	
//	public AlgoB(LinkedList<Wifi> input_wifi ,int n) {
//		Parameters.setNumberAlgoB(n);
//		this.Input_wifi =input_wifi;//get the input wifi line from no gps file.
//		Run(INITIAL.WifiSamples.);
//	}
	
	public AlgoB(String LineFromNoGps,int n) {
		Parameters.setNumberAlgoB(n);
		String currentLine = LineFromNoGps;
		Wifi a;
		System.out.println(LineFromNoGps);
		System.out.println("ENTER CONSTRUCTOR");
			String [] currentLineArr = currentLine.split(",");
			for (int i = 0; i < Integer.parseInt(currentLineArr[5])*4; i+=4) {
				a = new Wifi();
				a.setSSID(currentLineArr[i+6]); 
				a.setMAC(currentLineArr[i+7]);
				a.setChannel(currentLineArr[i+8]);
				a.setRXL(currentLineArr[i+9]);
				Input_wifi.add(a);
			}
			System.out.println("FINISH FOR");
		Run();
	}
	public AlgoB(String MAC1, String sig1,String MAC2, String sig2,String MAC3, String sig3) {
		
		Wifi a = new Wifi();
		Wifi b = new Wifi();
		Wifi c = new Wifi();
		a.setMAC(MAC1);
		b.setMAC(MAC2);
		c.setMAC(MAC3);
		
		a.setRXL(MAC1);
		b.setRXL(MAC2);
		c.setRXL(MAC3);
		LinkedList<Wifi> temp = new LinkedList<Wifi>();
		temp.add(a);
		temp.add(b);
		temp.add(c);
		this.Input_wifi.addAll(temp);
		Run();
	}
	/**
	 * Compare line from noGps file to whole DB.
	 * add each line to Linkedlist name :InputLines.
	 */
	public void Run() {
		/*/
		 * Send Line From noGps file and compare each line from DB.
		 * 
		 */
		CreateDB.CreateWifiSamples();
		System.out.println("WIFI SAMPLE SIZE:  " + CreateDB.WifiSamplesDB.size());
		for (int i = 0; i < CreateDB.WifiSamplesDB.size(); i++) {
			LineAlgo2 l = new LineAlgo2(Input_wifi, CreateDB.WifiSamplesDB.get(i));
			InputLines.add(l);
		}
		System.out.println("WIFI SAMPLE SIZE:  " + InputLines.size());
		Collections.sort(InputLines, new sortByPI());
		SumOfPIForTheN(Parameters.numberAlgoB);
		getPoint(Parameters.numberAlgoB);
//		System.out.println(Arrays.toString(calcforPoint(6)));
		/*/Sort
		 * Take the n-th similiar//maybe to transfer to new collection /
		 * get Point
		 * Print / Write to file / save.
		 */
		
	}
	/**
	 * Sum the Pi from all Lines.
	 * @param n
	 */
	private void SumOfPIForTheN(int n) {
		for (int i = 0; i < n && i<InputLines.size(); i++) {
			sumOfPI+=InputLines.get(i).getP();
		}
	}
	public double[] getPoint(int n) {
		double [] sum  = new double[3];
		double [] w_sum = new double [3];
		
//		System.out.println(InputLines.size());
//		System.out.println(Input_wifi.size());
		for (int i = 0; i < n || i<InputLines.get(i).getSample().size(); i++) {
			InputLines.get(i).getSample().getFirst().getLAT_double();
			InputLines.get(i).getSample().getFirst().getLON_double();
			InputLines.get(i).getSample().getFirst().getALT_double();
		}
//		try {
			for (int i = 0; i < n  && i <Input_wifi.size() && i < InputLines.get(i).getSample().size(); i++) {
//				System.out.print(Input_wifi.size()+" ");
				
				sum[0] += InputLines.get(i).getP()*InputLines.get(i).getSample().get(i).getLAT_double();
				sum[1] += InputLines.get(i).getP()*InputLines.get(i).getSample().get(i).getLON_double();
				sum[2] += InputLines.get(i).getP()*InputLines.get(i).getSample().get(i).getALT_double();
			}			
//		} catch (Exception e) {
//			System.out.println(e);
//		}
		w_sum[0] = sum[0] /sumOfPI;
		w_sum[1] = sum[1] /sumOfPI;
		w_sum[2] = sum[2] /sumOfPI;
		InputLines.clear();
		GEO.add(w_sum);
		return w_sum;
	}
}
