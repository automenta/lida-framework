/*******************************************************************************
 * Copyright (c) 2009, 2010 The University of Memphis.  All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the LIDA Software Framework Non-Commercial License v1.0 
 * which accompanies this distribution, and is available at
 * http://ccrg.cs.memphis.edu/assets/papers/2010/LIDA-framework-non-commercial-v1.0.pdf
 *******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VisualFieldPanel.java
 *
 * Created on 12/07/2009, 10:08:22
 */
package edu.memphis.ccrg.lida.framework.gui.panels;

import edu.memphis.ccrg.lida.environment.Environment;
import edu.memphis.ccrg.lida.framework.ModuleName;
import edu.memphis.ccrg.lida.framework.gui.events.FrameworkGuiEvent;
import edu.memphis.ccrg.lida.framework.gui.events.FrameworkGuiEventListener;
import edu.memphis.ccrg.lida.framework.gui.events.GuiEventProvider;

/**
 * 
 * @author Javier Snaider
 */
public class VisualEnvironmentPanel extends LidaPanelImpl implements
		FrameworkGuiEventListener {

	/** Creates new form VisualFieldPanel */
	public VisualEnvironmentPanel() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();

		setLayout(new java.awt.BorderLayout());

		jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
		jLabel1.setText("Environment");
		add(jLabel1, java.awt.BorderLayout.PAGE_START);

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		add(jScrollPane1, java.awt.BorderLayout.CENTER);
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel jLabel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextArea1;
	// End of variables declaration//GEN-END:variables

	@Override
	public void receiveFrameworkGuiEvent(FrameworkGuiEvent event) {
		if(event.getModule() == ModuleName.Environment){
			double[][] smc = (double[][]) event.getContent();
			String s = convertToString(smc);
			jTextArea1.setText(s);
		}
	}
	
	@Override
	public void initPanel(String[] params){
		Environment e = (Environment)lida.getSubmodule(ModuleName.Environment);
		if (e instanceof GuiEventProvider){
			((GuiEventProvider)e).addFrameworkGuiEventListener(this);
		}
	}

	public String convertToString(double[][] a) {
		String res = "";

		if ((a != null) && (a[0] != null)) {
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[0].length; j++) {
					if (a[i][j] == 0.0)
						res += "_._ ";
					else
						res += a[i][j] + " ";
				}
				res += "\n";
			}
		}
		return res;
	}
 
}
