package edu.memphis.ccrg.lida.workspace.workspaceBuffer;

import edu.memphis.ccrg.lida.framework.LidaModule;

/**
 * This interface defines how codelets can access data from Workspace sub modules.
 * Modules that need to be accessible to codelets should implement this interface.
 * 
 * @author Ryan J McCall, Javier Snaider
 *
 */
public interface WorkspaceBuffer extends LidaModule{
	
	/**
	 * Lower bound for a node or link to remain in the buffer
	 * @param lowerActivationBound
	 */
	public abstract void setLowerActivationBound (double lowerActivationBound);
}
