import java.io.File;
import java.util.LinkedList;


public class INITIAL {
	/**
	 * ReadPath = the Root / file.csv
	 * WritePath = the path to write file.
	 * 
	 */
	
	private static String ReadPath = "toRead";
	private static String WritePath = "toWrite//File19112017.csv";
	private static String suffix = ".csv";
	private static String csvHeader = "MAC,SSID,AuthMode,FirstSeen,Channel,RSSI,CurrentLatitude,CurrentLongitude,AltitudeMeters,AccuracyMeters,Type";
	private static String modelName = "model=";
	private static String csvClientHeader = "TIME,ID,LAT,LON,ALT,Number Of Networks";
	private static String csvClientWifiParameters = "SSID,MAC,Frequency,Signal";
	
	private static File fileReadPath = new File(ReadPath);
	public static File fileWritePath = new File(WritePath);
	
	private static int OneLineWifiCount = 10;

	
	public static String getCsvHeader() {
		return csvHeader;
	}
	public static void setCsvHeader(String csvHeader) {
		INITIAL.csvHeader = csvHeader;
	}
	
	public static File getFileReadPath() {
		return fileReadPath;
	}
	public static File getFileWritePath() {
		return fileWritePath;
	}
	public static void setFileReadPath(File fileReadPath) {
		INITIAL.fileReadPath = fileReadPath;
	}
	public static void setFileWritePath(File fileWritePath) {
		INITIAL.fileWritePath = fileWritePath;
	}
	
	
	public static String getSuffix() {
		return suffix;
	}
	public static void setSuffix(String suffix) {
		INITIAL.suffix = suffix;
	}
	public static String getReadPath() {
		return ReadPath;
	}
	public static String getWritePath() {
		return WritePath;
	}
	public static void setReadPath(String readPath) {
		ReadPath = readPath;
	}
	public static void setWritePath(String writePath) {
		WritePath = writePath;
	}
	public static String getModelName() {
		return modelName;
	}
	public static void setModelName(String modelName) {
		INITIAL.modelName = modelName;
	}
	public static int getOneLineWifiCount() {
		return OneLineWifiCount;
	}
	public static void setOneLineWifiCount(int oneLineWifiCount) {
		OneLineWifiCount = oneLineWifiCount;
	}
	public static String getCsvClientHeader() {
		return csvClientHeader;
	}
	public static void setCsvClientHeader(String csvClientHeader) {
		INITIAL.csvClientHeader = csvClientHeader;
	}
	public static String getCsvClientWifiParameters() {
		return csvClientWifiParameters;
	}
	public static void setCsvClientWifiParameters(String csvClientWifiParameters) {
		INITIAL.csvClientWifiParameters = csvClientWifiParameters;
	}
	
}
