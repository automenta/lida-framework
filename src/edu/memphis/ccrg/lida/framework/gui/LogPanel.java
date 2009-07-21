/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LogPanel.java
 *
 * Created on 12/07/2009, 09:42:13
 */
package edu.memphis.ccrg.lida.framework.gui;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JPanel;

import edu.memphis.ccrg.lida.framework.Lida;

/**
 *
 * @author Javier Snaider
 */
public class LogPanel extends javax.swing.JPanel implements LidaPanel {

    private LidaGuiController controller;
	private Lida lida;

    private String logName = "lida"; 
    private Logger logger = Logger.getLogger(logName); 

    /** Creates new form LogPanel */
    public LogPanel() {
        initComponents();
        logger.addHandler(new GuiLogHandler()); 
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        loggingText = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        clearButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        loggingText.setColumns(20);
        loggingText.setRows(5);
        jScrollPane1.setViewportView(loggingText);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setMinimumSize(new java.awt.Dimension(100, 60));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Log");

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 297, Short.MAX_VALUE)
                .addComponent(clearButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearButton)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        loggingText.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea loggingText;
    // End of variables declaration//GEN-END:variables
    
    public void registrerLidaGuiController(LidaGuiController lgc) {
        controller = lgc;
    }

    public void display(Object o) {
        if (o instanceof String) {
            String s = (String) o;
            loggingText.append(s);
        }
    }

    public void refresh() {
    }

    public JPanel getPanel() {
        return this;
    }

    public int moduleSuported() {
        return 0;
    }
    public class GuiLogHandler extends Handler {
        String logMessages = new String("");
        java.text.DateFormat df = java.text.DateFormat.getDateTimeInstance();
        
        /** Creates a new instance of GuiLogHandler */    
        public GuiLogHandler() {
        }

        public void publish(LogRecord logRecord){
        	Formatter f = getFormatter();
        	if(f == null){
        		f=new SimpleFormatter();
        	}
        	   	
//             logMessages = logRecord.getSequenceNumber() + " " + 
//                          dateString + " " + 
//                          logRecord.getLevel() + "\n" + 
//                          logRecord.getMessage() + "\n" +
//                          logRecord.getSourceClassName() + " " + 
//                          logRecord.getSourceMethodName() + "\n";
            loggingText.append(f.format(logRecord));
        }

        public void flush(){
        }
        
        public void close(){
            logMessages = null;
        }
    }
	public void registrerLida(Lida lida) {
		this.lida=lida;
		
	}

}