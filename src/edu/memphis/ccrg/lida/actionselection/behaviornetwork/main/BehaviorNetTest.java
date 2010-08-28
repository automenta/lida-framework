/**
 * 
 */
package edu.memphis.ccrg.lida.actionselection.behaviornetwork.main;

import java.util.HashMap;
import java.util.Map;

import edu.memphis.ccrg.lida.actionselection.ActionSelectionDriver;
import edu.memphis.ccrg.lida.actionselection.ActionSelectionListener;
import edu.memphis.ccrg.lida.framework.shared.Node;
import edu.memphis.ccrg.lida.framework.shared.NodeFactory;
import edu.memphis.ccrg.lida.framework.shared.NodeStructure;
import edu.memphis.ccrg.lida.framework.shared.NodeStructureImpl;
import edu.memphis.ccrg.lida.framework.tasks.TaskSpawner;
import edu.memphis.ccrg.lida.globalworkspace.BroadcastContent;

/**
 * TODO move to another package 'test'
 * @author Ryan J McCall
 */
public class BehaviorNetTest implements ActionSelectionListener{

	public static void main(String[] args) {
		new BehaviorNetTest().run();				
	}

	private void run() {
		BehaviorNetworkImpl behaviorNet = new BehaviorNetworkImpl();
		behaviorNet.addActionSelectionListener(this);
		
		TaskSpawner taskSpawner = new ActionSelectionDriver();
		behaviorNet.setTaskSpawner(taskSpawner);
		
		Map<String, Double> params = new HashMap<String, Double>();
		//params.put("", value)		
		behaviorNet.init(params);
		
		NodeFactory factory = NodeFactory.getInstance();
		NodeStructure bc = new NodeStructureImpl();
		Node apple = factory.getNode("NodeImpl", "apple");
		Node banana = factory.getNode("NodeImpl", "banana");
		bc.addNode(apple);
		bc.addNode(banana);
		
		int timesToSendBroadcast = 100;
		for(int i = 0; i < timesToSendBroadcast; i++)
			behaviorNet.receiveBroadcast((BroadcastContent) bc);
	}

	@Override
	public void receiveActionId(long id) {
		System.out.println("Received action " + id);
	}

}
