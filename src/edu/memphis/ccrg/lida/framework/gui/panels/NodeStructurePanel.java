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

import edu.memphis.ccrg.lida.framework.Lida;
import edu.memphis.ccrg.lida.framework.gui.utils.NodeStructureGuiAdapter;
import edu.memphis.ccrg.lida.framework.shared.Link;
import edu.memphis.ccrg.lida.framework.shared.Linkable;
import edu.memphis.ccrg.lida.framework.shared.NodeStructure;
import edu.memphis.ccrg.lida.pam.PerceptualAssociativeMemoryImpl;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;


/**
 *
 * @author Javier Snaider
 */
public class NodeStructurePanel extends LidaPanelImpl {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Creates new form NodeStructurePanel */
    public NodeStructurePanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        refreshButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        jToolBar1.setRollover(true);

        refreshButton.setText("refresh");
        refreshButton.setFocusable(false);
        refreshButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        refreshButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(refreshButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton refreshButton;
    // End of variables declaration//GEN-END:variables
    
private void draw(){
	// The Layout<V, E> is parameterized by the vertex and edge types
	
	Layout<Linkable, Link> layout = new FRLayout<Linkable, Link>(getGraph());
	layout.setSize(new Dimension(300,300)); // sets the initial size of the space
	// The BasicVisualizationServer<V,E> is parameterized by the edge types
	VisualizationViewer<Linkable, Link> vv =
	new VisualizationViewer<Linkable, Link>(layout);
	vv.setPreferredSize(new Dimension(350,350)); //Sets the viewing area size
	// Show vertex and edge labels
	vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Linkable>());
	vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<Link>());
	// Create a graph mouse and add it to the visualization component
	DefaultModalGraphMouse<Linkable, Link> gm = new DefaultModalGraphMouse<Linkable, Link>();
	gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
	vv.setGraphMouse(gm);
	jScrollPane1.setViewportView(vv);
}
private Graph<Linkable,Link> getGraph(){
		NodeStructure struct = ((PerceptualAssociativeMemoryImpl) lida.getPam()).getNodeStructure();
		Graph<Linkable,Link> g = new NodeStructureGuiAdapter(struct);
		return g;
}

public void registrerLida(Lida lida){
	super.registerLida(lida);
	draw();
}
}
