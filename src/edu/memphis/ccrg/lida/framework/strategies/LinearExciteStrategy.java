/*******************************************************************************
 * Copyright (c) 2009, 2011 The University of Memphis.  All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the LIDA Software Framework Non-Commercial License v1.0 
 *  which accompanies this distribution, and is available at
 * http://ccrg.cs.memphis.edu/assets/papers/2010/LIDA-framework-non-commercial-v1.0.pdf
 *******************************************************************************/
package edu.memphis.ccrg.lida.framework.strategies;

import java.util.Map;

/**
 * Basic {@link ExciteStrategy} governed by a linear curve.
 */
public class LinearExciteStrategy extends StrategyImpl implements ExciteStrategy {
	
	/*
	 * The default slope
	 * 
	 */
	private static final double DEFAULT_M = 1.0;

	/*
	 * The slope of this linear curve.
	 */
	private double m;

	/**
	 * Creates a new instance of LinearCurve. Values for slope and intercept are
	 * set to the default ones.
	 */
	public LinearExciteStrategy() {
		m = DEFAULT_M;
	}
	
	@Override
	public void init() {
		m = (Double) getParam("m", DEFAULT_M);
	}
	
	@Override
	public double excite(double currentActivation, double excitation, Object... params) {
		double mm = m;
		if (params!= null && params.length != 0) {
			mm = (Double) params[0];
		}
		
		return calcActivation(currentActivation, excitation, mm);
	}
	
	@Override
	public double excite(double currentActivation, double excitation, Map<String, ?> params) {
		double mm = m;
		if(params != null && params.containsKey("m")){
			mm = (Double) params.get("m");
		}
		
		return calcActivation(currentActivation, excitation, mm);
	} 

	/*
	 * @param currentActivation
	 * @param excitation
	 * @param mm
	 * @return
	 */
	private double calcActivation(double currentActivation, double excitation,
			double mm) {
		currentActivation += mm * excitation;
		if(currentActivation > 1.0){
			return 1.0;
		}else if(currentActivation < 0.0){
			return 0.0;
		}
		return currentActivation;
	}

}
