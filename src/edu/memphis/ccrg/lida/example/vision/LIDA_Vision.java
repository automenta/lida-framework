/* 
 * RunLida.java - Initializes, starts, and ends the threads 
 * that run the main LIDA components.  Sets up the shared memory 
 * that the threads use to pass data. 
 */
package edu.memphis.ccrg.lida.example.vision;

import java.util.List;
import java.lang.Thread; 
import java.util.ArrayList;
import edu.memphis.ccrg.lida.actionSelection.ActionSelectionImpl;
import edu.memphis.ccrg.lida.attention.AttentionDriver;
import edu.memphis.ccrg.lida.declarativeMemory.DeclarativeMemoryImpl;
import edu.memphis.ccrg.lida.example.vision.environ_SM.VisionEnvironment;
import edu.memphis.ccrg.lida.example.vision.environ_SM.VisionSensoryContent;
import edu.memphis.ccrg.lida.example.vision.environ_SM.VisionSensoryMemory;
import edu.memphis.ccrg.lida.example.vision.gui.CSMGui;
import edu.memphis.ccrg.lida.example.vision.gui.ControlPanelGui;
import edu.memphis.ccrg.lida.example.vision.gui.NodeLinkFlowGui;
import edu.memphis.ccrg.lida.example.vision.gui.VisualFieldGui;
import edu.memphis.ccrg.lida.example.vision.pam.PAM_Input;
import edu.memphis.ccrg.lida.framework.FrameworkTimer;
import edu.memphis.ccrg.lida.framework.Stoppable;
import edu.memphis.ccrg.lida.framework.ThreadSpawner;
import edu.memphis.ccrg.lida.globalworkspace.GlobalWorkspace;
import edu.memphis.ccrg.lida.globalworkspace.GlobalWorkspaceImpl;
import edu.memphis.ccrg.lida.perception.PAMDriver;
import edu.memphis.ccrg.lida.perception.PerceptualAssociativeMemoryImpl;
import edu.memphis.ccrg.lida.proceduralMemory.ProceduralMemoryDriver;
import edu.memphis.ccrg.lida.sensoryMemory.SensoryMemoryDriver;
import edu.memphis.ccrg.lida.serialization.GlobalWorkspace_Input;
import edu.memphis.ccrg.lida.shared.NodeStructureImpl;
import edu.memphis.ccrg.lida.transientEpisodicMemory.TEMImpl;
import edu.memphis.ccrg.lida.workspace.BroadcastBuffer.BroadcastBufferDriver;
import edu.memphis.ccrg.lida.workspace.BroadcastBuffer.BroadcastBufferImpl;
import edu.memphis.ccrg.lida.workspace.currentSituationalModel.CSMDriver;
import edu.memphis.ccrg.lida.workspace.currentSituationalModel.CurrentSituationalModelImpl;
import edu.memphis.ccrg.lida.workspace.episodicBuffer.EpisodicBufferDriver;
import edu.memphis.ccrg.lida.workspace.episodicBuffer.EpisodicBufferImpl;
import edu.memphis.ccrg.lida.workspace.main.WorkspaceImpl;
import edu.memphis.ccrg.lida.workspace.perceptualBuffer.PerceptualBufferDriver;
import edu.memphis.ccrg.lida.workspace.perceptualBuffer.PerceptualBufferImpl;
import edu.memphis.ccrg.lida.workspace.structureBuildingCodelets.StructureBuildingCodeletDriver;

public class LIDA_Vision implements ThreadSpawner, Runnable{

	//Perception
	private VisionEnvironment environment;
	private VisionSensoryMemory sensoryMemory;
	private PerceptualAssociativeMemoryImpl pam;
	//Episodic memory
	private TEMImpl tem;
	private DeclarativeMemoryImpl declarativeMemory;
	//Workspace
	private WorkspaceImpl workspace;
	private PerceptualBufferImpl perceptBuffer;
	private EpisodicBufferImpl episodicBuffer;
	private BroadcastBufferImpl broadcastBuffer;
	private CurrentSituationalModelImpl csm = new CurrentSituationalModelImpl();
	//Attention
	private GlobalWorkspace globalWksp;
	//Action Selection
	private ActionSelectionImpl actionSelection;
	//Drivers
	private SensoryMemoryDriver sensoryMemoryDriver;
	private PAMDriver pamDriver;
	private PerceptualBufferDriver perceptBufferDriver;
	private EpisodicBufferDriver episodicBufferDriver;
	private BroadcastBufferDriver broadcastBufferDriver;
	private StructureBuildingCodeletDriver sbCodeletDriver;
	private CSMDriver csmDriver;
	private AttentionDriver attnDriver;
	private ProceduralMemoryDriver proceduralMemDriver;		
	//GUIs
	private VisualFieldGui visualFieldGui = new VisualFieldGui();
	private CSMGui csmGui = new CSMGui();
	private NodeLinkFlowGui nodeLinkFlowGui;
	//Threads & thread control
	private List<Thread> threads = new ArrayList<Thread>();
	private List<Stoppable> drivers = new ArrayList<Stoppable>();
	//TODO: Move these to a config. file
	private boolean startRunning = false;
	private int threadSleepTime = 150;
	private FrameworkTimer timer = new FrameworkTimer(startRunning, threadSleepTime);
	
	public static void main(String[] args){
		new LIDA_Vision().run();
	}//method

	public void run(){
		initEnvironmentThread();
		//perception
		initSensoryMemoryThread();
		initPAMThread();
		
		//episodic memories
		initTransientEpisodicMemory();
		initDeclarativeMemory();
		
		//working memory
		initPerceptualBufferThread();
		initEpisodicBufferThread();
		initBroadcastBufferThread();
		initCSMThread();
		initSBCodeletsThread();
		initWorkspace();		
		
		//attention
		initAttentionThread();
		initGlobalWorkspace();
		
		//action selection
		initProceduralMemoryThread();
		initActionSelectionThread();
		
		//GUI last
		initGui();
		//Define Observers
		defineInterThreadCommunication();
		//Start everything up, threads are terminated via GUI
		startThreads();
	}//method

	private void initEnvironmentThread(){
		environment = new VisionEnvironment(timer, 10, 10);				
		threads.add(new Thread(environment, "SIMULATION_THREAD"));   
		drivers.add(environment);	
	}
	private void initSensoryMemoryThread(){
		sensoryMemory = new VisionSensoryMemory();		
		sensoryMemoryDriver = new SensoryMemoryDriver(sensoryMemory, timer);
		Thread smThread = new Thread(sensoryMemoryDriver, "SM_THREAD");
		threads.add(smThread);   
		drivers.add(sensoryMemoryDriver);				
	}
	private void initPAMThread(){
		pam = new PerceptualAssociativeMemoryImpl(new VisionSensoryContent());
		String pamInputPath = "";
		PAM_Input reader = new PAM_Input();
		reader.read(pam, pamInputPath);
		//PAM THREAD		
		pamDriver = new PAMDriver(pam, timer);
		Thread pamThread = new Thread(pamDriver, "PAM_THREAD");
		threads.add(pamThread);   
		drivers.add(pamDriver);
	}//method
	
	private void initPerceptualBufferThread(){
		perceptBuffer = new PerceptualBufferImpl();	
	    perceptBufferDriver = new PerceptualBufferDriver(perceptBuffer, timer);
		Thread pBufferThread = new Thread(perceptBufferDriver, "PBUFFER");
		threads.add(pBufferThread);   
		drivers.add(perceptBufferDriver);
	}
	private void initEpisodicBufferThread(){
		episodicBuffer = new EpisodicBufferImpl();
		episodicBufferDriver = new EpisodicBufferDriver(episodicBuffer, timer);
		Thread ebThread = new Thread(episodicBufferDriver, "EBUFFER");	
		threads.add(ebThread);   
		drivers.add(episodicBufferDriver);
	}
	private void initBroadcastBufferThread(){
		broadcastBuffer = new BroadcastBufferImpl();
		broadcastBufferDriver = new BroadcastBufferDriver(broadcastBuffer, timer);
		Thread pbroadsThread = new Thread(perceptBufferDriver, "PBROADS");	
		threads.add(pbroadsThread);   
		drivers.add(broadcastBufferDriver);		
	}
	private void initSBCodeletsThread() {
		sbCodeletDriver = new StructureBuildingCodeletDriver(timer);		
		Thread codeletThread = new Thread(sbCodeletDriver, "CODELETS_THREAD");
		threads.add(codeletThread);   
		drivers.add(sbCodeletDriver);			
	}
	private void initCSMThread(){
		csmDriver = new CSMDriver(timer, csm, csmGui);
		Thread csmThread = new Thread(csmDriver, "CSM_THREAD");
		threads.add(csmThread);
		drivers.add(csmDriver);
	}
	private void initDeclarativeMemory() {
		declarativeMemory = new DeclarativeMemoryImpl();
		//TODO will we use a driver?
	}
	private void initTransientEpisodicMemory() {
		tem = new TEMImpl(new NodeStructureImpl());
		//TODO will we use a driver?
	}
	private void initWorkspace() {
		workspace = new WorkspaceImpl(perceptBuffer, episodicBuffer, 
									  broadcastBuffer, csm);		
	}//method
	
	private void initAttentionThread(){
		attnDriver = new AttentionDriver(timer, csm, globalWksp);
		Thread attnThread = new Thread(attnDriver, "ATTN_DRIVER");
		threads.add(attnThread);
		drivers.add(attnDriver);
	}
	private void initGlobalWorkspace() {
		globalWksp = new GlobalWorkspaceImpl();
		String globalWorkspaceInputPath = "";
		GlobalWorkspace_Input reader = new GlobalWorkspace_Input();
		reader.read(globalWksp, globalWorkspaceInputPath);
	}//method
	
	private void initProceduralMemoryThread(){
		proceduralMemDriver = new ProceduralMemoryDriver(timer);
		Thread procThread = new Thread(proceduralMemDriver, "PROCEDURAL_DRIVER");
		threads.add(procThread);
		drivers.add(proceduralMemDriver);
	}
	private void initActionSelectionThread() {
		actionSelection = new ActionSelectionImpl();
		
	}
	private void initGui() {	
		//***For basic control of the system***
		new ControlPanelGui(timer, this, sbCodeletDriver, environment, actionSelection).setVisible(true);	
		
		//***To view the simulation***
		environment.addEnvironmentListener(visualFieldGui);
		visualFieldGui.setVisible(true);
		
		//***For GUI action control***
		//ActionControlGui acg = new ActionControlGui(environment, actionSelection);
		//acg.setVisible(true);
		
		//***GUI Showing counts of active nodes and links in the modules ***
		nodeLinkFlowGui = new NodeLinkFlowGui();
		pamDriver.addFlowGui(nodeLinkFlowGui);
		perceptBuffer.addFlowGui(nodeLinkFlowGui);
		episodicBufferDriver.addFlowGui(nodeLinkFlowGui);
		broadcastBufferDriver.addFlowGui(nodeLinkFlowGui);
		sbCodeletDriver.addFlowGui(nodeLinkFlowGui);
		csmDriver.addFlowGui(nodeLinkFlowGui);
		proceduralMemDriver.addFlowGui(nodeLinkFlowGui);
		//nodeLinkFlowGui.setVisible(true);
		
		//***GUI to see the contents of the CSM***
	//	csm.addCSMListener(csmGui);
		//csmGui.setVisible(true);
	}//method
	
	private void defineInterThreadCommunication(){
		environment.addEnvironmentListener(sensoryMemory);
		//sensoryMemory.addSensoryListener(sma);
		//sma.addSensoryMotorListener(sensoryMemory);
		sensoryMemory.addSensoryListener(pam);
		pam.addPAMListener(workspace);
		
		perceptBuffer.addPBufferListener(workspace);
		episodicBuffer.addEBufferListener(workspace);
		broadcastBuffer.addPBroadsListener(workspace);
		csm.addCSMListener(workspace);
		sbCodeletDriver.addWorkspace(workspace);
		
		workspace.addCueListener(declarativeMemory);
		//workspace.addCueListener(tem);
		workspace.addCodeletListener(sbCodeletDriver);
		workspace.addPamListener(pam);
			
		actionSelection.addBehaviorListener(workspace);		
		globalWksp.addBroadcastListener(pam);
		globalWksp.addBroadcastListener(workspace);
		globalWksp.addBroadcastListener(tem);
		globalWksp.addBroadcastListener(attnDriver);
		globalWksp.addBroadcastListener(proceduralMemDriver);
		proceduralMemDriver.addProceduralMemoryListener(actionSelection);
		actionSelection.addBehaviorListener(environment);
	}//method
	
	private void startThreads(){
		int size = threads.size();
		for(int i = 0; i < size; i++){
			Thread t = threads.get(i);
			if(t != null)
				t.start();
			else
				System.out.println("There was a null thread object in start");
		}//for
	}//method
	
	/**
	 * Stop in reverse order of starting
	 */	
	public void stopThreads(){		
		int size = drivers.size();
		for(int i = 0; i < size; i++){			
			Stoppable s = drivers.get(size - 1 - i);
			if(s != null)
				s.stopRunning();					
		}//for	
		try{Thread.sleep(200);
		}catch(Exception e){}
		System.exit(0);//Kills gui windows
	}//method	

	/**
	 * For ControlGUI to display thread count
	 * 
	 * @return number of threads started by this class
	 */
	public int getThreadCount() {
		return threads.size();
	}//method

}//class