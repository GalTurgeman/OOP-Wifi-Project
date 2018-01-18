package FilterInterface;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import parameters.Wifi;

public class FilterByTime implements Filter, Serializable{
	
	private String timeFormat ="yyyy-MM-dd HH:mm:ss";
	private LinkedList<Wifi> resultCollection;
//	private LinkedList<Wifi> cloneList;
	private Date dateMin;
	private Date dateMax;
	boolean checkIt;
	
	public FilterByTime(String timeStart, String timeEnd ,LinkedList<Wifi> inputCollection, boolean flag ,LinkedList<Wifi> otherAlreadyFiltered) {
		this.checkIt = flag;
		SimpleDateFormat formatD = new SimpleDateFormat(this.timeFormat);
		 try {
			this.dateMin = formatD.parse(timeStart);
			this.dateMax = formatD.parse(timeEnd);
		} catch (ParseException e) {
			System.err.println("The input time String was wrong ... please try this format : yyyy-MM-dd HH:mm:ss");
			e.printStackTrace();
		}
		this.resultCollection = new LinkedList<>();
		for(Wifi a : inputCollection) a.setTimeAsDate();
		runFilter(inputCollection);
		if(otherAlreadyFiltered != null) { // filter again by the that collection
			runSecondStageFilter(otherAlreadyFiltered);
		}
	}
	
	private void runSecondStageFilter(LinkedList<Wifi> otherAlreadyFiltered) {
		if(checkIt) {
			for(Wifi a: otherAlreadyFiltered) 
				if(passes(a) && !resultCollection.contains(a))this.resultCollection.add(a);
		}else {
			for(Wifi a : otherAlreadyFiltered)
				if(!passes(a) && !resultCollection.contains(a))this.resultCollection.add(a);
		}
	}

	private void runFilter(LinkedList<Wifi> input) {
		if(checkIt) {
			for(Wifi a : input) 
				if(passes(a))this.resultCollection.add(a);
		}else {
			for(Wifi a : input)
				if(!passes(a))this.resultCollection.add(a);
		}
	}

	@Override
	public boolean passes(Wifi a) {
		if ( a.getTimeDate().after(dateMin)  && a.getTimeDate().before(dateMax) || a.getTimeDate().compareTo(dateMax)==0 ||a.getTimeDate().compareTo(dateMin)==0 )return true;
		return false;
	}
	
	public LinkedList<Wifi> getResult(){
		return this.resultCollection;
	}

}
