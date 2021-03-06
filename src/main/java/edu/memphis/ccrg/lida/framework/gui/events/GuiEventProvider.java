/*******************************************************************************
 * Copyright (c) 2009, 2011 The University of Memphis.  All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the LIDA Software Framework Non-Commercial License v1.0 
 * which accompanies this distribution, and is available at
 * http://ccrg.cs.memphis.edu/assets/papers/2010/LIDA-framework-non-commercial-v1.0.pdf
 *******************************************************************************/
package edu.memphis.ccrg.lida.framework.gui.events;

/**
 * A GuiContentProvider is a class that provides content to a GUI. The GUIs
 * listen to providers, following Observer pattern.
 * 
 * @author Ryan J. McCall
 * 
 */
public interface GuiEventProvider {

	/**
	 * Must be able to register FrameworkGuiEvent listeners
	 * 
	 * @param listener
	 *            receiver of GuiEvents, typically a GuiPanel
	 */
    void addFrameworkGuiEventListener(FrameworkGuiEventListener listener);

	/**
	 * This is a convenience method to send GUI events to listeners.
	 * 
	 * @param event
	 *            GuiEvent
	 */
    void sendEventToGui(FrameworkGuiEvent event);
}
