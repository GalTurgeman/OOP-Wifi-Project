package IO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import Main.INITIAL;
import Main.Wifi;

public class ReadFile {


	/**This class can read Folder, files and deal with unreadable files .
	 * its read the all folders tree recursivley , at the end we got 2 collection one for the csv files and the other
	 * for the Wifi members.
	 * To deal with reading file we need first to deal with
	 *  1. figure out if the file is a file nor its a folder.
	 *  2. collect all csv
	 *  3. collect all good files. (.csv) (kind of filter)
	 *  4. is the file is fine (its with the wifi info) then we will read it and creat a collection of Wifi networks
	 *  
	 */

	private ArrayList<File> csvFiles;
	private LinkedList<Wifi> WifiList;
	private FileReader fr;
	private BufferedReader br;

	/**
	 * Constractor
	 * @author MarkG
	 */
	public ReadFile(){
		this.csvFiles = new ArrayList<File>();
		this.WifiList = new LinkedList<Wifi>();

	}

	/**		the Main function of the ReadFile that make in action the class.
	 * @param
	 */
	public void ReadingFile(){
		readTheFolder(INITIAL.getFileReadPath()); // Collecting all .csv Files  to csvFiles [ task 2 ] 
		System.out.println("Files to read:  \n"+ csvFiles+"\n");	
		SelectFineCSV(csvFiles); 				  // collection : from the csvFiles the good one to <collection of Wifi> [ task 3 ] 
		Build_Wifi_Collection(csvFiles); 		  // Collection : building wifi collection from all csv lists. LinkedList<Wifi> [ tast 4 ]  

	}


	/**
	 * function that reads recursivly the files/folders and adding all .csv to one collection.
	 * @param path
	 */
	private  void readTheFolder(File path){
		File[] arr = path.listFiles();
		for( File a : arr){
			if(a.isDirectory()){
				readTheFolder(a);
			}
			else{
				if(a.getName().contains(INITIAL.getSuffix())){
					csvFiles.add(a);
				}
			}
		}


	}

	/**
	 * Collecting only the Fine* CSV files , 
	 * Fine* = File that have the regular csv file header & the file is readable.
	 * @param csvFiles
	 */
	private void SelectFineCSV(ArrayList<File> csvFiles){
		ArrayList<File> tmparr = new ArrayList<File>();

		for(File a : csvFiles){
			try{
				fr =new FileReader(a);
				br = new BufferedReader(fr);
				br.readLine();
				String str= br.readLine();
				if(str.equals(INITIAL.getCsvHeader()) && a.canRead() ){ // TODO is im really need the a.canRead()??
					tmparr.add(a);
				}
			}catch(Exception e){
				System.out.println(e);
			}
		}
		csvFiles = tmparr;
	}

	/**
	 * The function that return the Model name of the searched device i.g Lenovo, Android , Asus etc.
	 * @param line
	 * @return
	 */
	public String SetWifiModel(String[] line){
		String ansName = "";
		for(String a : line){
			if(a.contains(INITIAL.getModelName())){
				ansName = a.substring(6, a.length());
				break;
			}
		}
		return ansName;
	}


	/**
	 * Collecting all Wifis from all Csv files.
	 * @param csvFiles
	 * @throws Exeption (working with file reading)
	 */
	private void Build_Wifi_Collection(ArrayList<File> csvFiles){
		for(File a : csvFiles){
			try{
				String tmpModelName = "";
				fr = new FileReader(a);
				br = new BufferedReader(fr);
				String str = br.readLine();
				String[] tmpFirstLine = str.split(",");
				tmpModelName = SetWifiModel(tmpFirstLine);
				str = br.readLine(); //TODO BUG : With that line (active) there is "java.lang.NullPointerException" , but all wifis count , Without that line(deactive) =no exception but The program pass one wifi.
				str = br.readLine();
				if(CheckIfGSM(str)) {
					str = br.readLine();
				}
				//								while((str =br.readLine() )!= null){
				while((str != null)){
					while(CheckIfGSM(str)) {
						str = br.readLine();
					}
					String[] tmp = str.split(",");
					tmp[10] = tmpModelName; 	//TODO check is every wifi get the model name. -tested ,  working fine @markG
					WifiList.add(new Wifi(tmp));
					str = br.readLine();
					//					System.out.println(WifiList.getFirst().getSSID());
				}

			}catch(IOException e){
				System.out.println(e);
			}
		}
	}
	/**
	 * CheckIFGSM checks if it is Wifi or GSM.
	 * return true if it is.
	 * return false if it is not.
	 * @param string
	 * @return boolean
	 */
	public boolean CheckIfGSM(String str) {
		String [] temp = str.split(",");
		if(!str.isEmpty())
			if(temp[temp.length-1].equals("GSM"))
				return true;
		return false;
	}

	/**
	 * @return TheWifiList  - all wifi's from all Csv files.
	 */
	public LinkedList<Wifi> getWifiList() {
		return WifiList;
	}

}





































