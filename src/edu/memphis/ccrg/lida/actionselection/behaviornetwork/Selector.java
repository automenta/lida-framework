/*******************************************************************************
 * Copyright (c) 2009, 2010 The University of Memphis.  All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the LIDA Software Framework Non-Commercial License v1.0 
 * which accompanies this distribution, and is available at
 * http://ccrg.cs.memphis.edu/assets/papers/2010/LIDA-framework-non-commercial-v1.0.pdf
 *******************************************************************************/
package edu.memphis.ccrg.lida.actionselection.behaviornetwork;

import java.util.Collection;

import edu.memphis.ccrg.lida.actionselection.Behavior;

/**
 * A strategy for choosing which behavior to execute.
 *  
 * @author Ryan J. McCall
 */
public interface Selector {

	/**
	 * Select a single behavior as the current winner.
	 * 
	 * @param behaviors
	 *            the behaviors
	 * @param candidateThreshold
	 *            the candidate threshold
	 * @return Behavior selected behavior
	 */
	public Behavior selectSingleBehavior(Collection<Behavior> behaviors, double candidateThreshold);

}
