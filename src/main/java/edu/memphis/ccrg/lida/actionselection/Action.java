/*******************************************************************************
 * Copyright (c) 2009, 2011 The University of Memphis.  All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the LIDA Software Framework Non-Commercial License v1.0 
 * which accompanies this distribution, and is available at
 * http://ccrg.cs.memphis.edu/assets/papers/2010/LIDA-framework-non-commercial-v1.0.pdf
 *******************************************************************************/
package edu.memphis.ccrg.lida.actionselection;

import edu.memphis.ccrg.lida.framework.initialization.Initializable;

/**
 * Encapsulation of an action to be executed.
 * 
 * @author Ryan J. McCall
 * @author Javier Snaider
 */
public interface Action extends Initializable {

	@SuppressWarnings(value = { "all" })
	public enum Topology {
		BASIC, PARALLEL, SEQUENCIAL
	}

	/**
	 * @return the action label.
	 */
    String getLabel();

	/**
	 * @param label
	 *            the action label to set.
	 */
    void setLabel(String label);

	/**
	 * @return the {@link Action} id
	 */
    int getId();

}
