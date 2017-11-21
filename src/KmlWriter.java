import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

import de.micromata.opengis.kml.v_2_2_0.*;

public class KmlWriter {
	
	public void WriteKml(LinkedList<Wifi> list) throws FileNotFoundException{
		Kml k = new Kml();
		k.createAndSetPlacemark()
		   .withName("London, UK").withOpen(Boolean.TRUE)
				.createAndSetPoint().addToCoordinates(-0.126236, 51.500152);
		//marshals to console
		//k.marshal();
		//marshals into file
		k.marshal(new File("finalKml.kml"));
	}

}
