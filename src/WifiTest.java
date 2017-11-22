import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class WifiTest {


	@Test
	public void testGetValues() {
		String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
		String [] arr = s.split(",");
		Wifi test = new Wifi(arr);
		boolean flag = true;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] != test.getValues()[i]){
				flag = false;
				break;
			}
		}
		assertEquals(flag, true);
	}


		@Test
		public void testGetMAC() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			flag = (test.getMAC().equals("b4:ee:b4:36:d2:b0") );
			assertEquals(flag, true);
		}
	//
		@Test
		public void testSetMAC() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			test.setMAC("test");
			if(!test.getMAC().equals("test"))
				flag = false;
			assertEquals(flag, true);
		}
	
		@Test
		public void testGetLAT() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			flag = (test.getLAT().equals("32.16857397") );
			assertEquals(flag, true);
		}
	
		@Test
		public void testSetLAT() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			test.setLAT("test");
			if(!test.getLAT().equals("test"))
				flag = false;
			assertEquals(flag, true);
		}
	
		@Test
		public void testGetLON() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			flag = (test.getLON().equals("34.81328609") );
			assertEquals(flag, true);
		}
	
		@Test
		public void testSetLON() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			test.setLON("test");
			if(!test.getLON().equals("test"))
				flag = false;
			assertEquals(flag, true);
		}
	
		@Test
		public void testGetALT() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			flag = (test.getALT().equals("-9") );
			assertEquals(flag, true);
		}
	
		@Test
		public void testSetALT() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			test.setALT("test");
			if(!test.getALT().equals("test"))
				flag = false;
			assertEquals(flag, true);
		}
	
		@Test
		public void testGetSSID() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			flag = (test.getSSID().equals("ben moshe") );
			assertEquals(flag, true);
		}
	
		@Test
		public void testSetSSID() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			test.setMAC("test");
			if(test.getSSID().equals("test"))
				flag = false;
			assertEquals(flag, true);
		}
	
		@Test
		public void testGetCrypt() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			flag = (test.getCrypt().equals("[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS]") );
			assertEquals(flag, true);
		}
	
		@Test
		public void testSetCrypt() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			test.setCrypt("test");
			if(!test.getCrypt().equals("test"))
				flag = false;
			assertEquals(flag, true);
		}
	
		@Test
		public void testGetChannel() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			flag = (test.getChannel().equals("1") );
			assertEquals(flag, true);
		}
	
		@Test
		public void testSetChannel() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			test.setChannel("test");
			if(!test.getChannel().equals("test"))
				flag = false;
			assertEquals(flag, true);
		}
	
		@Test
		public void testGetRSSI() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			flag = (test.getRSSI().equals("-51") );
			assertEquals(flag, true);
		}
	
		@Test
		public void testSetRXL() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			test.setRXL("test");
			if(!test.getRSSI().equals("test"))
				flag = false;
			assertEquals(flag, true);
		}
	
		@Test
		public void testGetTime() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			flag = (test.getTime().equals("27/10/2017 16:12") );
			assertEquals(flag, true);
		}
	
		@Test
		public void testSetTime() {
			String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
			String [] arr = s.split(",");
			Wifi test = new Wifi(arr);
			boolean flag= true;
			test.setTime("test");
			if(!test.getTime().equals("test"))
				flag = false;
			assertEquals(flag, true);
		}
	
}
