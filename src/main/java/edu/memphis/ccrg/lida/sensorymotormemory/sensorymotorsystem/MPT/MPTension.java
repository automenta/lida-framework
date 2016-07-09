/*******************************************************************************
 * Copyright (c) 2009, 2011 The University of Memphis.  All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the LIDA Software Framework Non-Commercial License v1.0 
 * which accompanies this distribution, and is available at
 * http://ccrg.cs.memphis.edu/assets/papers/2010/LIDA-framework-non-commercial-v1.0.pdf
 *******************************************************************************/

package edu.memphis.ccrg.lida.sensorymotormemory.sensorymotorsystem.MPT;

/**
 *
 * @author Daqi
 */
public interface MPTension {
    void setTension(double val);
    
    double getTension();
    
    void addTesion(double val);
    
    void removeTension(double val);
    
}
