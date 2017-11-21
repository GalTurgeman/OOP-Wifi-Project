import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;


public class WriteFile {
	private LinkedList<Wifi> tempOne;
	private FileWriter fw;
	private PrintWriter pw;
	


	/**
	 * 
	 */
	public WriteFile(){
		try{
			fw = new FileWriter(INITIAL.getFileWritePath());
			pw = new PrintWriter(fw);
			

		}catch(Exception e){
			System.out.println(e);
		}
	}

	/**
	 * get the wifiList read from the ReadFile class.
	 * @throws FileNotFoundException 
	 */
	public void ReWriteTheData() throws FileNotFoundException{
		ReadFile rf =new ReadFile();
		System.out.println("Reading file .... ");
		rf.ReadingFile();
		//tempOne = new LinkedList<Wifi>();
		tempOne = rf.getWifiList();
		//		for(Wifi a: rf.getWifiList()){ // TODO : repair
		//			tempOne.add(a);
		//		}
		CollectTheSameWifi(tempOne);
	}

	/**
	 * The Writing Step: first we write the header.
	 * 	 2. we are going to move through the all wifi's that we already collected at the line 53
	 *	 3. we collect all wifis that is the same as wifi at the "i" position
	 *	4. sort step: sort by the Rssi highest value at the top
	 *	5. remove the tail (for example if the client asked only 10 in the row and we have 15 wifi's then we will remove 5 wifi's from the tail.)
	 *	6. print the common data 
	 *	7. print the each wifi data
	 *	8. closing the writing session, the file should be done at that point.
	 * @param list
	 * @throws FileNotFoundException 
	 */
	public void CollectTheSameWifi(LinkedList<Wifi> list) { 
		try{
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
			for ( i = 0 ,j=0; i < list.size()-1; i=j) {
				LinkedList<Wifi> tmp = new LinkedList<Wifi>();
				tmp.add(list.get(i));	//first we what to add the list.i
				//j=i+1;
				while(Check2Wifis(list.get(i), list.get(j)) ){
					//keep write the same wifis
					tmp.add(list.get(j));
					j++;
				}
				
				SortTheWifiListRssi(tmp); //tested , working
				TakeTheNStrongestByRssi(INITIAL.getOneLineWifiCount(),tmp);//tested , working
				pw.println();
				pw.print(tmp.get(0).getTime() + ",");
				pw.print(tmp.get(0).getModel() + ",");
				pw.print(tmp.get(0).getLAT() + ",");
				pw.print(tmp.get(0).getLON() + ",");
				pw.print(tmp.get(0).getALT() + ",");
				pw.print((tmp.size()-1) + ",");

				for(int k = 0 ; k< tmp.size()-1; k++){ // print the data of every wifi
					pw.print(tmp.get(k).getSSID() + " , "+tmp.get(k).getMAC() + " , "+tmp.get(k).getChannel() + " , "+tmp.get(k).getRSSI()+ " , ");
				}
			}
			pw.close();
			fw.close();
		}catch(Exception ex){
			System.out.println("Some problem" + ex);
		}

		System.out.println("done.");	
	}

	/**
	 * purpose: check if the two given wifis is the same in the parameters of :  Time , model, lat, lon , alt.
	 * @param a first wifi
	 * @param b	second wifi
	 * @return True if there is symmetry , false if there is no symmetry.
	 */
	public boolean Check2Wifis(Wifi a, Wifi b){
		if(
				a.getTime().equals(b.getTime())
				//&& a.getModel().equals(b.getModel())
				&& a.getLAT().equals(b.getLAT())
				&& a.getLON().equals(b.getLON())
				&& a.getALT().equals(b.getALT())
				)return true;
		return false;
	}

	/**Sorting : complexity O(n^2) insertion Sort, can be improved by counting sort.
	 * 			Sorting by the Rssi value, The biggest at the head.
	 * @param tmp
	 */
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


	/**
	 * Removing from the collection elements that exceed the given amount per line 
	 * @param elementsInOneLine
	 * @param tmp
	 */
	private void TakeTheNStrongestByRssi(int elementsInOneLine, LinkedList<Wifi> tmp){
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

}



















































