/*******************************************************************************
 * Copyright (c) 2009, 2011 The University of Memphis.  All rights reserved. 
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
 * NodeStructurePanel.java
 *
 * Created on 17/08/2009, 08:55:55
 */

package edu.memphis.ccrg.lida.framework.gui.panels;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Icon;

import org.apache.commons.collections15.Transformer;

import edu.memphis.ccrg.lida.framework.FrameworkModule;
import edu.memphis.ccrg.lida.framework.gui.utils.GuiLink;
import edu.memphis.ccrg.lida.framework.gui.utils.GuiUtils;
import edu.memphis.ccrg.lida.framework.gui.utils.NodeIcon;
import edu.memphis.ccrg.lida.framework.gui.utils.NodeStructureGuiAdapter;
import edu.memphis.ccrg.lida.framework.shared.Link;
import edu.memphis.ccrg.lida.framework.shared.Linkable;
import edu.memphis.ccrg.lida.framework.shared.Node;
import edu.memphis.ccrg.lida.framework.shared.NodeStructure;
import edu.memphis.ccrg.lida.framework.shared.NodeStructureImpl;
import edu.memphis.ccrg.lida.framework.shared.activation.Activatible;
import edu.memphis.ccrg.lida.framework.tasks.TaskManager;
import edu.memphis.ccrg.lida.pam.PamNode;
import edu.memphis.ccrg.lida.pam.PerceptualAssociativeMemoryImpl;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.util.Relaxer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;

/**
 * A {@link GuiPanel} which creates a graphical view of a {@link NodeStructure}.
 * 
 *  The {@link NodeStructure} is one from a paticular {@link FrameworkModule} which is defined as a parameter in the guiPanels.properties file.
 *  
 *  {@link FrameworkModule#getModuleContent(Object...)} must return {@link NodeStructure}.
 *   
 * @author Javier Snaider
 */
public class NodeStructurePanel extends GuiPanelImpl {

	private static final Logger logger = Logger.getLogger(NodeStructurePanel.class.getCanonicalName());
	
	private NodeStructureGuiAdapter guiGraph=new NodeStructureGuiAdapter(new NodeStructureImpl());
	private VisualizationViewer<Linkable, GuiLink> vizViewer;
	private FrameworkModule module;
	
	/** Creates new form NodeStructurePanel */
	public NodeStructurePanel() {
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

		jToolBar1 = new javax.swing.JToolBar();
		refreshButton = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();

		jToolBar1.setRollover(true);

		refreshButton.setText("refresh");
		refreshButton.setFocusable(false);
		refreshButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		refreshButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		refreshButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				refreshButtonActionPerformed(evt);
			}
		});
		jToolBar1.add(refreshButton);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 400,
				Short.MAX_VALUE).addComponent(jScrollPane1,
				javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addComponent(
												jToolBar1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												25,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												269, Short.MAX_VALUE)));
	}// </editor-fold>//GEN-END:initComponents

	private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_refreshButtonActionPerformed
		refresh();
		refresh();
	}// GEN-LAST:event_refreshButtonActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JToolBar jToolBar1;
	private javax.swing.JButton refreshButton;
	// End of variables declaration//GEN-END:variables

	private void draw() {
		// The Layout<V, E> is parameterized by the vertex and edge types

		Layout<Linkable, GuiLink> layout = new FRLayout<Linkable, GuiLink>(guiGraph);
		layout.setSize(new Dimension(300, 300)); // Sets initial size of the
		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		vizViewer = new VisualizationViewer<Linkable, GuiLink>(layout);
		vizViewer.setPreferredSize(new Dimension(350, 350)); // Sets viewing area size
		// Show vertex and edge labels
		vizViewer.getRenderContext().setVertexLabelTransformer(
				new Transformer<Linkable, String>() {
					@Override
					public String transform(final Linkable linkable) {
						if (linkable instanceof Link) {
							return ((Link) linkable).getCategory().getLabel();
						}
						return linkable.getLabel();
					}
				});

		// vv.getRenderContext().setEdgeLabelTransformer(new
		// ToStringLabeller<GuiLink>());
		// Create a graph mouse and add it to the visualization component
		DefaultModalGraphMouse<Linkable, GuiLink> gm2 = new DefaultModalGraphMouse<Linkable, GuiLink>();
		gm2.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		vizViewer.getRenderContext().setVertexIconTransformer(
				new Transformer<Linkable, Icon>() {
					/*
					 * Implements the Icon interface to draw an Icon with
					 * background color
					 */
					@Override
					public Icon transform(final Linkable v) {
						if (v instanceof Node) {
							return NodeIcon.NODE_ICON;
						} else {
							return NodeIcon.LINK_ICON;
						}
					}
				});
		vizViewer.setVertexToolTipTransformer(new Transformer<Linkable, String>() {
			//TODO use the toString of the linkable
			@Override
			public String transform(final Linkable l) {
				String tip = null;
				if (l instanceof Activatible) {
					if(l instanceof PamNode){
						PamNode pn=(PamNode)l;
						tip ="<html><b>"+pn.toString() + "</b><br /> Activation: " + pn.getActivation()+ 
						"<br /> BaseActivation: " + pn.getBaseLevelActivation()+ "<br /> Threshold: " + 
						PerceptualAssociativeMemoryImpl.getPerceptThreshold()+"</html>";
						
					}else{
					Activatible n = (Activatible) l;
					tip ="<html><b>"+n.toString() + "</b><br /> Activation: " + n.getActivation()+"</html>";
					}
				}
				return tip;
			}
		});
		vizViewer.setEdgeToolTipTransformer(new Transformer<GuiLink, String>() {
			@Override
			public String transform(final GuiLink l) {
				String tip = null;
				GuiLink gl = l;
				Link n = gl.getLink();
				tip ="<html><b>"+n.toString() + "</b><br /> Activation: " + n.getActivation()+"</html>";
				return tip;
			}
		});

		vizViewer.setGraphMouse(gm2);

		jScrollPane1.setViewportView(vizViewer);
		vizViewer.fireStateChanged();
	}
	
	/**
	 * Definition of this Panel should include a parameter for the ModuleName for the
	 * module from which the NodeStructure will be obtained.  
	 * E.g., workspace.PerceptualBuffer or PerceptualAssociativeMemory
	 * @see edu.memphis.ccrg.lida.framework.gui.panels.GuiPanelImpl#initPanel(java.lang.String[])
	 */
	@Override
	public void initPanel(String[]param){	
		if (param == null || param.length == 0) {
			logger.log(Level.WARNING,
					"Error initializing NodeStructurePanel, not enough parameters.",
					0L);
			return;
		}
		module = GuiUtils.parseFrameworkModule(param[0], agent);

		if(module != null){
			display(module.getModuleContent());
			draw();
		}else{
			logger.log(Level.WARNING,
					"Unable to parse module " + param[0] + ". Panel not initialized.",
					0L);
		}
	}
	
	@Override
	public void refresh(){
		display(module.getModuleContent());
		Layout<Linkable, GuiLink> layout = vizViewer.getGraphLayout();
		layout.initialize();
		Relaxer relaxer = vizViewer.getModel().getRelaxer();
		if(relaxer != null) {
			relaxer.stop();
			relaxer.prerelax();
			relaxer.relax();
		}
	}

	@Override
	public void display(Object o) {
		if(o instanceof NodeStructure){
			guiGraph.setNodeStructure((NodeStructure) o);
		}else{
			logger.log(Level.WARNING, "Can only display NodeStructure, but received " +
					o + " from module: " + module.getModuleName(), TaskManager.getCurrentTick());
		}
    }
}