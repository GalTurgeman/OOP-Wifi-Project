package Filters;
import java.io.BufferedReader;
//import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.ArrayList;
import java.util.LinkedList;

import parameters.INITIAL;
import parameters.Wifi;
/**
 * This is the Explain how to work with the filter: 
 * in some class (like parameters)  run that line --> FilterByTime.filter("2017-10-27 16:16:36", "2017-10-27 16:17:11");
 * that line will sort all elements between the time +1 second s.t the time  2017-10-27 16:16:36 will not collect, only from the time 2017-10-27 16:16:37 will be 
 * inserted to the collection.
 * <<NOTE** that the inserted format is very important , wrong inserted line will be executed to error.>> 
 * (i.e "2017-10-27 16:16:36" <-fine , "2017/10/27 16:16:36" <-WRONG
 * at the end of the Filter you can find all the filtered data in the WifiToKML collection , just use the method getWifiToKML and you get the collection.
 * @author MarkEule.
 *
 */
public class FilterByTime {
	private static FileReader fr;
	private static BufferedReader br;
	private static LinkedList<String> wifiParameters; // tmp used in the function WriteLineToCollection
	private static LinkedList<Wifi> WifiToKML;			// end result to the KML file
	private static LinkedList<String> linesTobeWriten; 	// tmp lines to collect
	/**the filter gets 2 points of time  and return all points that was catched in that period .
	 * 
	 * @MarkGEule
	 */
	public static void filter(String parameter , String parameter2 ){
		try{
			SimpleDateFormat c= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startTime = c.parse(parameter);
			Date endTime = c.parse(parameter2);
			wifiParameters = new LinkedList<String>();
			WifiToKML = new LinkedList<Wifi>();
			linesTobeWriten = new LinkedList<String>();
			fr = new FileReader(INITIAL.getFileWritePath());
			br = new BufferedReader(fr);
			String line = br.readLine();
			line = br.readLine();
//			System.out.println(line);
			boolean flag = true;
			while( line != null && flag ==true){
				String[] lineArr= line.split(",");
				Date currentLineTime = c.parse(lineArr[0]);
				if(currentLineTime.after(startTime) && currentLineTime.before(endTime)){ 
					linesTobeWriten.add(line);
				}
				line = br.readLine();	
			}// end while in 2 cases : 1) br is null , there are no more lines to read, 2) we found the data parameter
			
			for(String a : linesTobeWriten) {
				WriteLineToCollection(a);
				wifiParameters.clear();
			}
			
			

		}catch(Exception e){
			System.out.println("file not found "+ e);
		}

	}
	public static LinkedList<String> getWifiParameters() {
		return wifiParameters;
	}
	public static LinkedList<Wifi> getWifiToKML() {
		return WifiToKML;
	}
	
	private static void WriteLineToCollection(String line) {
		if(line == null){
			System.out.println("There are no such data Was Found By the time ");
		}
		else{
			String[] ans = line.split(",");
			int NumOfWifiInLine = Integer.parseInt(ans[5]);
			wifiParameters.add(ans[0]+","+
					ans[1]+","+
					ans[2]+","+
					ans[3]+","+
					ans[4]+","); // first member : "TIME,ID,LAT,LON,ALT," //TODO : Its should Array and not a substring of 4 letter!
			
			for (int i = 0; i <NumOfWifiInLine*4; i+=4) {
				wifiParameters.add(ans[6+i]+","+ans[7+i]+","+ans[8+i]+","+ans[9+i]);
			}// here's the parameter collection are done,The collection represents like this i.e <"TIME,ID,LAT,LON,ALT," , "wifi#1Parameters" , "wifi#2Parameters" , ..>
			
			//Creating wifi collection for the KML writer.
			
			for (int i = 1; i < wifiParameters.size(); i++) { //"TIME,ID,LAT,LON,ALT,SSID,MAC,Frequency,Signal"
				String tmp = wifiParameters.get(0)+wifiParameters.get(i);
				String[] tmpConstractor = tmp.split(",");
				WifiToKML.add(new Wifi(tmpConstractor, i));
			}
		}
	}
	public static LinkedList<String> getLinesTobeWriten() {
		return linesTobeWriten;
	}
	


}
