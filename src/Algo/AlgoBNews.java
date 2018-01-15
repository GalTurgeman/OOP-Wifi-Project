package Algo;

import java.awt.LinearGradientPaint;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import IO.CreateDB;
import Main.Wifi;

public class AlgoBNews {

	private LinkedList<LineAlgo2> All_lines = new LinkedList<LineAlgo2>();
	private int n;
	private double sumOfPI;
	private double [] Point;
	
	public AlgoBNews(String MAC1, String sig1,String MAC2, String sig2,String MAC3, String sig3 , int number) {
		this.n = number;
		Wifi a = new Wifi();
		Wifi b = new Wifi();
		Wifi c = new Wifi();
		a.setMAC(MAC1);
		b.setMAC(MAC2);
		c.setMAC(MAC3);
		
		a.setRXL(MAC1);
		b.setRXL(MAC2);
		c.setRXL(MAC3);
		LinkedList<Wifi> LineInput = new LinkedList<Wifi>();
		LineInput.add(a);
		LineInput.add(b);
		LineInput.add(c);	
		Run(LineInput);
	}
	public AlgoBNews(String Line, int number) {
	/*/12/05/17 11:48 AM,model=SM-G950F_device=dreamlte,?,?,?,3,
	 * IT-MNG,1c:b9:c4:15:ed:b8,1,-81, ,
	 * 8c:0c:90:ae:16:83,11,-86,Ariel_University,
	 * 1c:b9:c4:16:ed:3c,44,-91
	 */
	 
		this.n = number;
		LinkedList<Wifi> LineInput = new LinkedList<Wifi>();
		String [] LineArr = Line.split(",");
		String time = LineArr[0];
		String model = LineArr[1];
		
		for (int i = 0; i < Integer.parseInt(LineArr[5])*4; i+=4) {
			Wifi a = new Wifi();
			a.setSSID(LineArr[i+6]); 
			a.setMAC(LineArr[i+7]);
			a.setChannel(LineArr[i+8]);
			a.setRXL(LineArr[i+9]);
			LineInput.add(a);
		}
		Run(LineInput);
	}
	public String getPoint() {
		return Arrays.toString(this.Point);
	}
	public void setPoint(double[] point) {
		Point = point;
	}
	public void Run(LinkedList<Wifi> LineInput) {
		CreateDB.CreateWifiSamples();
		for (int i = 0; i < CreateDB.WifiSamplesDB.size(); i++) {
//			System.out.println(LineInput.size());
//			System.out.println(CreateDB.WifiSamplesDB.get(i).size());
				LineAlgo2 l = new LineAlgo2(LineInput, CreateDB.WifiSamplesDB.get(i));
//				System.out.println(i+":"+l.p);
				All_lines.add(l);
//				System.out.println(l.getP());
		}
		Collections.sort(All_lines,new sortByPI());
		
			for (int i = 0; i < n && i<All_lines.size(); i++) {
				this.sumOfPI+=All_lines.get(i).getP();
//				System.out.println(i+"P"+All_lines.get(i).getP());
			}
			
			double [] sum  = new double[3];
			double [] w_sum = new double [3];
			
			for (int i = 0; i < n; i++) {
				sum[0]+=All_lines.get(i).getP()*All_lines.get(i).getLAT();
				sum[1]+=All_lines.get(i).getP()*All_lines.get(i).getLAT();
				sum[2]+=All_lines.get(i).getP()*All_lines.get(i).getLAT();
			}
//			System.out.println("SumOfPI"+sumOfPI);
//			System.out.println("sum[0]"+sum[0]);
//			System.out.println("sum[1]"+sum[1]);
//			System.out.println("sum[2]"+sum[2]);
		w_sum[0] =sum[0] / sumOfPI;
		w_sum[1] =sum[1] / sumOfPI;
		w_sum[2] =sum[2] / sumOfPI;
//		System.out.println(Arrays.toString(w_sum));
		this.Point = w_sum;
	}
}
