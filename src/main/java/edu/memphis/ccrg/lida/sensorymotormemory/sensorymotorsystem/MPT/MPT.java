/*******************************************************************************
 * Copyright (c) 2009, 2011 The University of Memphis.  All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the LIDA Software Framework Non-Commercial License v1.0 
 * which accompanies this distribution, and is available at
 * http://ccrg.cs.memphis.edu/assets/papers/2010/LIDA-framework-non-commercial-v1.0.pdf
 *******************************************************************************/

package edu.memphis.ccrg.lida.sensorymotormemory.sensorymotorsystem.MPT;

import edu.memphis.ccrg.lida.framework.tasks.TaskSpawner;

/**
 * A Motor Plan Template (MPT) is a certain data structure inside Sensory Motor Memory.
 * A MPT becomes a Motor Plan (MP) after it was specified; and a MP is executable
 * in a online control process
 * @author Daqi
 */
public interface MPT{

   
    /*
     * Store MPT in long-term memory
     */
    void load();

    void save();

    void init();

    /*
     * Receive data on running time
     */
    void receiveData(Object o);

    /*
     * Output the command
     */
    Object outputCommands();

    /*
     * To specify a MPT to a MP
     */
    void specify();

    /*
     * To update the command's values in running time
     */
    void update();
    
    /*
     * Starting to run a Motor Plan (MP), specified MPT
     */
    void onlineControl();


    /*
     * Collection of mapping functions from sensed data through dorsal stream to motor command values
     */
    Object dorsal_MotorValueOf(String cmdName);

    /*
     * Collection of mapping functions from a selected behavior (context) to motor command values
     */
    Object behavior_MotorValueOf(String cmdName);


    /*
     * A MPT involves executable elements, such as FSM, a kind of FrameworkTask. 
     * Therefore we need a receiveTS method to reuse the TS of SMM.
     */
    void receiveTS(TaskSpawner ts);

}
