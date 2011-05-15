package edu.memphis.ccrg.lida.framework.shared;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.memphis.ccrg.lida.attentioncodelets.BasicAttentionCodelet;
import edu.memphis.ccrg.lida.framework.initialization.AgentStarter;
import edu.memphis.ccrg.lida.framework.initialization.CodeletDef;
import edu.memphis.ccrg.lida.framework.initialization.ConfigUtils;
import edu.memphis.ccrg.lida.framework.initialization.FactoriesDataXmlLoader;
import edu.memphis.ccrg.lida.framework.initialization.LinkableDef;
import edu.memphis.ccrg.lida.framework.initialization.StrategyDef;
import edu.memphis.ccrg.lida.framework.shared.activation.Activatible;
import edu.memphis.ccrg.lida.framework.strategies.DecayStrategy;
import edu.memphis.ccrg.lida.framework.strategies.DefaultExciteStrategy;
import edu.memphis.ccrg.lida.framework.strategies.ExciteStrategy;
import edu.memphis.ccrg.lida.framework.strategies.LinearDecayStrategy;
import edu.memphis.ccrg.lida.framework.strategies.SigmoidDecayStrategy;
import edu.memphis.ccrg.lida.framework.strategies.SigmoidExciteStrategy;
import edu.memphis.ccrg.lida.framework.strategies.Strategy;
import edu.memphis.ccrg.lida.framework.tasks.Codelet;
import edu.memphis.ccrg.lida.pam.PamLinkImpl;
import edu.memphis.ccrg.lida.pam.PamNode;
import edu.memphis.ccrg.lida.pam.PamNodeImpl;
import edu.memphis.ccrg.lida.workspace.structurebuildingcodelets.BasicStructureBuildingCodelet;

public class ElementFactoryTest {

	private static ElementFactory factory;
	private Node node1, node2;
	private PamNode pamNode1;
	private Link link1;
	private LinkCategory category1;

	@BeforeClass
	public static void setUpBeforeClass() {
		factory = ElementFactory.getInstance();
		FactoriesDataXmlLoader factoryLoader = new FactoriesDataXmlLoader();
		Properties prop = ConfigUtils
				.loadProperties(AgentStarter.DEFAULT_PROPERTIES_PATH);
		factoryLoader.loadFactoriesData(prop);
	}
	
	@Before
	public void setUp(){
		node1 = factory.getNode();
		node2 = factory.getNode();
		pamNode1 = (PamNode) factory.getNode("PamNodeImpl");
		category1 = (LinkCategory)factory.getNode("PamNodeImpl");
		link1 = factory.getLink(node1, node2, category1);
		
		node1.setActivation(0.11);
		node1.setActivatibleRemovalThreshold(0.22);
		node1.setDesirability(0.33);
		node1.setLabel("blah");
		node1.setGroundingPamNode(pamNode1);
	}

	@Test
	public void testGetInstance() {
		ElementFactory factory1 = ElementFactory.getInstance();
		assertTrue(factory1 != null);

		ElementFactory factory2 = ElementFactory.getInstance();
		assertTrue(factory1 == factory2);
		assertEquals(factory1, factory2);
	}

	@Test
	public void testSetDefaultLinkType() {
		factory.setDefaultLinkType("FOO");
		assertEquals("LinkImpl", factory.getDefaultLinkType());

		factory.addLinkType("FOO", PamLinkImpl.class.getCanonicalName());
		factory.setDefaultLinkType("FOO");
		assertEquals("FOO", factory.getDefaultLinkType());

		// return to normal
		factory.setDefaultLinkType("LinkImpl");
	}

	@Test
	public void testSetDefaultNodeType() {
		factory.setDefaultNodeType("BAR");
		assertEquals("NodeImpl", factory.getDefaultNodeType());

		factory.addNodeType("FOO", PamNodeImpl.class.getCanonicalName());
		factory.setDefaultNodeType("FOO");
		assertEquals("FOO", factory.getDefaultNodeType());

		// return to normal
		factory.setDefaultNodeType("NodeImpl");
	}

	@Test
	public void testSetDefaultDecayType() {
		factory.setDefaultDecayType("BANANA");
		assertFalse("BANANA".equals(factory.getDefaultDecayType()));

		StrategyDef def = new StrategyDef();
		factory.addDecayStrategy("BANANA", def);
		factory.setDefaultDecayType("BANANA");
		assertEquals("BANANA", factory.getDefaultDecayType());

		// return to normal
		factory.setDefaultDecayType("defaultDecay");
	}

	@Test
	public void testSetDefaultExciteType() {
		factory.setDefaultExciteType("VVV");
		assertFalse("VVV".equals(factory.getDefaultExciteType()));

		StrategyDef def = new StrategyDef();
		factory.addExciteStrategy("VVV", def);
		factory.setDefaultExciteType("VVV");
		assertEquals("VVV", factory.getDefaultExciteType());

		// return to normal
		factory.setDefaultExciteType("defaultExcite");
	}

	@Test
	public void testAddDecayStrategy() {
		StrategyDef decay = new StrategyDef(SigmoidDecayStrategy.class
				.getCanonicalName(), "sigmoid", null, "decay", false);
		factory.addDecayStrategy("sigmoid", decay);
		DecayStrategy strategy = factory.getDecayStrategy("sigmoid");

		assertTrue(strategy instanceof SigmoidDecayStrategy);
	}

	@Test
	public void testAddExciteStrategy() {
		StrategyDef excite = new StrategyDef(SigmoidExciteStrategy.class
				.getCanonicalName(), "sigmoid", null, "excite", false);
		factory.addExciteStrategy("sigmoid", excite);
		ExciteStrategy strategy = factory.getExciteStrategy("sigmoid");

		assertTrue(strategy instanceof SigmoidExciteStrategy);
	}

	@Test
	public void testAddStrategy() {
		factory.addStrategy("generic", new StrategyDef(SigmoidExciteStrategy.class
				.getCanonicalName(), "sigmoid", null, "excite", false));
		
		Strategy strategy = factory.getStrategy("generic");
		assertTrue(strategy instanceof SigmoidExciteStrategy);
	}

	@Test
	public void testAddLinkType1() {
		LinkableDef linkDef = new LinkableDef(PamLinkImpl.class.getCanonicalName(), new HashMap<String, String>(), "abc", null);
		factory.addLinkType(linkDef);
		Link l = factory.getLink("abc", factory.getNode(), factory.getNode(), new PamNodeImpl());
		assertTrue(l instanceof PamLinkImpl);
		assertTrue(factory.containsLinkType("abc"));
	}

	@Test
	public void testAddLinkType2() {
		factory.addLinkType("samson", PamLinkImpl.class.getCanonicalName());
		Link l = factory.getLink("samson", factory.getNode(), factory.getNode(), new PamNodeImpl());
		assertTrue(l instanceof PamLinkImpl);
		assertTrue(factory.containsLinkType("samson"));
	}

	@Test
	public void testAddNodeType1() {
		LinkableDef nodeDef = new LinkableDef(PamNodeImpl.class.getCanonicalName(), new HashMap<String, String>(), "abc", null);
		factory.addNodeType(nodeDef);
		Node node = factory.getNode("abc");
		assertTrue(node instanceof PamNodeImpl);
		assertTrue(factory.containsNodeType("abc"));
	}

	@Test
	public void testAddNodeType2() {
		factory.addNodeType("samson", PamNodeImpl.class.getCanonicalName());
		Node node = factory.getNode("samson");
		assertTrue(node instanceof PamNodeImpl);
		assertTrue(factory.containsNodeType("samson"));
	}

	@Test
	public void testAddCodeletType1() {
		CodeletDef codeletDef = new CodeletDef(BasicAttentionCodelet.class.getCanonicalName(), 
											   new HashMap<String, String>(), "winwin", null);
		factory.addCodeletType(codeletDef);
		Codelet foo = factory.getCodelet("winwin", 1, 0.0, 0.0, null);
		assertTrue(foo instanceof BasicAttentionCodelet);
		assertTrue(factory.containsCodeletType("winwin"));
	}

	@Test
	public void testAddCodeletType2() {
		factory.addCodeletType("apple", BasicStructureBuildingCodelet.class.getCanonicalName());
		Codelet foo = factory.getCodelet("apple", 1, 0.0, 0.0, null);
		assertTrue(foo instanceof BasicStructureBuildingCodelet);
		assertTrue(factory.containsCodeletType("apple"));
	}

	@Test
	public void testContainsNodeType() {
		//already tested standard usage in previous tests
		assertFalse(factory.containsNodeType("RYANJMCCALL"));
	}

	@Test
	public void testContainsLinkType() {
		//already tested standard usage in previous tests
		assertFalse(factory.containsLinkType("RYANJMCCALL"));
	}

	@Test
	public void testContainsCodeletType() {
		//already tested standard usage in previous tests
		assertFalse(factory.containsCodeletType("RYANJMCCALL"));
	}

	@Test
	public void testGetDecayStrategy() {
		//already tested standard usage in previous tests
		DecayStrategy foo = factory.getDecayStrategy("1492");
		DecayStrategy defaultStrategy = factory.getDefaultDecayStrategy();
		assertEquals(foo.getClass(), defaultStrategy.getClass());
	}

	@Test
	public void testGetExciteStrategy() {
		//already tested standard usage in previous tests
		ExciteStrategy foo = factory.getExciteStrategy("1912");
		ExciteStrategy defaultStrategy = factory.getDefaultExciteStrategy();
		assertEquals(foo.getClass(), defaultStrategy.getClass());
	}

	@Test
	public void testGetStrategy() {
		//already tested standard usage in previous tests
		Strategy foo = factory.getStrategy("1984");
		assertTrue(foo == null);
	}

	@Test
	public void testGetLink0() {
		Link l = factory.getLink(node1, node2, category1, 0.7, 0.09);
		assertTrue(l instanceof LinkImpl);
		assertEquals(l.getSource(), node1);
		assertEquals(l.getSink(), node2);
		assertEquals(l.getCategory(), category1);
		assertTrue(0.7 == l.getActivation());
		assertTrue(0.09 == l.getActivatibleRemovalThreshold());
		assertTrue(l.isSimpleLink());
		assertTrue(l.getGroundingPamLink() == null);
		
		l = factory.getLink(node1, link1, category1, 0.7, 0.09);
		assertTrue(l instanceof LinkImpl);
		assertEquals(l.getSource(), node1);
		assertEquals(l.getSink(), link1);
		assertEquals(l.getCategory(), category1);
		assertTrue(0.7 == l.getActivation());
		assertTrue(0.09 == l.getActivatibleRemovalThreshold());
		assertTrue(!l.isSimpleLink());
		assertTrue(l.getGroundingPamLink() == null);
		
		l = factory.getLink(null, node2, category1, 0.7, 0.09);
		assertTrue(l == null);

		l = factory.getLink(node1, null, category1, 0.7, 0.09);
		assertTrue(l == null);
		
		l = factory.getLink(node1, node2, null, 0.7, 0.09);
		assertTrue(l == null);
	}

	@Test
	public void testGetLink1() {
		Link l = factory.getLink(node1, node2, category1);
		
		assertTrue(l instanceof LinkImpl);
		assertEquals(l.getSource(), node1);
		assertEquals(l.getSink(), node2);
		assertEquals(l.getCategory(), category1);
		assertTrue(Activatible.DEFAULT_ACTIVATION == l.getActivation());
		assertTrue(Activatible.DEFAULT_ACTIVATIBLE_REMOVAL_THRESHOLD == l.getActivatibleRemovalThreshold());
		assertTrue(l.isSimpleLink());
		assertTrue(l.getGroundingPamLink() == null);
	}

	@Test
	public void testGetLink2() {
		String requiredType = "LinkImpl";
		String desiredType = "PamLinkImpl";
		Link l = factory.getLink(requiredType, desiredType, node1, node2, category1);
		assertTrue(l instanceof PamLinkImpl);
		assertTrue(Activatible.DEFAULT_ACTIVATION == l.getActivation());
		assertTrue(Activatible.DEFAULT_ACTIVATIBLE_REMOVAL_THRESHOLD == l.getActivatibleRemovalThreshold());
		
		requiredType = "PamLinkImpl";
		desiredType = "LinkImpl";
		l = factory.getLink(requiredType, desiredType, node1, node2, category1);
		assertTrue(l == null);
	}

	@Test
	public void testGetLink3() {
		Link l = factory.getLink("LinkImpl", node1, node2, category1);
		assertTrue(l instanceof LinkImpl);
		
		l = factory.getLink("PamLinkImpl", node1, node2, category1);
		assertTrue(l instanceof PamLinkImpl);
		
		l = factory.getLink("NodeImpl", node1, node2, category1);
		assertTrue(l == null);
	}

	@Test
	public void testGetLink4() {
		String linkT = "PamLinkImpl";
		
		Link l = factory.getLink(linkT, node2, node1, category1, "defaultDecay", "defaultExcite",
						0.99, 0.11);
		
		assertTrue(l instanceof PamLinkImpl);
		assertEquals(l.getSource(), node2);
		assertEquals(l.getSink(), node1);
		assertEquals(l.getCategory(), category1);
		assertTrue(0.99 == l.getActivation());
		assertTrue(0.11 == l.getActivatibleRemovalThreshold());
		assertTrue(l.isSimpleLink());
		assertTrue(l.getGroundingPamLink() == l);
		assertEquals(LinearDecayStrategy.class, l.getDecayStrategy().getClass());
		assertEquals(DefaultExciteStrategy.class, l.getExciteStrategy().getClass());
	}

	@Test
	public void testGetNode0() {
		Node n = factory.getNode();
		
		assertTrue(n.getDesirability() == 0.0);
		assertTrue(n.getGroundingPamNode() == null);
		assertTrue(n.getActivation() == Activatible.DEFAULT_ACTIVATION);
		assertTrue(n.getActivatibleRemovalThreshold() == Activatible.DEFAULT_ACTIVATIBLE_REMOVAL_THRESHOLD);
	}
	
	@Test
	public void testGetNode7() {
		Node n = factory.getNode("NodeImpl");
		
		assertTrue(n.getDesirability() == 0.0);
		assertTrue(n.getGroundingPamNode() == null);
		assertTrue(n.getActivation() == Activatible.DEFAULT_ACTIVATION);
		assertTrue(n.getActivatibleRemovalThreshold() == Activatible.DEFAULT_ACTIVATIBLE_REMOVAL_THRESHOLD);
		
		PamNode pn = (PamNode) factory.getNode("PamNodeImpl");
		
		assertTrue(pn.getDesirability() == 0.0);
		assertEquals(pn.getGroundingPamNode(), pn);
		assertTrue(pn.getActivation() == Activatible.DEFAULT_ACTIVATION);
		assertTrue(pn.getActivatibleRemovalThreshold() == Activatible.DEFAULT_ACTIVATIBLE_REMOVAL_THRESHOLD);
		
		n = factory.getNode("adfowifjwoqi");
		assertTrue(n == null);
	}

	@Test
	public void testGetNode8() {
		Node n = factory.getNode("NodeImpl", "foobar");
		assertEquals("foobar", n.getLabel());
		
		n = factory.getNode("aslfskj", "asdlfkj");
		assertTrue(n == null);
		
		n = factory.getNode("NodeImpl", null);
		assertTrue(n.getLabel() == null);
	}

	@Test
	public void testGetNode1() {
		node1.setActivatibleRemovalThreshold(0.11);
		node1.setActivation(0.22);
		node1.setDesirability(0.33);
		node1.setGroundingPamNode(pamNode1);
		node1.setLabel("ABC");
		
		Node n = factory.getNode(node1);
		
		assertTrue(n.getActivatibleRemovalThreshold() == 0.11);
		assertTrue(n.getActivation() == 0.22);
		assertTrue(n.getDesirability() == 0.33);
		assertEquals(n.getGroundingPamNode(), pamNode1);
		assertEquals(n.getLabel(), "ABC");
	}

	@Test
	public void testGetNode2() {

		StrategyDef decayDef = new StrategyDef(SigmoidDecayStrategy.class.getCanonicalName(), 
				"specialDecay", null, "decay", true);
		factory.addDecayStrategy("specialDecay", decayDef);
		
		StrategyDef exciteDef = new StrategyDef(SigmoidExciteStrategy.class.getCanonicalName(), 
				"specialExcite", null, "excite", true);
		factory.addExciteStrategy("specialExcite", exciteDef);
		
		Map<String, String> defaultStrategies = new HashMap<String, String>();
		defaultStrategies.put("decay", "specialDecay");
		defaultStrategies.put("excite", "specialExcite");
		LinkableDef nodeDef = new LinkableDef(NodeImpl.class.getCanonicalName(), 
				defaultStrategies, "special", null);
		factory.addNodeType(nodeDef);
		
		Node oNode = factory.getNode();
		
		assertFalse(oNode.getExciteStrategy() instanceof SigmoidExciteStrategy);
		assertFalse(oNode.getDecayStrategy() instanceof SigmoidDecayStrategy);
		
		oNode.setActivation(0.11);
		oNode.setActivatibleRemovalThreshold(0.22);
		oNode.setDesirability(0.33);
		oNode.setLabel("blah");
		oNode.setGroundingPamNode(pamNode1);
		
		Node newNode = factory.getNode(oNode, "special");
		
		assertTrue(newNode.getExciteStrategy() instanceof SigmoidExciteStrategy);
		assertTrue(newNode.getDecayStrategy() instanceof SigmoidDecayStrategy);
		assertTrue(newNode.getActivation() == 0.11);
		assertTrue(newNode.getActivatibleRemovalThreshold() == 0.22);
		assertTrue(newNode.getDesirability() == 0.33);
		assertEquals("blah", newNode.getLabel());
		assertEquals(oNode.getGroundingPamNode(), newNode.getGroundingPamNode());
	}

	@Test
	public void testGetNode3() {
		String requiredType = "NodeImpl";
		String desiredType = "PamNodeImpl";
		Node oNode = factory.getNode();
		oNode.setActivation(0.11);
		oNode.setActivatibleRemovalThreshold(0.22);
		oNode.setDesirability(0.33);
		oNode.setLabel("blah");
		oNode.setGroundingPamNode(pamNode1);
		
		Node n = factory.getNode(requiredType, oNode, desiredType);
		
		assertTrue(n instanceof PamNodeImpl);
		assertTrue(n.getActivation() == 0.11);
		assertTrue(n.getActivatibleRemovalThreshold() == 0.22);
		assertTrue(n.getDesirability() == 0.33);
		assertEquals("blah", n.getLabel());
		assertEquals(oNode.getGroundingPamNode(), n.getGroundingPamNode());
		
		//sub test
		requiredType = "PamNodeImpl";
		desiredType = "NodeImpl";
		
		n = factory.getNode(requiredType, oNode, desiredType);
		
		assertTrue(n == null);
	}

	@Test
	public void testGetNode4() {
		Node n = factory.getNode(node1, "specialDecay", "specialExcite");
		assertTrue(n.getDecayStrategy() instanceof SigmoidDecayStrategy);
		assertTrue(n.getExciteStrategy() instanceof SigmoidExciteStrategy);
		
		assertTrue(n instanceof NodeImpl);
		assertTrue(n.getActivation() == node1.getActivation());
		assertTrue(n.getActivatibleRemovalThreshold() == node1.getActivatibleRemovalThreshold());
		assertTrue(n.getDesirability() == node1.getDesirability());
		assertEquals(n.getLabel(), node1.getLabel());
		assertEquals(n.getGroundingPamNode(), node1.getGroundingPamNode());
	}

//	@Test
//	public void testGetNode5() {
////		factory.getNode(oNode, nodeType, decayStrategy, exciteStrategy)
//	}

	@Test
	public void testGetNode6() {
		Node n = factory.getNode("PamNodeImpl", "specialDecay", "specialExcite", "chuck", 0.99, 0.11);
		assertTrue(n instanceof PamNodeImpl);
		assertTrue(n.getDecayStrategy() instanceof SigmoidDecayStrategy);
		assertTrue(n.getExciteStrategy() instanceof SigmoidExciteStrategy);
		assertEquals(n.getLabel(), "chuck");
		assertTrue(0.99 == n.getActivation());
		assertTrue(0.11 == n.getActivatibleRemovalThreshold());
		
		n = factory.getNode(null, "specialDecay", "specialExcite", "chuck", 0.99, 0.11);
		assertTrue(n == null);
		
		n = factory.getNode("PamNodeImpl", null, "specialExcite", "chuck", 0.99, 0.11);
		assertTrue(n instanceof PamNodeImpl);
		assertFalse(n.getDecayStrategy() instanceof SigmoidDecayStrategy);
		assertTrue(n.getExciteStrategy() instanceof SigmoidExciteStrategy);
		
		n = factory.getNode("PamNodeImpl", "specialDecay", null, "chuck", 0.99, 0.11);
		assertTrue(n instanceof PamNodeImpl);
		assertTrue(n.getDecayStrategy() instanceof SigmoidDecayStrategy);
		assertFalse(n.getExciteStrategy() instanceof SigmoidExciteStrategy);
		
		n = factory.getNode("PamNodeImpl", "specialDecay", "specialExcite", null, 0.99, 0.11);
		assertTrue(n instanceof PamNodeImpl);
	}

	@Test
	public void testGetCodelet0() {
		factory.addCodeletType("testType", BasicAttentionCodelet.class.getCanonicalName());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Hello", 500);
		
		Codelet c = factory.getCodelet("testType", 100, 0.66, 0.77, params);
		assertTrue(c instanceof BasicAttentionCodelet);
		assertEquals(c.getTicksPerStep(), 100);
		assertTrue(c.getActivation() == 0.66);
		assertTrue(c.getActivatibleRemovalThreshold() == 0.77);
		int hello = (Integer) c.getParam("Hello", null);
		assertEquals(500, hello);
	}

	@Test
	public void testGetCodelet1() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Hello", 500);
		Codelet c = factory.getCodelet("testType", "defaultDecay", "defaultExcite", 100, 0.66, 0.77, params);
		
		assertTrue(c.getDecayStrategy() instanceof LinearDecayStrategy);
		assertTrue(c.getExciteStrategy() instanceof DefaultExciteStrategy);
		
		assertTrue(c instanceof BasicAttentionCodelet);
		assertEquals(c.getTicksPerStep(), 100);
		assertTrue(c.getActivation() == 0.66);
		assertTrue(c.getActivatibleRemovalThreshold() == 0.77);
		int hello = (Integer) c.getParam("Hello", null);
		assertEquals(500, hello);
	}

	@Test
	public void testGetNodeStructure() {
		NodeStructure ns = factory.getNodeStructure();
		assertEquals(ns.getDefaultNodeType(), factory.getDefaultNodeType());
		assertEquals(ns.getDefaultLinkType(), factory.getDefaultLinkType());
	}

	@Test
	public void testGetPamNodeStructure() {
		NodeStructure ns = factory.getPamNodeStructure();
		assertEquals(ns.getDefaultNodeType(), "PamNodeImpl");
		assertEquals(ns.getDefaultLinkType(), "PamLinkImpl");
	}

	@Test
	public void testGetNodeStructure1() {
		NodeStructure ns = factory.getNodeStructure("basdf", "asdfl");
		assertTrue(ns == null);
		
		ns = factory.getNodeStructure("LinkImpl", "NodeImpl");
		assertTrue(ns == null);
		
		ns = factory.getNodeStructure("NodeImpl", "ffsdfs");
		assertTrue(ns == null);
		
		ns = factory.getNodeStructure("Nsdfasdfl", "LinkImpl");
		assertTrue(ns == null);
		
		ns = factory.getNodeStructure(null, null);
		assertTrue(ns == null);
		
		ns = factory.getNodeStructure("PamNodeImpl", "LinkImpl");
		assertTrue(ns != null);
		assertEquals(ns.getDefaultNodeType(), "PamNodeImpl");
		assertEquals(ns.getDefaultLinkType(), "LinkImpl");
	}

}