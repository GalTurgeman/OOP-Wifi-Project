import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

import de.micromata.opengis.kml.v_2_2_0.*;

public class KmlWriter {


	/**
	 * This class create Kml file from requested CSV.
	 * Using JAK library.
	 * @param LinkedList
	 * @return 
	 * @throws FileNotFoundException
	 */
	private LinkedList<Wifi> list; 
	/**Constructor for KmlWriter class.
	 * @param list
	 */
	public KmlWriter(LinkedList<Wifi> list,String INI){
		this.list = list;
		KMLWriter(list , INITIAL.getWritePathForKML());
	}
	/**Method that create document with all the points needed.
	 * Get LinkedList.
	 * @param ls
	 */

	public void KMLWriter(LinkedList<Wifi> ls,String WritePath) {
		Kml kml = new Kml();
		Document d = kml.createAndSetDocument().withName("My Locations");
		d.createAndSetTimeSpan().setBegin(ls.getFirst().StringToTime());
		d.createAndSetTimeSpan().setEnd(ls.getLast().StringToTime());
		Placemark p = null;
//			Style myStyle = new Style().withId("my_style");
//			myStyle.withIconStyle(new IconStyle().withIcon(new Icon().withHref("blue-pushpin.png")));
		Icon RedIcon = new Icon().withId("Redicon");
//		icon.setHref("blue-pushpin.png");
		RedIcon.setHref("http://maps.google.com/mapfiles/kml/pushpin/red-pushpin.png");
		Icon YellowIcon = new Icon().withId("Yellowicon");
		YellowIcon.setHref("http://maps.google.com/mapfiles/kml/pushpin/ylw-pushpin.png");
		Icon GreenIcon = new Icon().withId("Yellowicon");
		GreenIcon.setHref("http://maps.google.com/mapfiles/kml/pushpin/grn-pushpin.png");
//		http://maps.google.com/mapfiles/kml/pushpin/red-pushpin.png
		for(int i=0; i<ls.size(); i++)
		{
			if(i==1)System.out.println(ls.get(i).getRssiInINT());
//			p.setStyleUrl("blue
			p = kml.createAndSetPlacemark();
			p.setName(ls.get(i).getSSID());
			p.setDescription("\n "+ls.get(i).ShortToString());
			p.createAndSetTimeSpan().withBegin(ls.get(i).StringToTime()).withEnd(ls.get(i).StringToTime());
			if(ls.get(i).getRssiInINT()< 70){
				p.createAndAddStyle().createAndSetIconStyle().withScale(1.0).withIcon(RedIcon);				
			}
			else if(ls.get(i).getRssiInINT()> 70 && ls.get(i).getRssiInINT()> 85){
				p.createAndAddStyle().createAndSetIconStyle().withScale(1.0).withIcon(YellowIcon);
			}
			else{
				p.createAndAddStyle().createAndSetIconStyle().withScale(1.0).withIcon(GreenIcon);
			}

			p.createAndSetPoint()
			.addToCoordinates(Double.parseDouble(ls.get(i).getLON()), Double.parseDouble(ls.get(i).getLAT()), Double.parseDouble(ls.get(i).getALT()));
			d.addToFeature(p);
		}
		//marshals to console
		kml.setFeature(d);
//		kml.marshal();
		//marshals into file
		try {
			kml.marshal(new File(WritePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}	
}
