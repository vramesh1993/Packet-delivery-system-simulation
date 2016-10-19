package Assignment3;
import java.util.*;
//import java.util.ThreadLocalRandom;

import java.security.Timestamp;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
public class Packet {

	
	public double packetId;
	public int sourceCity;
	public int descCity;
	public String status = "In Transit";
	int speed = 100;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	ArrayList<Integer> ShortestPath = new ArrayList();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	ArrayList<Integer> Times = new ArrayList();
	ArrayList<String> Timestamps = new ArrayList();
	int [] timeStamp = new int[20];
	public int currCity;
	int [] distance =new int[20];
	/////////////////////
	int date=28;
	int clocktime=0;
	////////////////////
	public ArrayList<String> historyOfPacket = new ArrayList();
	
	/***********************************************************
	 * Constructor to initialize packets with respective ID source city and destination
	 **********************************************************/
	
	public Packet (double pid,int sc, int dc){
		this.packetId = pid;		
		this.descCity = dc;
		this.sourceCity = sc;
		this.currCity=sc;
		
	}
	

	
	public double getID(){
		return this.packetId;
	}
	/*********************************************************
	 * Method to associate a packet with its shortest route and compute times
	 * taken for the packet to reach various nodes on the path.
	 * 
	 * *******************************************************/
	@SuppressWarnings("unchecked")
	public void packetPath(){
		
		
		int counter=0;
		LayoutClass lc = new LayoutClass();
		ShortestPath = lc.getShortestPath(this.sourceCity, this.descCity);
		int size = ShortestPath.size();
		ListIterator<Integer> itr = ShortestPath.listIterator();
		int x=0;
		Integer I;
		while(itr.hasNext()){
			I=itr.next();
			distance[counter++]= I;	
		}	
		
		//compute times between nodes on the shortest path
		for (int i=1;i<counter;i++){
			timeStamp[i-1]=lc.getMatVal(distance[i-1], distance[i]);
			timeStamp[i-1]= timeStamp[i-1]/speed;
		}
		
		int sum=0;
		//Associate each node with absolute time from start of packet disposal
		for(int j=0;j<counter-1;j++){
			sum= sum + timeStamp[j];
			timeStamp[j]=sum;
			Times.add(timeStamp[j]);
			
		}
		
	}
	
	
	/********************************************************
	 * Update the status of the packet. Simulation thread keeps calling this 
	 * method to update status of every packet on the system.
	 * 
	 */
	public void updatePacket(int golbalTime){

		LayoutClass lc = new LayoutClass();
		int count=0;
		historyOfPacket.clear();
		for(int i=count;i<=Times.size();i++){
			timeStamp[i] = timeStamp[i] - golbalTime;
			
			if(timeStamp[count]<=0 ){
				currCity = ShortestPath.get(i);
				count =i;
				if(this.currCity == this.descCity){
					this.status ="Delivered";
				}
				historyOfPacket.add("\t\t\t"+ lc.cityName(currCity));
				Timestamps.add("\n" + "09/"+date+"/2016"+" "+clocktime+":00");
			}
			//Timestamps.set(1,"\n" + "09/"+date+"/2016"+" "+"0:00");
			clocktime += golbalTime;
			if(clocktime>24){
				clocktime=0;
				date++;
				if(date>30)
					date = 1;
			}
			
		}
	}
				
		
				
}
	
	


/*
LayoutClass lc = new LayoutClass();
int count=0;
historyOfPacket.clear();
for(int i=count;i<=Times.size();i++){
	//timeStamp[i] = timeStamp[i] - golbalTime;
	
	if(timeStamp[count]<=0 ){
		currCity = ShortestPath.get(i);
		count =i;
		if(this.currCity == this.descCity){
			this.status ="Delivered";
			return;
		}
		timeStamp[i] = timeStamp[i] - golbalTime;
		historyOfPacket.add("\n" + lc.cityName(currCity));
		
	}
	
	
	*/

