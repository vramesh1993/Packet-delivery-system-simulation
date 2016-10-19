package Assignment3;

import java.util.ArrayList;
import java.util.Iterator;

public class SimulationThread implements Runnable{
	public static ArrayList<Packet> Olist1 = new ArrayList<Packet>();
/******************************************************
 * Simulation thread runs silently in the background.
 * sleep for 1 second and then update all the packets
 * 
 * 
 ******************************************************/	
	public void run(){
		while(true){
			PacketThread pt = new PacketThread();
			Olist1 = pt.getList();
			Iterator<Packet> it = Olist1.iterator();
			Packet temp;
			//Iterate through all the packets and keep updating them
			while(it.hasNext()){
				temp = it.next();
				temp.updatePacket(1);
			}
			
			try {
				Thread.sleep(1000);	
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			
			
		}
    }
	
}
