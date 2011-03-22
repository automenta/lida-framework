package edu.memphis.ccrg.lida.workspace;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import edu.memphis.ccrg.lida.episodicmemory.CueListener;
import edu.memphis.ccrg.lida.framework.LidaModuleImpl;
import edu.memphis.ccrg.lida.framework.ModuleListener;
import edu.memphis.ccrg.lida.framework.ModuleName;
import edu.memphis.ccrg.lida.framework.shared.Node;
import edu.memphis.ccrg.lida.framework.shared.NodeImpl;
import edu.memphis.ccrg.lida.framework.shared.NodeStructure;
import edu.memphis.ccrg.lida.framework.shared.NodeStructureImpl;
import edu.memphis.ccrg.lida.workspace.workspaceBuffer.WorkspaceBuffer;
import edu.memphis.ccrg.lida.workspace.workspaceBuffer.WorkspaceBufferImpl;

public class CueBackgroundTaskTest {

	@Test
	public final void testRunThisLidaTask() {
		CueBackgroundTask cbt = new CueBackgroundTask();
		

		// Initialize with assigned value
		Map<String, Object> mapParas = new HashMap<String, Object>();
		mapParas.put("workspace.actThreshold", 0.5);
		cbt.init(mapParas);
		// Testing of method init();	
		cbt.init();
		
		//step 3-1:
		//Create 3 nodes and add them into a node structure
		NodeStructure ns = new NodeStructureImpl();
		
		Node n1 = new NodeImpl();
		n1.setId(2);
		n1.setActivation(0.2);
		ns.addDefaultNode(n1);
		
		Node n2 = new NodeImpl();
		n2.setId(6);
		n2.setActivation(0.6);
		ns.addDefaultNode(n2);
		
		Node n3 = new NodeImpl();
		n3.setId(8);
		n3.setActivation(0.8);
		ns.addDefaultNode(n3);

		//Step 3-2:
		//Create workspaceBuffer and add them into mockWorkspace
		WorkspaceBuffer perceptualBuffer = new WorkspaceBufferImpl();
		perceptualBuffer.setModuleName(ModuleName.PerceptualBuffer);
		
		mockWorkspace wMoudle = new mockWorkspace();
		wMoudle.addSubModule(perceptualBuffer);
		
		//Step 3-3:
		// Add node structure into workspaceBuffer of percetualBuffer
		((NodeStructure)wMoudle.getSubmodule(ModuleName.PerceptualBuffer)
				.getModuleContent()).mergeWith(ns);
		
        //Testing of setAssociateModule()
		cbt.setAssociatedModule(wMoudle, 0);
		
		//Run method of target class
		cbt.runThisLidaTask();
		
	}

	@Test
	public final void testInit() {
		//Init() be tested in testRunThisLidaTask method above with testing of 
		//RunThisLidaTask() together.
	}

	@Test
	public final void testSetAssociatedModule() {
		//SetAssociatedModule() be tested in testRunThisLidaTask method above with testing of 
		//RunThisLidaTask() together.
	}

	@Test
	public final void testToString() {
		CueBackgroundTask cbt = new CueBackgroundTask();
		String sAns = "CueBackgroundTask";
		
		assertEquals("Problem with class CueBackgroundTask for testToString()", cbt.toString(), sAns);
	}

}

class mockWorkspace  extends LidaModuleImpl implements Workspace {

	@Override
	public void cueEpisodicMemories(NodeStructure ns) {
		// Node (Id = 2) is removed cause activation(0.2) < actThreshold(0.5),
		// so here are only node (Id == 6) and node (Id == 8).
		assertTrue("Problem with class RunThisLidaTask for testRunThisLidaTask()",
				(ns.getNode(2) == null)&&(ns.getNode(6) != null)&&(ns.getNode(8) != null)
				&&(ns.getNodeCount() == 2));
		
	}
	@Override
	public void addListener(ModuleListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCueListener(CueListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addWorkspaceListener(WorkspaceListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getModuleContent(Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
}
