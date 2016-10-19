package Assignment3;

import java.util.ArrayList;
import java.util.ListIterator;

import java.util.Iterator;

public class QueryThread implements Runnable {
	//object list
	public static ArrayList<Packet> Olist1 = new ArrayList<Packet>();
	double ID=0;
	Packet pck;
	
	public void run(){
		PacketThread pt = new PacketThread();
		
		Olist1 = pt.getList();
		Iterator<Packet> it = Olist1.iterator();
		
		double usrid = pt.getID();
		while(it.hasNext()){	
			if(ID == usrid)		//search for the userid entered from console
				break;
			pck=it.next();		
			ID = pck.packetId;
		}	
		ID=0;
		 
		printDetails(pck);		//print the details of the packet corresponding to the user id
		
	}
	
	public void printDetails(Packet pt){
		LayoutClass lc = new LayoutClass();
		System.out.println("-----------TRACKING STATUS-----------");
		System.out.println("Tracking ID:"+(int)pt.packetId);
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println("Source City:"+lc.cityName(pt.sourceCity));
		System.out.println("Dest City:"+lc.cityName(pt.descCity));
		System.out.println();
		System.out.println("Current Location:"+lc.cityName(pt.currCity));
		System.out.println("-------------------------------------");
		System.out.println("Packet status: "+pt.status);
		System.out.println();
		System.out.println("Packet History...");
		for(int i=0;i<pt.historyOfPacket.size();i++){
			System.out.print(pt.Timestamps.get(i));
			System.out.println(pt.historyOfPacket.get(i));
		}
	}
	
}
