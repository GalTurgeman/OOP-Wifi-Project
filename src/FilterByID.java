

import java.util.LinkedList;

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
