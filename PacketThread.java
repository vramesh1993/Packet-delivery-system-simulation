package Assignment3;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;
import java.sql.*;

public class PacketThread {

public static ArrayList<Packet> Olist = new ArrayList<Packet>();
public static ArrayList<Double> IDs = new ArrayList<Double>();
public static ArrayList<Integer> src = new ArrayList<Integer>();
public static ArrayList<Integer> dest = new ArrayList<Integer>();
public static double usrid =4;

public ArrayList<Packet> getList() {
    return Olist;
}



public double getID() {
    return this.usrid;
}

	public static void main(String[] args) {
		Database dB= new Database();
		dB.dbconn();
		//Get the three lists from the database
		IDs = dB.getIDList();
		src = dB.getsrcList();
		dest = dB.getdestList();
		Packet P;
		
		Iterator<Double> it1 = IDs.iterator();
		Iterator<Integer> it2 = src.iterator();
		Iterator<Integer> it3 = dest.iterator();
		//Initialize all the packets, compute their shortest paths and put them in a list
		while(it1.hasNext()){
			P= new Packet(it1.next(),it2.next(),it3.next());
			Olist.add(P);
			P.packetPath();
		}
		
		Scanner reader = new Scanner(System.in);
		
		Runnable r1 = new SimulationThread();
        Thread t1 = new Thread(r1);
        Runnable r2 = new QueryThread();
        Thread t2 = new Thread(r2);
        
        double n=0;
        t1.start();
        
       
        
        while(true)
        {	
        	
            	System.out.println();
                System.out.println("Enter a Tracking ID:");
                
                n = reader.nextInt();
                if(n>=0)
                {
                	if(n>99999)
                		System.out.println("Invalid ID");
                	else{
                		usrid = n;
                		t2.run();
                	}   
                }
                else
                {
                	System.out.println("Number is negative! System Shutdown!");
                	System.exit(1);
                }

        }
        
      
		
	}

}
