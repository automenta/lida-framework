/*******************************************************************************
 * Copyright (c) 2009, 2010 The University of Memphis.  All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the LIDA Software Framework Non-Commercial License v1.0 
 * which accompanies this distribution, and is available at
 * http://ccrg.cs.memphis.edu/assets/papers/2010/LIDA-framework-non-commercial-v1.0.pdf
 *******************************************************************************/
/**
 * Node.java
 */
package edu.memphis.ccrg.lida.framework.shared;

import java.io.Serializable;

import edu.memphis.ccrg.lida.framework.shared.activation.Activatible;
import edu.memphis.ccrg.lida.pam.PamNode;

/**
 * Node represents a Concept in LIDA. It could be implemented in different ways 
 * for different parts of the system. For example could be pamNodes in the PAM and WorkspaceNodes
 * in the workspace.
 * Nodes with the same id represents the same concept so equals have to return true even if the objects are
 * of different classes.
 * 
 * @author Javier Snaider
 * 
 */
public interface Node extends Linkable, Activatible, Serializable {
	
	/**
	 * Returns the underlying PamNode that grounds this node 
	 * @return
	 */
    public PamNode getGroundingPamNode();
    
    /**
     * Used by factory to set the underlying PamNode for this node
     * @param n PamNode
     */
    public void setGroundingPamNode (PamNode n);
    
    //TODO remove since redundant with getExtendedId?
    public int getId();
    public void setId(int id);
    
    @Override
	public String getLabel();
    public void setLabel(String label);
    
	public String getFactoryNodeType();
	public void setFactoryNodeType(String factoryNodeType);
	
	/**
	 * Returns a double signifying the degree to which this node is a goal of the agent
	 * @return
	 */
	public double getDesirability();
	
	/**
	 * Set desirability
	 * @param degree  degree to which this node is a goal of the agent
	 */
	public void setDesirability(double degree);

}//interface

