package edu.memphis.ccrg.lida.workspace.episodicbuffer;

import edu.memphis.ccrg.lida.framework.LidaTaskManager;
import edu.memphis.ccrg.lida.framework.ModuleDriverImpl;
import edu.memphis.ccrg.lida.framework.gui.events.FrameworkGuiEvent;
import edu.memphis.ccrg.lida.framework.gui.events.FrameworkGuiEventListener;

/**
 * Not currently used.
 * @author ryanjmccall
 *
 */
public class EpisodicBufferDriver extends ModuleDriverImpl {

	private EpisodicBufferImpl eBuffer;

	public EpisodicBufferDriver(EpisodicBufferImpl eb, int ticksPerCycle, LidaTaskManager tm) {
		super(ticksPerCycle, tm);
		this.eBuffer = eb;
	}

	public void runThisDriver() {
		//eBuffer.activateCodelets();
		//eBuffer.sendEvent();
	}

	public void addFrameworkGuiEventListener(FrameworkGuiEventListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void sendEvent(FrameworkGuiEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}// class