import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.sound.sampled.Line;

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
	double sumOfPI = 0;

	public AlgoB(LinkedList<Wifi> input_wifi) {
		this.Input_wifi =input_wifi;//get the input wifi line from no gps file.
		Run();
	}
	/**
	 * Compare line fron no gps file to whole DB.
	 * add each line to Linked list name :InputLines.
	 */
	public void Run() {
		
		for (int i = 0; i < INITIAL.WifiSamples.size(); i++) {
			LineAlgo2 l = new LineAlgo2(Input_wifi, INITIAL.WifiSamples.get(i));
			InputLines.add(l);
		}
		Collections.sort(InputLines, new sortByPI());
		SumOfPIForTheN(6);
//		System.out.println(Arrays.toString(calcforPoint(6)));
		/*/Sort
		 * Take the n-th similiar//maybe to transfer to new collection /
		 * get Point
		 * Print / Write to file / save.
		 */


	}
	public void SumOfPIForTheN(int n) {
		for (int i = 0; i < n && i<InputLines.size(); i++) {//Basicly sum the pi
			sumOfPI+=InputLines.get(i).getP();
		}
	}
	public double[] calcforPoint(int n) {
		double [] sum  = new double[3];
		double [] w_sum = new double [3];
		LinkedList<Double> GEO = new LinkedList<Double>();
//		System.out.println(InputLines.size());
//		System.out.println(Input_wifi.size());
		for (int i = 0; i < n; i++) {
			InputLines.get(i).getSample().getFirst().getLAT_double();
			InputLines.get(i).getSample().getFirst().getLON_double();
			InputLines.get(i).getSample().getFirst().getALT_double();
		}
//		try {
			for (int i = 0; i < n  && i <Input_wifi.size() && i < InputLines.get(i).getSample().size(); i++) {
//				System.out.print(Input_wifi.size()+" ");
//				System.out.print(InputLines.get(i).getSample().get(i).getSSID()+ "  ");
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
		return w_sum;
	}
}
