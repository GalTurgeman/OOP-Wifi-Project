package IO;

import java.sql.*;
import java.util.logging.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Main.SortByTimeComparator;
import Main.Wifi;


public class SQL {

	private static String _ip = "5.29.193.52";
	private static String _url = "jdbc:mysql://"+_ip+":3306/oop_course_ariel";
	private static String _user = "oop1";
	private static String _password = "Lambda1();";
	private static Connection _con = null;
	private static Statement st;
	private static ResultSet rs;
	private static int max_id = 800;
	private static LinkedList<Wifi> SqlWifi = new LinkedList<Wifi>();


	public static void getTable() throws ClassNotFoundException {
		try {   

			_con = DriverManager.getConnection(_url, _user, _password);
			st = _con.createStatement();
			rs = st.executeQuery("select * from oop_course_ariel.ex4_db ORDER BY time ASC");
			Wifi a ;
			int counter =1;
			while(rs.next()) {//get new row
				for ( int i = 0 ;  i<rs.getInt(7)+1; i+=2) {//get each column to specific row
//					System.out.print(counter +": "+ rs.getString(i+8)+",");
//					System.out.print(rs.getString(i+9)+" ,");
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
//				System.out.println();
//				counter++;
			}
			SqlWifi.sort(new SortByTimeComparator());
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQL.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
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
