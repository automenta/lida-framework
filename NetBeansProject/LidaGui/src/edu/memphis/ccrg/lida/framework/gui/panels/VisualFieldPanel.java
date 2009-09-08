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

import edu.memphis.ccrg.lida.framework.gui.*;
import javax.swing.JPanel;

//import edu.memphis.ccrg.lida.environment.Environment;
import edu.memphis.ccrg.lida.framework.Lida;
import edu.memphis.ccrg.lida.framework.Module;

/**
 *
 * @author Javier Snaider
 */
public class VisualFieldPanel extends  LidaPanelImpl implements FrameworkGuiEventListener{

	private static final long serialVersionUID = 13L;
    
    /** Creates new form VisualFieldPanel */
    public VisualFieldPanel() {
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18));
        jLabel1.setText("Sensory Stimulus");
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

    public Module moduleSuported() {
        return Module.environment;
    }

	public void receiveGuiEvent(FrameworkGuiEvent event) {
		double [][] smc=(double[][])event.getData();
		String s=convertToString(smc);
		jTextArea1.setText(s);	
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
	}// method

}
