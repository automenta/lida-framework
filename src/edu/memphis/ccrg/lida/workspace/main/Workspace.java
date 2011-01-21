/*******************************************************************************
 * Copyright (c) 2009, 2010 The University of Memphis.  All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the LIDA Software Framework Non-Commercial License v1.0 
 * which accompanies this distribution, and is available at
 * http://ccrg.cs.memphis.edu/assets/papers/2010/LIDA-framework-non-commercial-v1.0.pdf
 *******************************************************************************/
package edu.memphis.ccrg.lida.workspace.main;

import edu.memphis.ccrg.lida.framework.LidaModule;
import edu.memphis.ccrg.lida.framework.shared.NodeStructure;
import edu.memphis.ccrg.lida.transientepisodicmemory.CueListener;

/**
 * The workspace collection of submodules where Cues from episodic memories, the recent contents
 * of conscious, the perceptual buffer, and the current situational model are stored. 
 * A workspace should be interfaceable with codelets whose job is to
 *  operate on the contents of these submodules.  
 * 
 * @author Ryan J. McCall
 */
public interface Workspace extends LidaModule{

	/**
	 * Add episodic memory that will listen for cues from the Workspace
	 * @param l listener
	 */
	public void addCueListener(CueListener l);
	
	/**
	 * Adds specified {@link WorkspaceListener}
	 * @param l listener of this Workspace
	 */
	public void addWorkspaceListener(WorkspaceListener l);

	/**
	 * Prompts this Workspace to cue episodic memories with content.
	 * 
	 * @param ns NodeStructure to cue with.
	 */
	public void cueEpisodicMemories(NodeStructure ns);
	
	/**
	 * Sets lowerActivationBound
	 * @param lowerBound lower bound for the amount of activation
	 * a node or link must have to remain in Workspace buffer.
	 */
	public void setActivationLowerBound(double lowerBound);
}
