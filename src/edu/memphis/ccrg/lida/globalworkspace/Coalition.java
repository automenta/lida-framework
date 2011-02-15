/*******************************************************************************
 * Copyright (c) 2009, 2010 The University of Memphis.  All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the LIDA Software Framework Non-Commercial License v1.0 
 * which accompanies this distribution, and is available at
 * http://ccrg.cs.memphis.edu/assets/papers/2010/LIDA-framework-non-commercial-v1.0.pdf
 *******************************************************************************/
/**
 * 
 */
package edu.memphis.ccrg.lida.globalworkspace;

import edu.memphis.ccrg.lida.attention.AttentionCodelet;
import edu.memphis.ccrg.lida.framework.shared.activation.Activatible;

/**
 * Coaltions are created and added to the {@link GlobalWorkspace}.
 * by {@link AttentionCodelet} objects.
 * {@link Coalition} must overwrite correctly {@link Object#equals(Object)} and {@link Object#hashCode()} methods.
 * 
 * @author Javier Snaider
 */
public interface Coalition extends Activatible { 

	/**
	 * 
	 * @return The Content of the coalition.
	 */
	public BroadcastContent getContent();
	
}
