package edu.memphis.ccrg.lida.proceduralmemory;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Before;

import edu.memphis.ccrg.lida.framework.shared.LidaElementFactory;
import edu.memphis.ccrg.lida.framework.shared.Node;
import edu.memphis.ccrg.lida.framework.shared.NodeStructure;
import edu.memphis.ccrg.lida.framework.shared.NodeStructureImpl;

public class BasicSchemeActivationBehaviorTest extends TestCase {
	
	private static LidaElementFactory factory = LidaElementFactory.getInstance();
	private Node node1, node2, node3;
	private NodeStructure broadcast;
	private Map<Node, Set<Scheme>>  map;
	private MockProceduralMemory pm;
	private Scheme a, b, c;
	
	@Override
	@Before
	public void setUp() throws Exception {	
		pm = new MockProceduralMemory();
		
		node1 = factory.getNode();
		node1.setActivation(1.0);
		node2 = factory.getNode();
		node2.setActivation(1.0);
		node3 = factory.getNode();
		node3.setActivation(1.0);

		map = new HashMap<Node, Set<Scheme>>();
		
		a = new SchemeImpl("", 1, null);
		b = new SchemeImpl("", 2, null);
		c = new SchemeImpl("", 3, null);
		
		Set<Scheme> node1Set = new HashSet<Scheme>();
		Set<Scheme> node2Set = new HashSet<Scheme>();
		Set<Scheme> node3Set = new HashSet<Scheme>();
		
		NodeStructure context = new NodeStructureImpl();
		context.addDefaultNode(node1);
		a.setContext(context);
		node1Set.add(a);
		
		context = new NodeStructureImpl();
		context.addDefaultNode(node1);
		context.addDefaultNode(node2);
		b.setContext(context);
		node1Set.add(b);
		node2Set.add(b);
		
		context = new NodeStructureImpl();
		context.addDefaultNode(node1);
		context.addDefaultNode(node2);
		context.addDefaultNode(node3);
		c.setContext(context);
		node1Set.add(c);
		node2Set.add(c);
		node3Set.add(c);
		
		map.put(node1, node1Set);
		map.put(node2, node2Set);
		map.put(node3, node3Set);
	}	
	public void test1(){
		SchemeActivationBehavior behavior = new BasicSchemeActivationBehavior(pm);
		
		broadcast = new NodeStructureImpl();
		broadcast.addDefaultNode(node1);
		broadcast.addDefaultNode(node2);
		broadcast.addDefaultNode(node3);	
		
		behavior.setSchemeSelectionThreshold(1.0);
		
		behavior.activateSchemesWithBroadcast(broadcast, map);
		
		Collection<Scheme> instantiated = pm.getTestInstantiated();
		assertTrue(instantiated.size()+"",instantiated.size() == 3);
		assertTrue(instantiated.contains(a));
		assertTrue(instantiated.contains(b));
		assertTrue(instantiated.contains(c));
	}
	
	public void test2(){
		SchemeActivationBehavior behavior = new BasicSchemeActivationBehavior(pm);
		
		broadcast = new NodeStructureImpl();
		broadcast.addDefaultNode(node1);
		broadcast.addDefaultNode(node2);
		
		behavior.setSchemeSelectionThreshold(1.0);
		behavior.activateSchemesWithBroadcast(broadcast, map);
		
		Collection<Scheme> instantiated = pm.getTestInstantiated();
		assertTrue(instantiated.size()+"",instantiated.size() == 2);
		assertTrue(instantiated.contains(a));
		assertTrue(instantiated.contains(b));
		assertFalse(instantiated.contains(c));
	}

	public void test3(){
		SchemeActivationBehavior behavior = new BasicSchemeActivationBehavior(pm);
		
		broadcast = new NodeStructureImpl();
		broadcast.addDefaultNode(node1);
		broadcast.addDefaultNode(node2);
		
		behavior.setSchemeSelectionThreshold(0.5);
		behavior.activateSchemesWithBroadcast(broadcast, map);
		
		Collection<Scheme> instantiated = pm.getTestInstantiated();
		assertTrue(instantiated.size()+"",instantiated.size() == 3);
		assertTrue(instantiated.contains(a));
		assertTrue(instantiated.contains(b));
		assertTrue(instantiated.contains(c));
	}
	
	public void test4(){
		SchemeActivationBehavior behavior = new BasicSchemeActivationBehavior(pm);
		
		broadcast = new NodeStructureImpl();
		broadcast.addDefaultNode(node2);
		
		behavior.setSchemeSelectionThreshold(0.4);
		behavior.activateSchemesWithBroadcast(broadcast, map);
		
		Collection<Scheme> instantiated = pm.getTestInstantiated();
		assertTrue(instantiated.size()+"",instantiated.size() == 1);
		assertFalse(instantiated.contains(a));
		assertTrue(instantiated.contains(b));
		assertFalse(instantiated.contains(c));
	}
	
	
}