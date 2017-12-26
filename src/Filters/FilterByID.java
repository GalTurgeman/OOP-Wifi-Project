package Filters;


import java.util.LinkedList;

import IO.ReadFile;
import Main.Wifi;
/**
This function is filtering the CSV file by the given model name.
after the filtering you can use @return: nothing returns, ListToKML list , contains the filtered elements.
*/
public class FilterByID{
	private LinkedList<Wifi> ListToKML;
	public FilterByID(String ModelName){
		ReadFile fr = new ReadFile();
		fr.ReadingFile();
		 ListToKML = new LinkedList<Wifi>();
		for(Wifi a : fr.getWifiList()){
			if(a.getModel().equals(ModelName))ListToKML.add(a);
		}
	}
	public LinkedList<Wifi> getListToKML() {
		return ListToKML;
	}	
}
