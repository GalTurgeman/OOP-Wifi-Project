import java.awt.geom.Point2D;

public class Wifi {

	private String MAC, LAT, LON, SSID, ALT, Crypt;
	private String Type; // Wifi or GSM
	private String Channel, RSSI, Time;
	private String model;
	private double WeightAlgoA;
	private double WeightAlgoB;
	private double wLAT,wLON,wALT;//weight lat,lon,alt; gal.
	private boolean Algo_A_Test=false;

	/*
	 * Constructors
	 */
	/**
	 * @param MAC
	 * @param SSID
	 * @param Crypt
	 * @param Time
	 * @param Channel
	 * @param RSSI
	 * @param LAT
	 * @param LON
	 * @param ALT
	 * @param Type
	 * @param model
	 */
	public Wifi(String MAC,String SSID, String Crypt, String Time, String Channel, String RSSI, String LAT,String LON,String ALT, String Type, String model){
		this.MAC = MAC;
		this.SSID = SSID;
		this.Crypt = Crypt;
		this.Time = Time;
		this.Channel = Channel;
		this.RSSI = RSSI;
		this.LAT = LAT;
		this.LON = LON;
		this.ALT = ALT;
		this.Type = Type;
		this.model = model;
		this.WeightAlgoA = 1/(Math.pow(this.getRSSIAsNum(), 2));
	}
	public Wifi(double [] point , String SSID , String MAC , String Time,String RSSI){
		 this.LAT =  ""+point[0];
		 this.LON = ""+point[1];
		 this.ALT = ""+point[2];
		 this.SSID = SSID;
		 this.MAC = MAC;
		 this.Time = Time;
		 this.RSSI = RSSI;
	}
	/**
	 * @param arr
	 */
	public Wifi(String[] arr){
		this.MAC = arr[0];
		this.SSID = arr[1];
		this.Crypt = arr[2];
		this.Time = arr[3];
		this.Channel = arr[4];
		this.RSSI = arr[5];
		this.LAT = arr[6];
		this.LON = arr[7];
		this.ALT = arr[8];
		this.Type = arr[9];
		this.model = arr[10];
		this.WeightAlgoA = 1/(Math.pow(this.getRSSIAsNum(), 2));
	}

	/**
	 * 
	 */
	/**
	 -	 * Constructor for the KML file
	 -	 * the int i is never used, he is there to recognize between the two constracturs. 
	 -	 * @param TIME
	 -	 * @param ID
	 -	 * @param LAT
	 -	 * @param LON
	 -	 * @param ALT
	 -	 * @param SSID
	 -	 * @param MAC
	 -	 * @param Frequency
	 -	 * @param Signal
	 -	 */
	public Wifi(String[] toKML, int i){
		this.Time =toKML[0]; 
		this.model =toKML[1];
		this.LAT =toKML[2];
		this.LON =toKML[3];
		this.ALT =toKML[4];
		this.SSID =toKML[5];
		this.MAC =toKML[6];
		this.Channel =toKML[7];
		this.RSSI =toKML[8];
	}

	public Wifi(){
		this.MAC = "";
		this.SSID = "";
		this.Crypt = "";
		this.Time = "";
		this.Channel = "";
		this.RSSI = "";
		this.LAT = "";
		this.LON = "";
		this.ALT = "";
		this.Type = "";
		this.model = "";
		
	}
	

	/**
	 * @return
	 */
	public String[] getValues (){
		String[] values = new String[11];
		values[0] = this.MAC;
		values[1] = this.SSID;
		values[2] = this.Crypt;
		values[3] = this.Time;
		values[4] = this.Channel;
		values[5] = this.RSSI;
		values[6] = this.LAT;
		values[7] = this.LON;
		values[8] = this.ALT;
		values[9] = this.Type;
		values[10] = this.model;
		return values;	
	}


	/**
	 * @return
	 */
	public Wifi get(){
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
//	public String toString() {
//		return "Wifi [MAC=" + MAC + ", SSID=" + SSID + ", Crypt=" + Crypt + ", Time=" + Time  
//				+ ", Channel=" + Channel +  ", RSSI=" + RSSI + ", LAT=" + LAT + ", LON=" + LON +  
//				", ALT=" + ALT + ", Model: "+ model +"]";
//	}
	public String toString(){
		return "Wifi [MAC=" + MAC + ", SSID=" + SSID + ", Time=" + Time+"]";
	}
	/**@author gal
	 * Print Only the needed details for google earth view.
	 * @return SSID MAC Frequency Signal.
	 */
	public String ShortToString(){
		//SSID1	MAC1	Frequency1	Signal1 
		return "\nWifi SSID: " +SSID +" \nMAC: " + MAC + " \nFreq: " +Channel + "\nSignal: " +RSSI;
	}
	public String getMAC() {
		return MAC;
	}

	/**
	 * @param MAC
	 */
	public void setMAC(String MAC) {
		this.MAC = MAC;
	}

	/**
	 * @return
	 */
	public String getLAT() {
		return LAT;
	}

	/**
	 * @param lAT
	 */
	public void setLAT(String lAT) {
		this.LAT = lAT;
	}

	/**
	 * @return
	 */
	public String getLON() {
		return LON;
	}

	/**
	 * @param lON
	 */
	public void setLON(String lON) {
		this.LON = lON;
	}

	/**
	 * @return
	 */
	public String getALT() {
		return ALT;
	}

	/**
	 * @param alt
	 */
	public void setALT(String alt) {
		this.ALT = alt;
	}

	/**
	 * @return
	 */
	public String getSSID() {
		return SSID;
	}

	/**
	 * @param sSID
	 */
	public void setSSID(String sSID) {
		this.SSID = sSID;
	}

	/**
	 * @return
	 */
	public String getCrypt() {
		return Crypt;
	}

	/**
	 * @param crypt
	 */
	public void setCrypt(String crypt) {
		this.Crypt = crypt;
	}

	/**
	 * @return
	 */
	public String getType() {
		return Type;
	}

	/**
	 * @return
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @param type
	 */
	public void setConnection_Mode(String type) {
		this.Type = type;
	}

	/**
	 * @return
	 */
	public String getChannel() {
		return Channel;
	}

	/**
	 * @param channel
	 */
	public void setChannel(String channel) {
		this.Channel = channel;
	}

	/**
	 * @return
	 */
	public String getRSSI() {
		return RSSI;
	}

	/**
	 * @param rssi
	 */
	public void setRXL(String rssi) {
		this.RSSI = rssi;
	}

	/**
	 * @return
	 */
	public String getTime() {
		return Time;
	}

	/**
	 * @param time
	 */
	public void setTime(String time) {
		this.Time = time;
	}

	/**
	 * @return
	 */
	public int getRssiInINT(){// TODO rename to getRssiInt, insert without spaces at readfile
		if(RSSI.contains(""+' ')){
			RSSI =	RSSI.replaceAll("\\s+", "");
		}
		int ans = Integer.parseInt(RSSI)*(-1);
		return ans;
	}
	public String StringToTime(){
		String ans = this.Time.replace(' ', 'T');
		return ans + "Z";
	}
	/**Will create a 2D Point and return it.
	 * using Point2D from:
	 * java.awt.geom.Point2D;
	 * 
	 * @author gal
	 */
	public Point2D Get_PubilcGeo(String LAT, String LON){
		Point2D P = null;
		P.setLocation(Double.parseDouble(LAT), Double.parseDouble(LON));
		return P;
	}
	/**Will convert LAT as string to double.
	 * 
	 * @author gal
	 */
	public double getLAT_double(){// TODO rename to getRssiInt, insert without spaces at readfile
		if(LAT.contains(""+' ')){
			LAT = LAT.replaceAll("\\s+", "");
		}
		double ans = Double.parseDouble(LAT);
		return ans;
	}
	/**Will convert LON as string to double.
	 * 
	 * @author gal
	 */
	public double getLON_double(){// TODO rename to getRssiInt, insert without spaces at readfile
		if(LON.contains(""+' ')){
			LON = LON.replaceAll("\\s+", "");
		}
		double ans = Double.parseDouble(LON);
		return ans;
	}
	/**Will convert ALT as string to double.
	 * 
	 * @author gal
	 */
	public double getALT_double(){// TODO rename to getRssiInt, insert without spaces at readfile
		if(ALT.contains(""+' ')){
			ALT = ALT.replaceAll("\\s+", "");
		}
		double ans = Double.parseDouble(ALT);
		return ans;
	}
	/**Will set in wifi object weight LAT.
	 * Using this formula:
	 * w*getLAT_double;
	 * @author gal
	 */
	public void Set_wLAT(double w){
		this.wLAT = w*getLAT_double();
	}
	/**Will set in wifi object weight LON.
	 * Using this formula:
	 * w*getLON_double;
	 * @author gal
	 */
	public void Set_wLON(double w){
		this.wLON = w*getLON_double();
	}
	/**Will set in wifi object weight ALT.
	 * Using this formula:
	 * w*getALT_double;
	 * @author gal
	 */
	public void Set_wALT(double w){
		this.wALT = w*getALT_double();
	}
	public double getWeightAlgoA(){
		return 1/Math.pow(this.getRSSIAsNum(), 2);
	}
	public void SetWeightAlgoA(){
		this.WeightAlgoA = 1/(Math.pow(this.getRSSIAsNum(), 2));
	}
	public double get_wALT(){
		return this.wALT;
	}
	public double get_wLAT(){
		return this.wLAT;
	}
	public double get_wLON(){
		return this.wLON;
	}
	public double getRSSIAsNum(){
		return Double.parseDouble(RSSI);
	}

	public double getWeightAlgoB() {
		return WeightAlgoB;
	}

	public void setWeightAlgoB(double diff) {
		this.WeightAlgoB=Parameters.norm/(Math.pow(diff, Parameters.sigDiff)*Math.pow(getRSSIAsNum(), Parameters.power) );
	}

	public boolean getIsCheked() {
		return Algo_A_Test;
	}

	public void setIsChecked(boolean algo_A_Test) {
		Algo_A_Test = algo_A_Test;
	}
}





