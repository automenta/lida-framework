/*******************************************************************************
 * Copyright (c) 2009, 2011 The University of Memphis.  All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the LIDA Software Framework Non-Commercial License v1.0 
 * which accompanies this distribution, and is available at
 * http://ccrg.cs.memphis.edu/assets/papers/2010/LIDA-framework-non-commercial-v1.0.pdf
 *******************************************************************************/
/**
 *
 */
package edu.memphis.ccrg.lida.framework.gui.panels;

import edu.memphis.ccrg.lida.framework.Agent;
import edu.memphis.ccrg.lida.framework.gui.FrameworkGui;
import edu.memphis.ccrg.lida.framework.gui.FrameworkGuiController;

import javax.swing.*;

/**
 * A GUI Panel which can be displayed in the {@link FrameworkGui}
 * 
 * @author Javier Snaider
 */
public interface GuiPanel {
	/**
	 * Initializes panel
	 * 
	 * @param param
	 *            Parameters to initialize with.
	 */
    void initPanel(String[] param);

	/**
	 * Registers the {@link FrameworkGuiController} as the controller.
	 * 
	 * @param lgc
	 *            GuiController for this panel
	 */
    void registerGuiController(FrameworkGuiController lgc);

	/**
	 * Sets {@link Agent} object as the model for this panel.
	 * 
	 * @param agent
	 *            {@link Agent} object
	 */
    void registerAgent(Agent agent);

	/**
	 * Update Panel to display supplied object
	 * 
	 * @param o
	 *            Object to display
	 */
    void display(Object o);

	/**
	 * Refreshes the content this panel displays.
	 */
    void refresh();

	/**
	 * Returns associated JPanel
	 * 
	 * @return a JPanel
	 */
    JPanel getPanel();

	/**
	 * Sets name of panel
	 * 
	 * @param name
	 *            label for panel
	 */
    void setName(String name);

	/**
	 * Gets name of panels
	 * 
	 * @return name of panel
	 */
    String getName();

}
