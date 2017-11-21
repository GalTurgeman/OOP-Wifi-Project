import java.io.BufferedReader;
//import java.io.File;
import java.io.FileReader;
//import java.util.ArrayList;
import java.util.LinkedList;

public class FilterByTime {
	private static FileReader fr;
	private static BufferedReader br;
	private static LinkedList<String> wifiParameters;
	private static LinkedList<Wifi> WifiToKML;
	/**
	 * 
	 * @MarkGEule
	 */
	public static void filter(String parameter){
		try{
			fr = new FileReader(INITIAL.getFileWritePath());
			br = new BufferedReader(fr);
			String line = br.readLine();
			boolean flag = true;
			while( line != null && flag ==true){
				String[] lineArr= line.split(",");
				if(lineArr[0].equals(parameter)){
					flag = false;//we found our data and we want to get all wifis that wroted at that line.
					break;
				}
				line = br.readLine();	
			}// end while in 2 cases : 1) br is null , there are no more lines to read, 2) we found the data parameter

			if(line == null){
				System.out.println("There are no such data");
			}
			else{
				String[] ans = line.split(",");
				wifiParameters = new LinkedList<String>();
				int NumOfWifiInLine = Integer.parseInt(ans[5]);
				wifiParameters.add(ans[0]+","+
						ans[1]+","+
						ans[2]+","+
						ans[3]+","+
						ans[4]+","); // first member : "TIME,ID,LAT,LON,ALT," //TODO : Its should Array and not a substring of 4 letter!
				/*int k = 0;
				int l =0;*/
				for (int i = 0; i <NumOfWifiInLine*4; i+=4) {
					wifiParameters.add(ans[6+i]+","+ans[7+i]+","+ans[8+i]+","+ans[9+i]);
				}// here's the parameter collection are done,The collection represents like this i.e <"TIME,ID,LAT,LON,ALT," , "wifi#1Parameters" , "wifi#2Parameters" , ..>
				
				//Creating wifi collection for the KML writer.
				WifiToKML = new LinkedList<Wifi>();
				for (int i = 1; i < wifiParameters.size(); i++) { //"TIME,ID,LAT,LON,ALT,SSID,MAC,Frequency,Signal"
					String tmp = wifiParameters.get(0)+wifiParameters.get(i);
					String[] tmpConstractor = tmp.split(",");
					WifiToKML.add(new Wifi(tmpConstractor, i));
				}
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
	


}
