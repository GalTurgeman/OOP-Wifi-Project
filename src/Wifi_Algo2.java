
public class Wifi_Algo2 extends Wifi implements Comparable<Wifi_Algo2>{

	private double diff,w,pi;

	public Wifi_Algo2(double diff, double w, double pi) {
		super();
		this.diff = diff;
		this.w = w;
		this.pi = pi;
	}
	public void SetWifi(Wifi w){
		this.setMAC(w.getMAC());
		this.setSSID(w.getSSID());
		this.setTime(w.getTime());
		this.setChannel(w.getChannel());
		this.setRXL(w.getRSSI());
		this.setLAT(w.getLAT());
		this.setLON(w.getLON());
		this.setALT(w.getALT());
	}
	public Wifi_Algo2(double diff, double w) {
		super();
		this.diff = diff;
		this.w = w;
	}
	public double getDiff() {
		return diff;
	}

	public void setDiff(double diff) {
		this.diff = diff;
	}

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}

	public double getPi() {
		return pi;
	}

	public void setPi(double pi) {
		this.pi = pi;
	}
	@Override
	/**
	 * return 1 if (wifi w1,wifi w2) w1 pi is larger than w2;
	 * reutnr 0 if w2 is larger than w1;
	 */
	public int compareTo(Wifi_Algo2 o) {
		if(this.getPi() > o.getPi())
			return 1;
		return 0;
	}

}
