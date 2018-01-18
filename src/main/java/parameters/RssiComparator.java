package parameters;
import java.util.Comparator;

public class RssiComparator implements Comparator<Wifi>{
	@Override
	public int compare(Wifi o1, Wifi o2){
		int w1Rssi = Integer.parseInt(o1.getRSSI())*(-1);
		int w2Rssi = Integer.parseInt(o2.getRSSI())*(-1);
		if(w1Rssi == w2Rssi)return 0;
		else if(w1Rssi > w2Rssi)return 1;
		else return -1;
	}
}
