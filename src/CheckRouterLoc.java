import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class CheckRouterLoc{

	private LinkedList<Wifi> list = new LinkedList<Wifi>();
	private LinkedList<Wifi> List_With_Same_Mac= new LinkedList<Wifi>();
	private String MAC;
	private double [] temp; 
	public double [] wSum ;


	public CheckRouterLoc(String MAC){
		
		if(MAC.contains(""+' ')){
			MAC= MAC.replaceAll("  ", "").trim();
		}
		this.MAC = MAC;		
		try {
			CheckLoc(this.MAC);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	public void SortTheWifiListRssi(LinkedList<Wifi> tmp){
		for (int i = 1; i < tmp.size(); i++) {
			int j =i;
			while(j>0 && tmp.get(j-1).getRssiInINT() <tmp.get(j).getRssiInINT()){
				Wifi tmp1 = tmp.get(j-1);
				tmp.set(j-1, tmp.get(j));
				tmp.set(j, tmp1);
				j--;
			}
		}
	}

	public String CheckLoc(String mac) throws FileNotFoundException {
		List_With_Same_Mac = new LinkedList<Wifi>();
		WriteFile w =new WriteFile();
		w.ReWriteTheData();
		list = w.getFineWifiList();

		boolean flag = false;
		int counter = 0;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getMAC().equals(mac)){
				List_With_Same_Mac.add(list.get(i).get());
				flag = true;
				counter++;
			}
		}
		System.out.println(List_With_Same_Mac);
		if(flag){
			SortTheWifiListRssi(List_With_Same_Mac);
			temp = Calc3(List_With_Same_Mac,List_With_Same_Mac.size());
			return Arrays.toString(temp);
		}
		else{
			return("MAC was not found at Database.");
		}
	}
	public double[] Calc3(LinkedList<Wifi> tmp,int n){
		double [] Wcenter = new double [3];
		double TotalWeight=0;
		double [] sum = new double [3];
		//		double [] wSum = new double [3];

		for (int i = 0; i < n; i++) {
			Wcenter[0]+=tmp.get(i).getLAT_double();
			Wcenter[1]+=tmp.get(i).getLON_double();
			Wcenter[2]+=tmp.get(i).getALT_double();
			tmp.get(i).SetWeight();
			TotalWeight += tmp.get(i).getWeight();
			tmp.get(i).Set_wALT(tmp.get(i).getWeight());
			tmp.get(i).Set_wLAT(tmp.get(i).getWeight());
			tmp.get(i).Set_wLON(tmp.get(i).getWeight());
			sum[0] += tmp.get(i).get_wLAT();
			sum[1] += tmp.get(i).get_wLON();
			sum[2] += tmp.get(i).get_wALT();
		}
		this.wSum = new double[3];
		this.wSum[0] = sum[0]/ TotalWeight;
		this.wSum[1] =sum[1] / TotalWeight;
		this.wSum[2] =sum[2] / TotalWeight;
		return this.wSum;
	}
	public String toString(){
		String s = Arrays.toString(this.wSum);
		return s;
	}
}