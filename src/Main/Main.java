package Main;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.text.DocumentFilter.FilterBypass;


import Algo.AlgoA;
import Algo.ReadWriteInputAlgo2;
import FiltersByInterface.FilterByDeviceInput;
import FiltersByInterface.FilterByLocation;
import FiltersByInterface.FilterByTime;
import IO.WriteFile;

//import de.micromata.opengis.kml.v_2_2_0.Kml;

public class Main {
	public static void main(String[] args) throws IOException  {//throws Exception
		WriteFile tt = new WriteFile();
		tt.ReWriteTheData();
//		System.out.println(INITIAL.CSVWifisInOrder);
//		System.out.println(INITIAL.CSVWifisInOrder.toString());
//		System.out.println(INITIAL.CSVWifisInOrder.get(0).getWeightAlgoA());
//		
	//	AlgoA a = new AlgoA(3);
	//	ReadWriteInputAlgo2 RIA2 = new ReadWriteInputAlgo2(INITIAL.getReadPathForAlgoBInput(),3);
		
//		KmlWriter k = new KmlWriter(INITIAL.CSVWifisInOrder);
		
//		FilterByLocation fBL = new FilterByLocation(1, 1, 1, 100, 100, 100, INITIAL.CSVWifisInOrder);
		
		
		
		//TODO: use that flag to filter it correctly 
		FilterByDeviceInput fbs = new FilterByDeviceInput("02:8d:db:6e:71:c1", INITIAL.CSVWifisInOrder,false);
		System.out.println(fbs.getResult().getFirst().getTime());
		System.out.println(fbs.getResult().size());
		System.out.println("finish");
//		for(Wifi a : fbs.getResult()) a.setTimeAsDate();
//		fbs.getResult().getFirst().setTimeAsDate();
//		System.out.println(fbs.getResult().getFirst().getTimeDate());
//		YYYY-MM-dd HH:MM:SS

	//	System.out.println(fBL.get_resultList());
		
		
		/// time testing ////
		
		
		FilterByTime fbt = new FilterByTime("2017-10-27  16:16:40", "2017-10-27 16:16:46", INITIAL.CSVWifisInOrder, true);
		System.out.println(fbt.getResult());
		
	}

	
}

