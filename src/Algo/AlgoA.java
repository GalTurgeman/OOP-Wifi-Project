package Algo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

import IO.CreateDB;
import Main.INITIAL;
import Main.Parameters;
import Main.Wifi;


public class AlgoA{

	private static LinkedList<String> wifiParameters;
	private LinkedList<Wifi> list = new LinkedList<Wifi>();
	private LinkedList<Wifi> List_With_Same_Mac= new LinkedList<Wifi>();
	private String MAC;
	private double [] temp; 
	public double [] wSum ;
	private FileWriter fw;
	private PrintWriter pw;
	private FileReader fr ;
	private BufferedReader br;
	
	public AlgoA(int n){
		Parameters.setNumberAlgoA(n);
		System.out.println("Algo 1 will be limit by "+ n +" similiar lines.");
		this.list.addAll(CreateDB.getFullDB());
//		ReadComb();
		AlgoA_Start();
	}
	/**
	 * ReadComb read the combine CSV file.
	 *  
	 *
	 */
	private void ReadComb()  {
		
			String currentLine = "";
			String [] currentLineArr;
			try {
				fr = new FileReader(INITIAL.getWritePath());
				br = new BufferedReader(fr);
				currentLine = br.readLine();
				currentLineArr = currentLine.split(",");
				if(currentLineArr[0].equals("TIME")) {
					currentLine = br.readLine();
				}
					while (currentLine != null) {
						currentLineArr = currentLine.split(",");
						insert_Line_asWifi(currentLineArr);	
						currentLine =br.readLine();
					}
			} catch (IOException e) {
				System.err.println(e);
			}
	}
	private void insert_Line_asWifi(String[] lineArr) {
		wifiParameters = new LinkedList<String>();
		int NumOfWifiInLine = Integer.parseInt(lineArr[5]); //The number of wifis that wrote in the line .
		wifiParameters.add(lineArr[0]+","+
				lineArr[1]+","+
				lineArr[2]+","+
				lineArr[3]+","+
				lineArr[4]+","); // first member : "TIME,ID,LAT,LON,ALT," 
		for (int i = 0; i <NumOfWifiInLine*4; i+=4) {
			wifiParameters.add(lineArr[6+i]+","+lineArr[7+i]+","+lineArr[8+i]+","+lineArr[9+i]); // the number 6,7,8,9 represent the first Wifi#1 on each step of i we will jump to next wifi
		}// here's the parameter collection are done,The collection represents like this i.e:: wifiParameters= new <"TIME,ID,LAT,LON,ALT," , "wifi#1Parameters" , "wifi#2Parameters" , ..>();

		//Creating wifi collection for the KML writer.
		
		for (int i = 1; i < wifiParameters.size(); i++) { //"TIME,ID,LAT,LON,ALT,SSID,MAC,Frequency,Signal"
			String tmp = wifiParameters.get(0)+wifiParameters.get(i); // combain the "TIME,ID,LAT,LON,ALT" + wifi(data) 
			String[] tmpConstractor = tmp.split(",");
			list.add(new Wifi(tmpConstractor, i)); //adding the wifi by uniqe constractor.
		}
	}
	
	public void AlgoA_Start(){
		for (Wifi a : list) {
			if(!a.getIsCheked()){//By default getIsCheked -> false
				List_With_Same_Mac.add(a);//Adding the first one.
				//Object NEWWIFIALGO -> null
				CheckLoc(a.getMAC());
			}
			List_With_Same_Mac.clear();//Clear the list to be ready for next MAC;
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
	public String CheckLoc(String mac){
		//		List_With_Same_Mac = new LinkedList<Wifi>();

		boolean flag = false;
		int counter = 0;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getMAC().equals(mac)){
				list.get(i).setIsChecked(true);//visit the wifi.
				List_With_Same_Mac.add(list.get(i).get());
				flag = true;
				counter++;
			}
		}
		if(flag){
			SortTheWifiListRssi(List_With_Same_Mac);
			int n= Parameters.numberAlgoA;
			temp = Calc(List_With_Same_Mac,List_With_Same_Mac.size());
//			WriteToCSVAlgoA();
			return Arrays.toString(temp);
			//create new obejct NewWIFIalgoA -> send the array and the first wifi 
			//INITINT.ALGO<NEW> -> add the new object. 
		}
		else{
			return("MAC was not found at Database.");
		}
	}

	public double[] Calc(LinkedList<Wifi> tmp,int n){
		double [] Wcenter = new double [3];//Think to transfer it up, make it global.
		double TotalWeight=0;
		double [] sum = new double [3];
		//		double [] wSum = new double [3];

		for (int i = 0; i < n; i++) {
			//			System.out.println(i);
			Wcenter[0]+=tmp.get(i).getLAT_double();
			Wcenter[1]+=tmp.get(i).getLON_double();
			Wcenter[2]+=tmp.get(i).getALT_double();
			tmp.get(i).SetWeightAlgoA();
			TotalWeight += tmp.get(i).getWeightAlgoA();
			tmp.get(i).Set_wALT(tmp.get(i).getWeightAlgoA());
			tmp.get(i).Set_wLAT(tmp.get(i).getWeightAlgoA());
			tmp.get(i).Set_wLON(tmp.get(i).getWeightAlgoA());
			sum[0] += tmp.get(i).get_wLAT();
			sum[1] += tmp.get(i).get_wLON();
			sum[2] += tmp.get(i).get_wALT();
		}
		this.wSum = new double[3];
		this.wSum[0] = sum[0]/ TotalWeight;
		this.wSum[1] =sum[1] / TotalWeight;
		this.wSum[2] =sum[2] / TotalWeight;
		//(double [] point , String SSID , String MAC , String Time){
		Wifi a = new Wifi(wSum,tmp.getFirst().getSSID(),tmp.getFirst().getMAC(),tmp.getFirst().getTime() , tmp.getFirst().getRSSI());
//		INITIAL.AlgoCollection.add(a);

		return this.wSum;
	}
	public String toString(){
		String s = Arrays.toString(this.wSum);
		return "Router Location is : \n" + s;
	}
	public void WriteToCSVAlgoA(){
		try{
			fw = new FileWriter(INITIAL.getWritePathForAlgoA());
			pw = new PrintWriter(fw);
			//double [] point , String SSID , String MAC , String Time,String RSSI)
			pw.println("Time,w_LAT,w_LON,w_ALT,SSID,MAC,RSSI");
			for (int i = 0; i < INITIAL.AlgoCollection.size(); i++) {
				pw.println(INITIAL.AlgoCollection.get(i).getTime()+","+
						INITIAL.AlgoCollection.get(i).getLAT()+","+
						INITIAL.AlgoCollection.get(i).getLON()+","+
						INITIAL.AlgoCollection.get(i).getALT()+","+
						INITIAL.AlgoCollection.get(i).getSSID()+","+
						INITIAL.AlgoCollection.get(i).getMAC()+","+
						INITIAL.AlgoCollection.get(i).getRSSI()
						);
			}
			pw.close();
			fw.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}

}