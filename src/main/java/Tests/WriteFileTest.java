package Tests;
import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import main.java.WriteFile;
import parameters.Wifi;

public class WriteFileTest {

	@Test
	public void test() {
		LinkedList<Wifi> list = new LinkedList<>();
		String s = "b4:ee:b4:36:d2:b0,ben moshe,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],27/10/2017 16:12,1,-51,32.16857397,34.81328609,-9,8,WIFI";
		String [] a = s.split(",");
		boolean flag = false;
		Wifi w1 = new Wifi(a);
		WriteFile wf = new WriteFile();
		flag = wf.Check2Wifis(w1, w1);
		assertEquals(flag, true);
	}

}
