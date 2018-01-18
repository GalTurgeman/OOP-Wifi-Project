package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

import Algo.AlgoBNews;
import parameters.INITIAL;
import parameters.SortByTimeComparator;
import parameters.Wifi;
import parameters.sortByRSSI;

public class CreateDB {

	private String pathName;
	public static LinkedList<Wifi> FullDB = new LinkedList<Wifi>();
	public static LinkedList<Wifi> TempFullDB = new LinkedList<Wifi>();
	public static LinkedList<LinkedList<Wifi>> WifiSamplesDB = new LinkedList<LinkedList<Wifi>>();
	private static LinkedList<String> wifiParameters;
	private static LinkedList<String> ListMacCounter = new LinkedList<String>();
	public static int MacCounter;
	public static int Records;
	public static int RecordsToSave;
	private static PrintWriter pw;
	private static FileWriter fw ;
	private FileReader fr ;
	private BufferedReader br;

	/**fromWhere will have 2 option to be .
	 * 1 mean csvComb
	 * 0 mean folder
	 * @param pathName
	 * @param fromWhere
	 */
	public CreateDB(File pathName, int fromWhere)   {
		if(fromWhere == 1) {
			CSVHandler(pathName);
			getMacCounter(FullDB);
		}
		else {
			WriteFile tt =new WriteFile();
			INITIAL.setFileReadPath(pathName);
			INITIAL.setReadPath(pathName.getAbsolutePath());
			try {
				tt.ReWriteTheData();
				CSVHandler(INITIAL.getFileWritePath());
				getMacCounter(FullDB);
			}
			catch (FileNotFoundException e) {
				System.out.println(e);
			}
		}
	}
	public void CSVHandler(File pathName2) {
		ReadComb(pathName2);
	}
	/**
	 * Know how to handle Comb CSV Files.
	 * @param File pathName
	 */
	private void ReadComb(File pathName)  {

		String currentLine = "";
		String [] currentLineArr;
		try {
			fr = new FileReader(pathName);
			br = new BufferedReader(fr);
			currentLine = br.readLine();
			if(currentLine==null || currentLine.isEmpty())//to stop reading empty or null lines
				return;
			currentLineArr = currentLine.split(",");
			if(currentLineArr[0].equals("TIME")) {
				currentLine = br.readLine();
			}
			while (currentLine != null) {
				currentLineArr = currentLine.split(",");
				insert_Line_asWifi(currentLineArr);	
				currentLine = br.readLine();
				if(currentLine==null || currentLine.isEmpty())//to stop reading empty or null lines
					break;
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private void insert_Line_asWifi(String[] lineArr) {
		Records++;
		wifiParameters = new LinkedList<String>();
		int NumOfWifiInLine = Integer.parseInt(""+lineArr[5]); //The number of wifis that wrote in the line .
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
			FullDB.add(new Wifi(tmpConstractor, i)); //adding the wifi by uniqe constractor.
			Collections.sort(FullDB , new SortByTimeComparator());
		}

	}

	public static int getMacCounter(LinkedList<Wifi> fullDB) {
		if(!FullDB.isEmpty()) {
			ListMacCounter.add(fullDB.getFirst().getMAC());
			for (Wifi wifi : fullDB) {
				if(!ListMacCounter.contains(wifi.getMAC())) {
					ListMacCounter.add(wifi.getMAC());
				}
			}
			MacCounter = ListMacCounter.size();
		}
		else if(!TempFullDB.isEmpty()) {
			ListMacCounter.add(TempFullDB.getFirst().getMAC());
			for (Wifi wifi : TempFullDB) {
				if(!ListMacCounter.contains(wifi.getMAC())) {
					ListMacCounter.add(wifi.getMAC());
				}
			}
			MacCounter = ListMacCounter.size();
		}
		return MacCounter;
	}

	public static LinkedList<Wifi> getFullDB() {
		return FullDB;
	}

	public static void setFullDB(LinkedList<Wifi> fullDB) {
		FullDB = fullDB;
	}
	public static void WriteToCSVFullDB(LinkedList<Wifi> list) { 
		Collections.sort(list,new SortByTimeComparator());
		try{
			fw = new FileWriter(INITIAL.getSaveToFullDBPath());
			pw = new PrintWriter(fw);
			pw.print("TIME,ID,LAT,LON,ALT,Number Of Networks");
			for (int i = 1; i <= INITIAL.getOneLineWifiCount(); i++) {
				pw.print(",SSID" + i);
				pw.print(",MAC" + i);
				pw.print(",Frequency" + i);
				pw.print(",Signal" + i);
			}
			//pw.println(x); //header
			int j;
			int i = 0;
			System.out.println("CREATE DB Writing file ....");
			//			for ( i = 0 ,j=0; i < list.size(); i=j) {
			for ( i = 0 ,j=1; i < list.size()-1; i = j) {
				LinkedList<Wifi> tmpListSameTime = new LinkedList<Wifi>();
				tmpListSameTime.add(list.get(i));	//first we what to add the list.i
				while(Check2Wifis(list.get(i), list.get(j)) ){
					//keep write the same wifis
					tmpListSameTime.add(list.get(j));
					j++;
					if(j == list.size())
						break;
				}

				//SortTheWifiListRssi(tmpListSameTime); //tested , working
				Collections.sort(tmpListSameTime, new sortByRSSI());
				TakeTheNStrongestByRssi(INITIAL.getOneLineWifiCount(),tmpListSameTime);//tested , working

				pw.println();
				pw.print(tmpListSameTime.get(0).getTime() + ",");
				pw.print(tmpListSameTime.get(0).getModel() + ",");
				pw.print(tmpListSameTime.get(0).getLAT() + ",");
				pw.print(tmpListSameTime.get(0).getLON() + ",");
				pw.print(tmpListSameTime.get(0).getALT() + ",");
				pw.print((tmpListSameTime.size()) + ",");


				for(int k = 0 ; k < tmpListSameTime.size(); k++){ // print the data of every wifi //TODO check why tmp.size()-1;
					pw.print(tmpListSameTime.get(k).getSSID() + " , "+tmpListSameTime.get(k).getMAC() + " , "+tmpListSameTime.get(k).getChannel() + " , "+tmpListSameTime.get(k).getRSSI()+ " , ");
				}
			}
			pw.close();
			fw.close();
		}catch(IOException ex){
			System.out.println("Some problem" + ex);
		}
		//		System.out.println("File in: "+INITIAL.getFileWritePath().getAbsolutePath()+"\nDone!");	
	}

	/**
	 * purpose: check if the two given wifis is the same in the parameters of :  Time , model, lat, lon , alt.
	 * @param a first wifi
	 * @param b	second wifi
	 * @return True if there is symmetry , false if there is no symmetry.
	 */
	public static  boolean Check2Wifis(Wifi a, Wifi b){
		if(
				a.getTime().equals(b.getTime())
				&& a.getLAT().equals(b.getLAT())
				&& a.getLON().equals(b.getLON())
				&& a.getALT().equals(b.getALT())
				)return true;
		return false;
	}

	/**
	 * Removing from the collection elements that exceed the given amount per line 
	 * @param elementsInOneLine
	 * @param tmp
	 */
	private static void TakeTheNStrongestByRssi(int elementsInOneLine, LinkedList<Wifi> tmp){
		int tempSize = tmp.size();
		if(elementsInOneLine < tmp.size()){
			int i = 1;
			while(i < tempSize - elementsInOneLine){
				tmp.removeLast();
				i++;
			}
		}
		else{
			return;
		}
	}
	public static void ClearDB() {
		FullDB.clear();
		Records =0 ;
		MacCounter =0;
		ListMacCounter.clear();
	}
	public static void Undo() {	
		ClearDB();
		FullDB.addAll(TempFullDB);
		TempFullDB.clear();
		Records = RecordsToSave;
		getMacCounter(FullDB);
	}
	public static void CreateWifiSamples() {
		if(!WifiSamplesDB.isEmpty() || FullDB.isEmpty()) {
			return;
		}

		LinkedList<Wifi> SameTime = new LinkedList<Wifi>();
		String timeToCompare=FullDB.getFirst().getTime();
		int place;
		//If has same time add it to list.
		
			for (int i = 0; i < FullDB.size()-1; i++) {
				if(FullDB.get(i).getTime().equals(FullDB.get(i+1).getTime())) {
					SameTime.add(FullDB.get(i));
				}
				else if(FullDB.get(i).getTime().equals(FullDB.get(i-1).getTime())) {
					SameTime.add(FullDB.get(i));
					WifiSamplesDB.add(SameTime);
					SameTime = new LinkedList<>();
				}
			}
	}
	//Lenovo PB2-690Y
	public static int getRecords(LinkedList<Wifi> fullDB) {
		CreateWifiSamples();
		Records =WifiSamplesDB.size();
		return Records;
	}
	public static void parameters (String [] args) {
		File f = new File("/Users/gal/git/Wifi_Project/toRead");

		CreateDB c = new CreateDB(f,0);
		c.CreateWifiSamples();
		AlgoBNews b = new AlgoBNews("e4:95:6e:40:87:1a"  , "-20", "b4:ee:b4:36:d2:b0", "-51", "88:dc:96:17:c0:9e", "-51", 3);
	
	}
}