package edu.memphis.ccrg.lida.sensoryMemory;

import java.util.ArrayList;
import java.util.List;

public class SensoryMemory implements SimulationListener{
	
	private final int size = 5;
	private SimulationContent simContent;
	private SensoryContent senseContent;
	private List<SensoryListener> listeners;
			
	public SensoryMemory(){
		simContent = new SimulationContent(size);
		senseContent = new SensoryContent(size);	
		listeners = new ArrayList<SensoryListener>();
	}//SensoryMemory
	
	public synchronized void receiveSimContent(SimulationContent sc){//SimulationListener
		simContent = sc;		
	}	
	
	public void processSimContent(){
		int[] src = new int[size];
		int[] dest = new int[size];
		synchronized(this){
			src = (int[])simContent.getSenseContent();
			System.arraycopy(src, 0, dest, 0, size);//TODO: WRY??
		}
		//do processing		
		senseContent.setContent(dest);		
	}//
	
	//broadcast to all listeners
	public void sendSensoryContent(boolean print){
		if(print)
			senseContent.print();
		for(int i = 0; i < listeners.size(); i++)
			(listeners.get(i)).receiveSense(senseContent);
	}
		
	public void addSensoryListener(SensoryListener sl){
		listeners.add(sl);
	}
	
}//class SensoryMemory
