package FiltersByInterface;

import java.util.LinkedList;

import Main.Wifi;

public class FilterByDeviceInput implements FilterB{
	private LinkedList<Wifi> resultCollection;
	private LinkedList<Wifi> cloneList;
	boolean checkIt;
	String input;
	
	 public FilterByDeviceInput(String input, LinkedList<Wifi> inputCollection, boolean flag) {
		this.input = input;
		resultCollection = new LinkedList<Wifi>();
		checkIt = flag; //TODO: use that flag to filter it correctly 
		cloneList = new LinkedList<>();
		for(Wifi a : inputCollection) {
			cloneList.add(a);
		}
		runFilter();
		
		
	}
	
	 private void runFilter() {
		 if(checkIt) { // if the checkIt == true , then we want to filter the collection by that input.
			 for(int i =0 ; i< cloneList.size() ; i++) {
				 if(passes(cloneList.get(i)))resultCollection.add(cloneList.get(i));
			 }
		 }else { // if the checkIt == false , then we want to fillter the collection by all values except that input.
			for(int i =0 ; i< cloneList.size() ; i++) {
				 if(!passes(cloneList.get(i)))resultCollection.add(cloneList.get(i));
			}
		 }
	 }
	
	@Override
	public boolean passes(Object a) {
		if( ((Wifi)a).getModel().equals(this.input))return true;
		return false;
	}
	public LinkedList<Wifi> getResult(){
		return this.resultCollection;
	}
}
