public class Wifi {

	private String MAC, LAT, LON, SSID, ALT, Crypt;
	private String Type; // Wifi or GSM
	private String Channel, RSSI, Time;
	private String model;

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
	}
	
	/**
	 * Constructor for the KML file
	 * the int i is never used, he is there to recognize between the two constracturs. 
	 * @param TIME
	 * @param ID
	 * @param LAT
	 * @param LON
	 * @param ALT
	 * @param SSID
	 * @param MAC
	 * @param Frequency
	 * @param Signal
	 */
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

	/**
	 * 
	 */
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
	public String toString() {
		return "Wifi [MAC=" + MAC + ", SSID=" + SSID + ", Crypt=" + Crypt + ", Time=" + Time  
				+ ", Channel=" + Channel +  ", RSSI=" + RSSI + ", LAT=" + LAT + ", LON=" + LON +  
				", ALT=" + ALT + ", Model: "+ model +"]";
	}
	/**
	 * @return
	 */
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
	public int getRssiInINT(){// TODO rename to getRssiInt
		int ans = Integer.parseInt(RSSI)*(-1);
		return ans;
	}
//	
//	public String[] rearrangeForCsv(){
//		String[] arr = new String[10];
//		
//		arr[6] = this.MAC;
//		arr[5] = this.SSID;
//		arr[2] = this.Crypt;
//		arr[0] = this.Time;
//		arr[7] = this.Channel;
//		arr[8] = this.RSSI;
//		arr[2] = this.LAT;
//		arr[3] = this.LON;
//		arr[4] = this.ALT;
//		arr[5] = this.Type;
//		return arr;
//	}
	

	

	
}





