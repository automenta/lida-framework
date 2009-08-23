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
import edu.memphis.ccrg.lida.pam.PerceptualAssociativeMemoryImpl;
import edu.memphis.ccrg.lida.shared.Link;
import edu.memphis.ccrg.lida.shared.Linkable;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;


/**
 *
 * @author Javier
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
    
@SuppressWarnings("unchecked")
private void draw(){
	// The Layout<V, E> is parameterized by the vertex and edge types
	
	Layout<Integer, String> layout = new CircleLayout( getGraph());
	layout.setSize(new Dimension(300,300)); // sets the initial size of the space
	// The BasicVisualizationServer<V,E> is parameterized by the edge types
	VisualizationViewer<Integer,String> vv =
	new VisualizationViewer<Integer,String>(layout);
	vv.setPreferredSize(new Dimension(350,350)); //Sets the viewing area size
	// Show vertex and edge labels
	vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
	vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
	// Create a graph mouse and add it to the visualization component
	DefaultModalGraphMouse gm = new DefaultModalGraphMouse<Object, Object>();
	gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
	vv.setGraphMouse(gm);
	jScrollPane1.setViewportView(vv);
}
private Graph<?, ?> getGraph(){
	
//	NodeStructure ns=new NodeStructureImpl();
//	ns.addNode(new NodeImpl());
//	ns.addNode(new NodeImpl());
//	ns.addNode(new NodeImpl());
//	ns.addNode(new NodeImpl());

//	Graph<Integer, String> g2 = new SparseMultigraph<Integer, String>();
//
//	g2.addVertex((Integer)1);
//	g2.addVertex((Integer)2);
//	g2.addVertex((Integer)3);
//	g2.addEdge("Edge-A", 1,3);
//	g2.addEdge("Edge-B", 2,3, EdgeType.DIRECTED);
//	g2.addEdge("Edge-C", 3, 2, EdgeType.DIRECTED);
//	g2.addEdge("Edge-P", 2,3); // A parallel edge
//	return g2;
	Graph<Linkable,Link> g=new NodeStructureGuiAdapter(((PerceptualAssociativeMemoryImpl)lida.getPam()).getNodeStructure());
return g;
}
public void registrerLida(Lida lida){
	super.registrerLida(lida);
	draw();
}
}
