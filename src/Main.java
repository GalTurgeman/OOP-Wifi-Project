import java.util.Iterator;
import java.util.LinkedList;

//import de.micromata.opengis.kml.v_2_2_0.Kml;

public class Main {
	public static void main(String[] args) throws Exception {//throws Exception
		WriteFile tt = new WriteFile();
		tt.ReWriteTheData();
		AlgoA a = new AlgoA();
//		KmlWriter k = new KmlWriter(INITIAL.CSVWifisInOrder);
		ReadInputAlgo2 RIA2 = new ReadInputAlgo2("/Users/gal/Downloads/Ex2/data/TS1/_comb_no_gps_ts1.csv");
//		Algo2 b = new Algo2("ec:8c:a2:09:03:38","-77","06:ae:db:3a:64:05","-84","a2:6c:ac:9f:fb:27","-91");
//		System.out.println(b.WifiToSave);
	}
}

