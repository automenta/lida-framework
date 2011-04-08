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
 * LidaPanelImpl.java
 *
 * Created on 17/08/2009, 08:08:08
 */

package edu.memphis.ccrg.lida.framework.gui.panels;

import javax.swing.JPanel;

import edu.memphis.ccrg.lida.framework.Lida;
import edu.memphis.ccrg.lida.framework.gui.LidaGuiController;
import edu.memphis.ccrg.lida.framework.gui.events.FrameworkGuiEventListener;
import edu.memphis.ccrg.lida.framework.gui.events.GuiEventProvider;

/**
 * Abstract implementation of {@link LidaPanel}
 * 
 * Panels that extend this class inherit the 'initPanel()' method.  
 * This method is called for every LidaPanel this created in LidaGui.  
 * Since all LidaPanels have a reference to the {@link Lida} object, 
 * the LidaPanel can register itself as a {@link FrameworkGuiEventListener} of 
 * whatever module it wants (provided that module implements {@link GuiEventProvider}).  
 * This means that the module has a reference to the panel, and 
 * it can use this reference to send data to the panel to display.
 * 
 * Implementations should be added in the guiPanels.properties file 
 * by name of panel and canonical name of class. 
 *
 * @author Javier Snaider
 */
public abstract class LidaPanelImpl extends javax.swing.JPanel implements LidaPanel{

	protected LidaGuiController controller;
	protected Lida lida;

    /** Creates new form LidaPanelImpl */
    public LidaPanelImpl() {
        initComponents();
    }

    /** 
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
	public void registrerLidaGuiController(LidaGuiController lgc) {
        controller = lgc;
    }

    /**
     * Subclasses may override this method.
     */
    @Override
	public void display(Object o) {
    }

    /**
     * Subclasses may override this method.
     */
    @Override
	public void refresh() {
    }
	
    /**
     * Subclasses may override this method.
     */
	@Override
	public void initPanel(String[] param){
	}
    
    @Override
	public JPanel getPanel() {
        return this;
    }

    @Override
	public void registerLida(Lida lida) {
		this.lida=lida;
	}

}