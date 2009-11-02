/*
 * @(#)TEMImpl.java  1.0  May 1, 2009
 *
 * Copyright 2006-2009 Cognitive Computing Research Group.
 * 365 Innovation Dr, Rm 303, Memphis, TN 38152, USA.
 * All rights reserved.
 */

package edu.memphis.ccrg.lida.transientepisodicmemory;

import edu.memphis.ccrg.lida.framework.shared.Node;
import edu.memphis.ccrg.lida.framework.shared.NodeImpl;
import edu.memphis.ccrg.lida.framework.shared.NodeStructure;
import edu.memphis.ccrg.lida.globalworkspace.BroadcastContent;
import edu.memphis.ccrg.lida.globalworkspace.BroadcastListener;
import edu.memphis.ccrg.lida.transientepisodicmemory.sdm.SparseDistributedMemory;
import edu.memphis.ccrg.lida.transientepisodicmemory.sdm.Translator;
import edu.memphis.ccrg.lida.transientepisodicmemory.sdm.TranslatorImpl;
import edu.memphis.ccrg.lida.workspace.main.LocalAssociationListener;
import edu.memphis.ccrg.lida.workspace.main.WorkspaceListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.FutureTask;

/**
 * This is the cannonical implementation of TEM. It uses a sparse distributed
 * memory to store the information.
 * @author Rodrigo Silva L.
 */
public class TemImpl implements TransientEpisodicMemory, BroadcastListener, CueListener {

    private SparseDistributedMemory sdm;
    @SuppressWarnings("unused")
	private Translator translator;
	private List<LocalAssociationListener> localAssocListeners = new ArrayList<LocalAssociationListener>();
    
    /**
     * The constructor of the class.
     * @param structure the structure with the nodes used for this TEM
     */
    public TemImpl(NodeStructure structure) {
        translator = new TranslatorImpl(structure);
     }

    public TemImpl() {
	}

	/**
     * Receives the conscious broadcast and store its information in this TEM.
     * @param bc the content of the conscious broadcast
     */
    public void receiveBroadcast(BroadcastContent bc) {
        //TODO: logic for episodic learning goes here...
    }

    /**
     * Cues this episodic memory.
     * @param cue a set of nodes used to cue this episodic memory
     * @return a future task with the local association related to the cue
     */
    public void cue(MemoryCue cue) {
    	NodeStructure ns =cue.getNodeStructure();
//        Collection<Node> nodes = cue.getNodeStructure().getNodes();
//        LocalAssociationImpl association = new LocalAssociationImpl();
//        FutureTask<LocalAssociation> future = null;
        
        	byte[] address = translator.translate(ns);
            
        	byte[] out = sdm.retrieve(address);
            
            NodeStructure result = translator.translate(out);
           
            for(LocalAssociationListener l : localAssocListeners){
            	l.receiveLocalAssociation(result);
            }
        return;
    }
    
    // Rodrigo, this method is called continually.  The rate at which is called 
	// can be modified by changing the 'ticksPerCycle' parameter of PerceptualBufferDriver.
	// This is set in the Lida Class.  The higher the value for 'tickPerCycle' the slower
	// the rate of cueing will be.
	public synchronized void receiveCue(NodeStructure cue) {		
		//cue(cue);
	}

	public void learn() {

	}
	public void addLocalAssociationListener(LocalAssociationListener listener){
		localAssocListeners.add(listener);
	}

}