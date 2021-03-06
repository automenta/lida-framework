/*******************************************************************************
 * Copyright (c) 2009, 2011 The University of Memphis.  All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the LIDA Software Framework Non-Commercial License v1.0 
 * which accompanies this distribution, and is available at
 * http://ccrg.cs.memphis.edu/assets/papers/2010/LIDA-framework-non-commercial-v1.0.pdf
 *******************************************************************************/
package edu.memphis.ccrg.lida.framework.strategies;

import edu.memphis.ccrg.lida.framework.shared.ElementFactory;

import java.util.Map;

/**
 * A strategy pattern for exciting Activatibles or Learnables
 * 
 * Implementations should add themselves to {@link ElementFactory} via the
 * factoriesData.xml configuration file.
 * 
 * 
 * @author Javier Snaider
 * @author Ryan J. McCall
 * 
 */
public interface ExciteStrategy extends Strategy {

	/**
	 * Excites the current activation according to some internal excite
	 * function.
	 * 
	 * @param currentActivation
	 *            activation of the entity before excite.
	 * @param excitation
	 *            amount of activation to adds
	 * @param params
	 *            parameters
	 * @return new activation amount
	 */
    double excite(double currentActivation, double excitation,
                  Object... params);

	/**
	 * Excites the current activation according to some internal excite
	 * function.
	 * 
	 * @param currentActivation
	 *            activation of the entity before excite.
	 * @param excitation
	 *            amount of activation to adds
	 * @param params
	 *            parameters
	 * @return new activation amount
	 */
    double excite(double currentActivation, double excitation,
                  Map<String, ? extends Object> params);

}
