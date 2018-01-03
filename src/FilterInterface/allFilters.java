package FilterInterface;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

import IO.CreateDB;
import Main.INITIAL;
import Main.Wifi;
import de.micromata.opengis.kml.v_2_2_0.atom.Link;

public class allFilters implements Serializable{
	public  String userStartTime , userEndTime ;
	public   String userDevice;
	public  double userStarLat , userEndLat , userStarLon , userEndLon, userStartAlt , userEndAlt ;
	public transient static LinkedList<Wifi> DBi = new LinkedList<Wifi>();
	boolean save;
	//	private boolean timeFilter;
	//	private boolean deviceFilter;
	//	private boolean locationFilter;

	public transient LinkedList<Wifi> resultColl;

	public allFilters(boolean save,boolean time , boolean device , boolean location , boolean and , boolean or,
			String userStartTime ,String userEndTime ,
			String userDevice,
			String userStarLat ,String userEndLat ,String userStarLon ,String userEndLon,String userStartAlt ,String userEndAlt ) {
		this.userStarLat = Double.parseDouble(userStarLat);
		this.userEndLat = Double.parseDouble(userEndLat);
		this.userStarLon = Double.parseDouble(userStarLon);
		this.userEndLon = Double.parseDouble(userEndLon);
		this.userStartAlt = Double.parseDouble(userStartAlt);
		this.userEndAlt = Double.parseDouble(userEndAlt);
		this.save = save;
		//try {
		//		FileOutputStream fileoutput = new FileOutputStream("/Users/gal/Desktop/1.txt");
		//		ObjectOutputStream out = new ObjectOutputStream(fileoutput);
		//		out.writeObject(filter338);
		//		out.close();
		//		fileoutput.close();
		//		System.err.println("SER ub zibi");
		//	}
		//	catch (IOException i) {
		//		i.printStackTrace();
		//	}
		//}
		if(!save) {
			DBi = CreateDB.FullDB;
		}
		this.userStartTime = userStartTime;
		this.userEndTime = userEndTime;
		this.userDevice = userDevice;
		resultColl = new LinkedList<>();
		if(and) {
			if(time && device) {
				FilterByTime Filtertime = new FilterByTime(userStartTime, userEndTime, DBi, true, null); //TODO : change to
				FilterByDeviceInput Filterdevice = new FilterByDeviceInput(userDevice, Filtertime.getResult(), true, null);
				resultColl = Filterdevice.getResult();
			}

			else if(time && location) {
				FilterByTime Filtertime2 = new FilterByTime(userStartTime, userEndTime, DBi, true, null); //TODO : change to
				FilterByLocation filter_location = new FilterByLocation(this.userStarLat, this.userStarLon, this.userStartAlt, this.userEndLat, this.userEndLon, this.userEndAlt, Filtertime2.getResult(), true, null);
				resultColl = filter_location.get_resultList();
			}

			else if(device && location) {
				FilterByLocation filter_location2 = new FilterByLocation(this.userStarLat, this.userStarLon, this.userStartAlt, this.userEndLat, this.userEndLon, this.userEndAlt, DBi, true, null);
				FilterByDeviceInput Filterdevice2 = new FilterByDeviceInput(userDevice, filter_location2.get_resultList(), true, null);
				resultColl = Filterdevice2.getResult();
			}
		}

		else if(or) {
			if(time || device) {
				FilterByTime FiltertimeOR1 = new FilterByTime(userStartTime, userEndTime, DBi, true, null); //TODO : change to
				FilterByDeviceInput FilterdeviceOR1 = new FilterByDeviceInput(userDevice, DBi, true, null);
				mergerCollection(FiltertimeOR1.getResult(), FilterdeviceOR1.getResult());
			}

			else if(time || location) {
				FilterByTime FiltertimeOR2 = new FilterByTime(userStartTime, userEndTime, DBi, true, null); //TODO : change to
				FilterByLocation filter_locationOR2 = new FilterByLocation(this.userStarLat, this.userStarLon, this.userStartAlt, this.userEndLat, this.userEndLon, this.userEndAlt, DBi, true, null);
				mergerCollection(FiltertimeOR2.getResult(), filter_locationOR2.get_resultList());
			}

			else if(device || location) {
				FilterByLocation filter_locationOR3 = new FilterByLocation(this.userStarLat, this.userStarLon, this.userStartAlt, this.userEndLat, this.userEndLon, this.userEndAlt, DBi, true, null);
				FilterByDeviceInput FilterdeviceOR3 = new FilterByDeviceInput(userDevice, DBi, true, null);
				mergerCollection(filter_locationOR3.get_resultList(), FilterdeviceOR3.getResult());
			}
		}

		else { // only time , device , location
			if(time) {
				FilterByTime FiltertimeALONE = new FilterByTime(userStartTime, userEndTime, DBi, true, null); //TODO : change to
				resultColl = FiltertimeALONE.getResult();
			}
			else if(device) {
				FilterByDeviceInput FilterdeviceALONE = new FilterByDeviceInput(userDevice, DBi, true, null);
				resultColl = FilterdeviceALONE.getResult();
			}
			else if(location) {
				FilterByLocation filter_locationALONE = new FilterByLocation(this.userStarLat, this.userStarLon, this.userStartAlt, this.userEndLat, this.userEndLon, this.userEndAlt , DBi, true, null);
				resultColl = filter_locationALONE.get_resultList();
			}

		}
	}

	private void mergerCollection(LinkedList<Wifi> arr, LinkedList<Wifi> brr){
		for(Wifi a: arr ) {
			resultColl.add(a);
		}
		for(Wifi a: brr ) {
			resultColl.add(a);
		}

	}

}
