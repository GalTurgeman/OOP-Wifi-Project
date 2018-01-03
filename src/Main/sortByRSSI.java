package Main;
import java.util.Comparator;

public class sortByRSSI implements Comparator<Wifi> {

	@Override
	public int compare(Wifi w1, Wifi w2) {
		if(w1.getRssiInINT() > w2.getRssiInINT())return 1;
		else if(w1.getRssiInINT() < w2.getRssiInINT())return -1;
		else return 0;
	}

}
