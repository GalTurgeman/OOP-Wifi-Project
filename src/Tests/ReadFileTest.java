package Tests;
import static org.junit.Assert.*;

import org.junit.Test;

import Main.Wifi;

public class ReadFileTest {

	@Test
	public void testSetWifiModel() {
		String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
		String [] arr = s.split(",");
		Wifi test = new Wifi(arr);
		boolean flag= true;
		test.setModel("test");
		if(!test.getModel().equals("test"))
			flag = false;
		assertEquals(flag, true);
	}

}
