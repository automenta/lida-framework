/*******************************************************************************
 * Copyright (c) 2009, 2010 The University of Memphis.  All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the LIDA Software Framework Non-Commercial License v1.0 
 * which accompanies this distribution, and is available at
 * http://ccrg.cs.memphis.edu/assets/papers/2010/LIDA-framework-non-commercial-v1.0.pdf
 *******************************************************************************/
package edu.memphis.ccrg.lida.episodicmemory;

import edu.memphis.ccrg.lida.framework.shared.NodeStructure;
import edu.memphis.ccrg.lida.workspace.main.Workspace;

/**
 * Listens to cues from the {@link Workspace}. 
 * This interface should be implemented by Episodic Memory modules.
 * 
 * @author Ryan J. McCall
 *
 */
public interface CueListener {
	
	/**
	 * Receive a cue
	 * @param cue NodeStructure to recall
	 */
	public void receiveCue(NodeStructure cue);

}
