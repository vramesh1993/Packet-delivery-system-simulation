package Assignment3;

import java.sql.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Database {

	int id,source,destination;
	/***********************************************************/
	//JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/STUD?useSSL=false";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "visH!1993";
	static Connection conn = null;
	static Statement stmt = null;
	/***********************************************************/
	//public static ArrayList<Packet> Olist = new ArrayList<Packet>();
	public static ArrayList<Double> usrids = new ArrayList<Double>();
	public static ArrayList<Integer> src = new ArrayList<Integer>();
	public static ArrayList<Integer> dest = new ArrayList<Integer>();
	
	
	public double getID(){
		return (double)this.id;
	}
	
	public double getsource(){
		return this.source;
	}
	
	public double getdest(){
		return this.destination;
	}
	
	public ArrayList<Double> getIDList() {
		    return usrids;
		}
	
	
	public ArrayList<Integer> getsrcList() {
	    return src;
	}

	public ArrayList<Integer> getdestList() {
	    return dest;
	}
	
	//public ArrayList<Packet> getList() {
	//    return Olist;
	//}
	
	public void dbconn(){
		try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();

		      String sql = "SELECT * FROM course"; //Where id ="+ idnum +" ";
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      int i=0;
		      while(rs.next()){
		         //Retrieve by column name
		    	  //System.out.println(rs.getInt("tracking_ID"));
		    	  usrids.add((double)rs.getInt("tracking_ID"));
		    	  src.add(rs.getInt("Source"));
		    	  dest.add(rs.getInt("Destination"));
		    	 // Packet P1 = new Packet(rs.getInt("tracking_ID"),rs.getInt("Source"),rs.getInt("Destination"));
		    	  //Olist.add(P1);
		    	 // P1.packetPath();
		    	  i++;
		    	 
		        //id  = rs.getInt("id");
		         //source = rs.getInt("source");
		         //destination = rs.getInt("destination");
		         
		         
		        // createtable();
		      }
		      rs.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		}//end 

	}



