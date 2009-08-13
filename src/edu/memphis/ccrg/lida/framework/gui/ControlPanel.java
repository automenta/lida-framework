/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ControlPanel.java
 *
 * Created on 12/07/2009, 09:26:00
 */
package edu.memphis.ccrg.lida.framework.gui;

import javax.swing.JPanel;
import javax.swing.JSlider;

import edu.memphis.ccrg.lida.framework.Lida;
import edu.memphis.ccrg.lida.framework.gui.commands.Command;
import edu.memphis.ccrg.lida.framework.gui.commands.SetTimeScaleCommand;

/**
 *
 * @author Javier Snaider
 */
public class ControlPanel extends javax.swing.JPanel implements LidaPanel,FrameworkGuiEventListener {

	private static final long serialVersionUID = 1L;
	private LidaGuiController controller;
    private Lida lida;
    
	boolean isPaused = true;
	private int sliderMin = 15;
	private int sliderMax = 350;
	private int sliderStartValue = 100;

    /** Creates new form ControlPanel */
    public ControlPanel() {

    	initComponents();

        minSleepTimeLabel.setText(sliderMin+" ms");
        maxSleepTimeLabel.setText(sliderMax+" ms");
        sleepTimeTextField.setText(this.sliderStartValue + "");

    	//actionSelection = as;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        startPauseButton = new javax.swing.JButton();
        resetEnvironmentButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        threadCountTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        sleepTimeTextField = new javax.swing.JTextField();
        speedSlider = new javax.swing.JSlider();
        minSleepTimeLabel = new javax.swing.JLabel();
        maxSleepTimeLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        addTicksButton = new javax.swing.JButton();
        sleepTimeTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TicksEnabledCheckBox = new javax.swing.JCheckBox();

        startPauseButton.setText("Start/Pause");
        startPauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startPauseButtonActionPerformed(evt);
            }
        });

        resetEnvironmentButton.setText("Reset Environment");
        resetEnvironmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetEnvironmentButtonClicked(evt);
            }
        });

        quitButton.setText("Quit");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 14));
        jLabel1.setText("LIDA Control Panel");

        jLabel4.setText("Thread Count");

        threadCountTextField.setText("--");

        jLabel7.setText("System Status");

        statusLabel.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        statusLabel.setText("Paused");

        jLabel2.setText("Ticks Scale (ms)");

        sleepTimeTextField.setText("--");

        speedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                speedSliderStateChanged(evt);
            }
        });

        minSleepTimeLabel.setText("Min");

        maxSleepTimeLabel.setText("Max");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        addTicksButton.setText("add ticks");

        sleepTimeTextField1.setText("--");

        jLabel5.setText("Ticks:");

        TicksEnabledCheckBox.setText("Ticks Mode Enabled");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sleepTimeTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addTicksButton, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(TicksEnabledCheckBox)
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(TicksEnabledCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sleepTimeTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addTicksButton)
                    .addComponent(jLabel5)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(resetEnvironmentButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quitButton)
                .addContainerGap(119, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(threadCountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(startPauseButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(speedSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(minSleepTimeLabel)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(sleepTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maxSleepTimeLabel)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(threadCountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(startPauseButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(minSleepTimeLabel)
                            .addComponent(maxSleepTimeLabel)
                            .addComponent(jLabel2)
                            .addComponent(sleepTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetEnvironmentButton)
                    .addComponent(quitButton))
                .addContainerGap())
        );
    }// </editor-fold>

    private void startPauseButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
    	isPaused = !isPaused;
		if(isPaused){
			statusLabel.setText("PAUSED");
		controller.executeCommand("pauseRunningThreads", null);
		}else{
			statusLabel.setText("RUNNING");
			controller.executeCommand("resumeRunningThreads", null);
		}
		refresh();
    }                                                

    private void resetEnvironmentButtonClicked(java.awt.event.ActionEvent evt) {                                               
     	//environment.resetEnvironment();
    	controller.executeCommand("resetEnvironment", null);
    }                                              

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	statusLabel.setText("QUITTING");
    	controller.executeCommand("quitAll", null);
    }                                          

    private void speedSliderStateChanged(javax.swing.event.ChangeEvent evt) {                                         
   	 	JSlider source = (JSlider)evt.getSource();
   	 	if(!source.getValueIsAdjusting()){
   	 		int sleepTime = (int)source.getValue();
   	 		sleepTimeTextField.setText(sleepTime + "");
   	 		
   	 		//Another way to execute commands
   	 		Command command= new SetTimeScaleCommand();
   	 		command.setParameter("timeScale", sleepTime);
   	 		controller.executeCommand(command);
   	 		refresh();
   	 	}    
    }                                        
    
    // Variables declaration - do not modify
    private javax.swing.JCheckBox TicksEnabledCheckBox;
    private javax.swing.JButton addTicksButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel maxSleepTimeLabel;
    private javax.swing.JLabel minSleepTimeLabel;
    private javax.swing.JButton quitButton;
    private javax.swing.JButton resetEnvironmentButton;
    private javax.swing.JTextField sleepTimeTextField;
    private javax.swing.JTextField sleepTimeTextField1;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JButton startPauseButton;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextField threadCountTextField;
    // End of variables declaration

    public void registrerLidaGuiController(LidaGuiController lgc) {
        controller = lgc;
    }

    public void display(Object o) {
    }

    public void refresh() {
//    	isPaused = lida.getTaskManager().isTasksPaused();
//    	if(isPaused)
//    		statusLabel.setText("PAUSED");
//    	else
//    		statusLabel.setText("RUNNING");
//    	//
//    	String threadCount = "";
//        threadCount = (lida.getTaskManager().getSpawnedTaskCount() +
//        			   lida.getSbCodeletDriver().getSpawnedTaskCount() +
//        			   lida.getAttentionDriver().getSpawnedTaskCount()) + "";
//        threadCountTextField.setText(threadCount);
    }

    public JPanel getPanel() {
        return this;
    }

    public int moduleSuported() {
        return FrameworkGuiEvent.FRAMEWORK;
    }

    public void receiveGuiEvent(FrameworkGuiEvent event) {
    }
    
	public void registrerLida(Lida lida) {
		this.lida=lida;		
	}
    
}//class