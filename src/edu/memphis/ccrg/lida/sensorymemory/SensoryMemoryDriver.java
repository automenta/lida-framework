package edu.memphis.ccrg.lida.sensorymemory;

import edu.memphis.ccrg.lida.framework.LidaTask;
import edu.memphis.ccrg.lida.framework.LidaTaskManager;
import edu.memphis.ccrg.lida.framework.ModuleDriverImpl;

public class SensoryMemoryDriver extends ModuleDriverImpl {
	
	private SensoryMemory sm;
	
	public SensoryMemoryDriver(SensoryMemory sm, int ticksPerCycle, LidaTaskManager tm){
		super(ticksPerCycle, tm);
		this.sm = sm;
	}//constructor
		
	public void runThisDriver(){
		sm.processSensors();		
	}

	@Override
	protected void processResults(LidaTask task) {
		// TODO Auto-generated method stub
		
	}
	
}//class SMDriver