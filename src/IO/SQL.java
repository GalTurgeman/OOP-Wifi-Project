package IO;

import java.sql.*;
import java.util.logging.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.timer.Timer;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.transform.OutputKeys;
import Main.SortByTimeComparator;
import Main.Wifi;


public class SQL {

	private static String _ip = "5.29.193.52";
	private static String _url = "jdbc:mysql://"+_ip+":3306/oop_course_ariel";
	private static String _user = "oop1";
	private static String _password = "Lambda1();";

	private static Connection _con;
	private static Statement st;
	private static ResultSet rs;

	private static int max_id = 800;
	private static LinkedList<Wifi> SqlWifi = new LinkedList<Wifi>();

	private static String LastCheckSum="nl";

	public static void getTable() throws ClassNotFoundException {
		try {   
			_con = DriverManager.getConnection(_url, _user, _password);
			st = _con.createStatement();
			rs = st.executeQuery("select * from oop_course_ariel.ex4_db ORDER BY time ASC");
			LastCheckSum = getCheckSum();
			Wifi a ;
			int counter =1;
			while(rs.next()) {//get new row
				for ( int i = 0 ;  i<rs.getInt(7)+1; i+=2) {//get each column to specific row
					a = new Wifi();
					a.setTime(rs.getString(2));
					a.setModel(rs.getString(3));
					a.setLAT(rs.getString(4));
					a.setLON(rs.getString(5));
					a.setALT(rs.getString(6));
					a.setMAC(rs.getString(i+8));
					a.setRXL(rs.getString(i+9));

					SqlWifi.add(a);
				}	
			}
			SqlWifi.sort(new SortByTimeComparator());
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQL.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			JOptionPane.showMessageDialog( null,
					"Connection failed \nerror: \n"+ex.getMessage(),
					"Connection failed",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	public static String getCheckSum() {
		Connection c;
		String ans="";
		try {
			c = DriverManager.getConnection(SQL.get_url(), SQL.get_user(), SQL.get_password());
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("CHECKSUM table oop_course_ariel.ex4_db");
			if(r.next()) {
				ans = r.getString(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ans;
	}
	public static boolean CheckIfUpToDate() {
		Connection c;
		try {
			c = DriverManager.getConnection(SQL.get_url(), SQL.get_user(), SQL.get_password());
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("CHECKSUM table oop_course_ariel.ex4_db");
			if(r.next()) {
				if(LastCheckSum.isEmpty())
					return true;
				else if(LastCheckSum.equals(r.getString(2)))
					return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static void main(String []args) {
		try {
			SQL.getTable();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CreateDB.FullDB.addAll(SqlWifi);
		CreateDB.CreateWifiSamples();
		for (int i = 0; i < CreateDB.WifiSamplesDB.size(); i++) {
			System.out.print(i+": ");
			for (int j = 0; j < CreateDB.WifiSamplesDB.get(i).size(); j++) {
				System.out.print(CreateDB.WifiSamplesDB.get(i).get(j));
			}
			System.out.println();
		}
	}
	public static void addToDB() {
		CreateDB.FullDB.addAll(SqlWifi);
		CreateDB.FullDB.sort(new SortByTimeComparator());
	}
	public static String get_ip() {
		return _ip;
	}
	public static void set_ip(String _ip) {
		SQL._ip = _ip;
	}
	public static String get_url() {
		return _url;
	}
	public static void set_url(String _url) {
		SQL._url = _url;
	}
	public static String get_user() {
		return _user;
	}
	public static void set_user(String _user) {
		SQL._user = _user;
	}
	public static String get_password() {
		return _password;
	}
	public static void set_password(String _password) {
		SQL._password = _password;
	}
}
