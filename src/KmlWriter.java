import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

import de.micromata.opengis.kml.v_2_2_0.*;

public class KmlWriter {


	/**
	 * @param LinkedList
	 * @return 
	 * @throws FileNotFoundException
	 */
	private LinkedList<Wifi> list;

	public KmlWriter(LinkedList<Wifi> list){
		this.list = list;
		KMLWriter(list);
	}
	public void KMLWriter(LinkedList<Wifi> ls) {
		Kml kml = new Kml();
		Document d = kml.createAndSetDocument();
		for(int i=0; i<ls.size(); i++)
		{
			d.createAndSetTimeSpan();
			d.createAndAddPlacemark()
			.withName(ls.get(i).getSSID())
			.withDescription("\n "+ls.get(i).ShortToString())
			.createAndSetPoint().addToCoordinates(
					Double.parseDouble(ls.get(i).getLON()),
					Double.parseDouble(ls.get(i).getLAT()), 
					Double.parseDouble(ls.get(i).getALT())
					);
		}
		//marshals to console
		kml.marshal();
		//marshals into file
		try {
			kml.marshal(new File("/Users/gal/Desktop/Kml.kml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}	
}
