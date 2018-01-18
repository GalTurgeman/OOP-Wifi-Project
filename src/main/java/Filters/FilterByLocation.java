package Filters;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

import parameters.INITIAL;
import parameters.Wifi;

public class FilterByLocation implements Filter{
	/**NOTE* : Use this class only on Complete SCV file aka the file that are sorted to N members in one line
	 * This class filtering the data sheet by given by the use the LAT , LON  and radius 
	 *@return : after filter(double LAT, double LON, double radius) you can get the LinkedList<Wifi> WifiToKML that full with the 
	 *filterd points.	
	 */

	private static FileReader fr;
	private static BufferedReader br;
	private static LinkedList<String> wifiParameters;
	private static LinkedList<Wifi> WifiToKML;
	/**
	 * 
	 * @MarkGEule
	 */
	public static void filter(double lat, double lon, double radius){
		try {
			WifiToKML = new LinkedList<Wifi>();
			//user input - in the INITIAL.java
			//program process... s.t reading lines & finding the right data.
			fr = new FileReader(INITIAL.getWritePath()) ; //FileReader(INITIAL.getFileWritePath())
			br = new BufferedReader(fr);
			String line = br.readLine();
			line = br.readLine(); // Jumps over the header , stright to the wifi's
			while( line != null){ // && flag ==true
				String[] lineArr= line.split(","); // set the readied line to array.
				double Xpoint = Double.parseDouble(lineArr[2]); // Wifi's Parameters from the data sheets (X,Y)
				double Ypoint = Double.parseDouble(lineArr[3]);
				if(check_Point_In_The_Radius(lat,lon,Xpoint,Ypoint,radius)) {
					insert_Line_asWifi(lineArr);
				}
				line = br.readLine();	
			}// end while in 2 cases : 1) br is null , there are no more lines to read, 2) we found the data parameter
		}catch(Exception e) {
			System.out.println("file not found "+ e);
		}
	}
	
	
	public static LinkedList<String> getWifiParameters() {
		return wifiParameters;
	}
	
	
	public static LinkedList<Wifi> getWifiToKML() {
		return WifiToKML;
	}

	
	private static boolean check_Point_In_The_Radius(double inputX,double inputY,double Xpoint,double Ypoint,double RadiusInTheCube) {
		double ans1 = ( Math.pow((inputX - Xpoint), 2) + Math.pow((inputY - Ypoint), 2));
		boolean flag = false;
		if(ans1 <= RadiusInTheCube)flag= true;
		
		 return flag;
	}
	
	
	private static void insert_Line_asWifi(String[] lineArr) {
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
			WifiToKML.add(new Wifi(tmpConstractor, i)); //adding the wifi by uniqe constractor.
		}
	}
	@Override
	public void filter() {
		//calls the filter by location
		filter(INITIAL.getInputLAT(), INITIAL.getInputLON() ,INITIAL.getInputRadius_Cube()); 
	}

}