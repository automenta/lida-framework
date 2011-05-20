package edu.memphis.ccrg.lida.framework.initialization;


import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import edu.memphis.ccrg.lida.framework.shared.ElementFactory;
import edu.memphis.ccrg.lida.framework.shared.Linkable;
import edu.memphis.ccrg.lida.framework.shared.Node;
import edu.memphis.ccrg.lida.framework.shared.NodeImpl;
import edu.memphis.ccrg.lida.framework.strategies.DefaultExciteStrategy;
import edu.memphis.ccrg.lida.framework.strategies.LinearDecayStrategy;
import edu.memphis.ccrg.lida.framework.strategies.Strategy;
import edu.memphis.ccrg.lida.framework.tasks.Codelet;
import edu.memphis.ccrg.lida.pam.PamLinkImpl;
import edu.memphis.ccrg.lida.pam.PamNodeImpl;
import edu.memphis.ccrg.lida.workspace.structurebuildingcodelets.BasicStructureBuildingCodelet;

public class FactoriesDataXmlLoaderTest3 {

	private FactoriesDataXmlLoader loader;
	private static ElementFactory factory = ElementFactory.getInstance();

	@Before
	public void setUp() throws Exception {
		loader = new FactoriesDataXmlLoader();
	}
	
	@Test
	public void testLoadFactoriesData() {
		Properties p = new Properties();
		p.setProperty("lida.elementFactory", "testData/testFactoriesData.xml");
		
		loader.loadFactoriesData(p);
		
		assertTrue(factory.containsStrategy("excite1"));
		assertTrue(factory.containsStrategy("decay1"));
		assertTrue(factory.containsNodeType("node1"));
		assertTrue(factory.containsNodeType("node2"));
		assertTrue(factory.containsLinkType("link1"));
		assertTrue(factory.containsCodeletType("topleft"));
		assertTrue(factory.containsCodeletType("bottomright"));
		
		Strategy s = factory.getStrategy("excite1");
		assertTrue(s instanceof DefaultExciteStrategy);
		s = factory.getStrategy("decay1");
		assertTrue(s instanceof LinearDecayStrategy);
		
		Linkable l = factory.getNode("node1");
		assertTrue(l instanceof PamNodeImpl);
		l = factory.getNode("node2");
		assertNull(l);
		Node n = new NodeImpl();
		n.setId(304593);
		Node n1 = new NodeImpl();
		n1.setId(3043);
		l = factory.getLink("link1", n, n1, new PamNodeImpl());
		assertTrue(l instanceof PamLinkImpl);
		
		Codelet c = factory.getCodelet("topleft", 1, 0.0, 0.0, null);
		assertTrue(c instanceof BasicStructureBuildingCodelet);
		
		c = factory.getCodelet("bottomright", 1, 0.0, 0.0, null);
		assertNull(c);
	}

}
