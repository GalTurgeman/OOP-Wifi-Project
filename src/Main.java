
public class Main {
	public static void main(String[] args) throws Exception {//throws Exception
		/*WriteFile tt = new WriteFile();
		tt.ReWriteTheData();*/
		
		System.out.println("2017-10-27 16:35:31");
		FilterByTime.filter("2017-10-27 16:35:31");
		System.out.println(FilterByTime.getWifiToKML());
		
		
		
		System.out.println("2017-10-27 16:37:41");
		FilterByTime.filter("2017-10-27 16:37:41");
		System.out.println(FilterByTime.getWifiToKML());
	}
}

