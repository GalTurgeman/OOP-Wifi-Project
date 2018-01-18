package parameters;

import java.util.Comparator;

public class SortByTimeComparator implements Comparator<Wifi>{
	@Override
	public int compare(Wifi o1, Wifi o2) {
		if(o1.getTime().equals(o2.getTime()))
			return 1;
		else if(o2.getTime().equals(o1.getTime()))
			return -1;
		return 0;
	}
}
