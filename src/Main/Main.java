package Main;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import Algo.AlgoA;
import Algo.ReadWriteInputAlgo2;
import IO.WriteFile;

//import de.micromata.opengis.kml.v_2_2_0.Kml;

public class Main {
	public static void main(String[] args) throws IOException   {//throws Exception
		WriteFile tt = new WriteFile();
		tt.ReWriteTheData();
//		
		AlgoA a = new AlgoA(3);
		ReadWriteInputAlgo2 RIA2 = new ReadWriteInputAlgo2(INITIAL.getReadPathForAlgoBInput(),3);
		
//		KmlWriter k = new KmlWriter(INITIAL.CSVWifisInOrder);
		
	}
}

