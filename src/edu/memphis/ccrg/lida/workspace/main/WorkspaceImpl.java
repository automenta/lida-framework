package edu.memphis.ccrg.lida.workspace.main;

import java.util.ArrayList;
import java.util.List;

import edu.memphis.ccrg.lida.actionSelection.ActionContent;
import edu.memphis.ccrg.lida.actionSelection.ActionSelectionListener;
import edu.memphis.ccrg.lida.declarativeMemory.DeclarativeMemory;
import edu.memphis.ccrg.lida.declarativeMemory.DeclarativeMemoryContent;
import edu.memphis.ccrg.lida.declarativeMemory.DeclarativeMemoryListener;
import edu.memphis.ccrg.lida.globalworkspace.BroadcastContent;
import edu.memphis.ccrg.lida.globalworkspace.BroadcastListener;
import edu.memphis.ccrg.lida.perception.PAMListener;
import edu.memphis.ccrg.lida.perception.PerceptualAssociativeMemory;
import edu.memphis.ccrg.lida.shared.NodeStructure;
import edu.memphis.ccrg.lida.transientEpisodicMemory.TransientEpisodicMemory;
import edu.memphis.ccrg.lida.transientEpisodicMemory.TransientEpisodicMemoryContent;
import edu.memphis.ccrg.lida.transientEpisodicMemory.TransientEpisodicMemoryListener;
import edu.memphis.ccrg.lida.workspace.currentSituationalModel.CSMListener;
import edu.memphis.ccrg.lida.workspace.currentSituationalModel.CurrentSituationalModel;
import edu.memphis.ccrg.lida.workspace.episodicBuffer.EpisodicBuffer;
import edu.memphis.ccrg.lida.workspace.episodicBuffer.EpisodicBufferContentImpl;
import edu.memphis.ccrg.lida.workspace.episodicBuffer.EpisodicBufferListener;
import edu.memphis.ccrg.lida.workspace.perceptualBuffer.PerceptualBuffer;
import edu.memphis.ccrg.lida.workspace.perceptualBuffer.PerceptualBufferContent;
import edu.memphis.ccrg.lida.workspace.perceptualBuffer.PerceptualBufferListener;
import edu.memphis.ccrg.lida.workspace.previousBroadcasts.PreviousBroadcasts;
import edu.memphis.ccrg.lida.workspace.previousBroadcasts.PreviousBroadcastsContentImpl;
import edu.memphis.ccrg.lida.workspace.previousBroadcasts.PreviousBroadcastsListener;
import edu.memphis.ccrg.lida.workspace.structureBuildingCodelets.CodeletReadable;
import edu.memphis.ccrg.lida.workspace.structureBuildingCodelets.CodeletsDesiredContent;
import edu.memphis.ccrg.lida.wumpusWorld.d_perception.NodeStructureRyan;

/**
 * 
 * TODO: Not yet tested.
 * 
 * @author ryanjmccall
 *
 */
public class WorkspaceImpl implements Workspace, PAMListener, 
									  TransientEpisodicMemoryListener, 
									  DeclarativeMemoryListener,
									  BroadcastListener, 
									  ActionSelectionListener, 
									  PerceptualBufferListener,
									  PreviousBroadcastsListener,
									  EpisodicBufferListener, 
									  CSMListener{
	
	//Workspace contains these components
	private PerceptualBuffer perceptualBuffer;
	private EpisodicBuffer episodicBuffer;
	private PreviousBroadcasts prevBroads;
	private CurrentSituationalModel csm;
	
	//These listeners listen to the Workspace
	private List<WorkspaceListener> listeners = new ArrayList<WorkspaceListener>();
	private WorkspaceListener sbCodeletWorkspaceListener;
	//private WorkspaceContent content;
	
	public WorkspaceImpl(PerceptualBuffer pb, EpisodicBuffer eb, PreviousBroadcasts pbroads, CurrentSituationalModel csm, 
						TransientEpisodicMemory tem, DeclarativeMemory dm, PerceptualAssociativeMemory pam){
		perceptualBuffer = pb;
		episodicBuffer = eb;
		prevBroads = pbroads;
		this.csm = csm;	
		
		//TODO: Either each component has a reference to the workspace and
		//      calls the workspace to do output _OR_
		// Each component sends the output itself (e.g. episodic buffer sends to PAM; CSM sends to TEM and DM)
		// 
		 
//		pb.addWorkspaceObjectReference(this);
//		eb.addWorkspaceObjectReference(this);
//		pbroads.addWorkspaceObjectReference(this);
//		csm.addWorkspaceObjectReference(this);		
	}//constructor
	
	//****Output from the Workspace to other modules
	public void addWorkspaceListener(WorkspaceListener listener) {
		listeners.add(listener);		
	}
	
	public void addCodeletWorkspaceListener(WorkspaceListener listener){
		sbCodeletWorkspaceListener = listener;
	}
	
	public void sendWorkspaceContent(WorkspaceContent content){
		for(WorkspaceListener l: listeners)
			l.receiveWorkspaceContent(content);
	}
	
	public void receivePBufferContent(WorkspaceContent w){
		sbCodeletWorkspaceListener.receiveWorkspaceContent(w);		
	}

	public void receivePrevBroadcastContent(WorkspaceContent w) {
		sbCodeletWorkspaceListener.receiveWorkspaceContent(w);			
	}

	public void receiveEBufferContent(WorkspaceContent w) {
		sbCodeletWorkspaceListener.receiveWorkspaceContent(w);			
	}

	public void receiveCSMContent(WorkspaceContent content) {
		sendWorkspaceContent(content);		
	}

	//****Input into the Workspace from other Modules
	public void receivePAMContent(WorkspaceContent pc) {
		perceptualBuffer.receivePAMContent(pc);		
	}
	public void receiveTEMContent(TransientEpisodicMemoryContent association) {
		episodicBuffer.receiveTEMContent(association);		
	}	
	public void receivenDMContent(DeclarativeMemoryContent association) {
		episodicBuffer.receivenDMContent(association);		
	}
	public void receiveBroadcast(BroadcastContent bc) {
		prevBroads.receiveBroadcast(bc);		
	}
	public void receiveBehaviorContent(ActionContent c) {
		// TODO: Implementing this is a long way off as of (3.30.09)		
	}

	/**
	 * Codelets use this
	 */
	public void addContentToCSM(WorkspaceContent updatedContent) {
		csm.addWorkspaceContent(updatedContent);		
	}

	/**
	 * for codelets to access the buffers
	 */
	public WorkspaceContent getCodeletDesiredContent(int moduleID, CodeletsDesiredContent soughtContent) {
		if(moduleID == Workspace.CSM){
			return csm.getCodeletsObjective(soughtContent);
		}else if(moduleID == Workspace.PBUFFER){
			return perceptualBuffer.getCodeletsObjective(soughtContent);
		}else if(moduleID == Workspace.EBUFFER){
			return episodicBuffer.getCodeletsObjective(soughtContent);
		}else if(moduleID == Workspace.PBROADS){
			return prevBroads.getCodeletsObjective(soughtContent);
		}
		return new NodeStructureRyan();
	}//method
	
}//class