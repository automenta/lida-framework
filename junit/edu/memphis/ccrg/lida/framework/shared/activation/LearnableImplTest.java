/**
 * 
 */
package edu.memphis.ccrg.lida.framework.shared.activation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl;

import edu.memphis.ccrg.lida.framework.strategies.DecayStrategy;
import edu.memphis.ccrg.lida.framework.strategies.DefaultExciteStrategy;
import edu.memphis.ccrg.lida.framework.strategies.DefaultTotalActivationStrategy;
import edu.memphis.ccrg.lida.framework.strategies.ExciteStrategy;
import edu.memphis.ccrg.lida.framework.strategies.LinearDecayStrategy;
import edu.memphis.ccrg.lida.framework.strategies.TotalActivationStrategy;

/**
 * @author Owner
 *
 */
public class LearnableImplTest {

	/**
	 * @throws java.lang.Exception
	 */
	
	LearnableImpl node1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		node1 = new LearnableImpl ();
		//node2 = new LearnableImpl ();  
	}

	/**
	 * Test method for {@link edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl#decay(long)}.
	 */
	@Test
	public void testDecay() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl#getTotalActivation()}.
	 */
	@Test
	public void testGetTotalActivation() {
		
		TotalActivationStrategy ts= new DefaultTotalActivationStrategy();
		node1.setTotalActivationStrategy(ts);
		node1.setActivation(0.2);
		node1.setBaseLevelActivation(0.2);
		//assertEquals("Problem with GetTotalActivation", 0.4, node1.getTotalActivation());  
	    assertEquals (0.4, node1.getTotalActivation(), 0.000000001);
	}

	/**
	 * Test method for {@link edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl#isRemovable()}.
	 */
	@Test
	public void testIsRemovable() {
		//node1.setActivation(-1.1);
		node1.setBaseLevelActivation(-1.0);
		//System.out.println (node1.getBaseLevelActivation()) ;
		 assertEquals ("problems",true , node1.isRemovable());
	}

	/**
	 * Test method for {@link edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl#LearnableImpl(double, edu.memphis.ccrg.lida.framework.strategies.ExciteStrategy, edu.memphis.ccrg.lida.framework.strategies.DecayStrategy, edu.memphis.ccrg.lida.framework.shared.activation.TotalActivationStrategy)}.
	 */
	@Test
	public void testLearnableImplDoubleExciteStrategyDecayStrategyTotalActivationStrategy() {

	}

	/**
	 * Test method for {@link edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl#LearnableImpl()}.
	 */
	@Test
	public void testLearnableImpl() {
	
	}

	/**
	 * Test method for {@link edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl#decayBaseLevelActivation(long)}.
	 */
	@Test
	public void testDecayBaseLevelActivation() {
		DecayStrategy ds = new LinearDecayStrategy();
		node1.setBaseLevelDecayStrategy(ds);
		node1.setBaseLevelActivation(0.5);			
		node1.decayBaseLevelActivation(100);
		node1.getBaseLevelActivation();
		
		assertTrue("Problem with DecayBaseLevelActivation", node1.getBaseLevelActivation()<0.5);
	}

	/**
	 * Test method for {@link edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl#getBaseLevelExciteStrategy()}.
	 */
	@Test
	public void testGetBaseLevelExciteStrategy() {
		ExciteStrategy es = new DefaultExciteStrategy();
		node1.setBaseLevelExciteStrategy(es);
		
		assertEquals("Problem with SetBaseLevelExciteStrategy", es,node1.getBaseLevelExciteStrategy());;
	}

	/**
	 * Test method for {@link edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl#setBaseLevelExciteStrategy(edu.memphis.ccrg.lida.framework.strategies.ExciteStrategy)}.
	 */
	@Test
	public void testSetBaseLevelExciteStrategy() {
		ExciteStrategy es = new DefaultExciteStrategy();
		node1.setBaseLevelExciteStrategy(es);
		
		assertEquals("Problem with SetBaseLevelExciteStrategy", es,node1.getBaseLevelExciteStrategy());
		
	}

	/**
	 * Test method for {@link edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl#getBaseLevelDecayStrategy()}.
	 */
	@Test
	public void testGetBaseLevelDecayStrategy() {
		DecayStrategy ds = new LinearDecayStrategy();
		node1.setBaseLevelDecayStrategy(ds);
		
		assertEquals("Problem with GetBaseLevelDecayStrategy", ds,node1.getBaseLevelDecayStrategy());
	}

	/**
	 * Test method for {@link edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl#setBaseLevelDecayStrategy(edu.memphis.ccrg.lida.framework.strategies.DecayStrategy)}.
	 */
	@Test
	public void testSetBaseLevelDecayStrategy() {
		DecayStrategy ds = new LinearDecayStrategy();
		node1.setBaseLevelDecayStrategy(ds);
		
		assertEquals("Problem with SetBaseLevelDecayStrategy", ds,node1.getBaseLevelDecayStrategy());
	}

	/**
	 * Test method for {@link edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl#reinforceBaseLevelActivation(double)}.
	 */
	@Test
	public void testReinforceBaseLevelActivation() {
		ExciteStrategy es = new DefaultExciteStrategy();
		node1.setBaseLevelExciteStrategy(es);
		node1.setBaseLevelActivation(0.2);
		node1.reinforceBaseLevelActivation(0.3);
		
		assertEquals ( 0.5,node1.getBaseLevelActivation(),0.001);
	}

	/**
	 * Test method for {@link edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl#setBaseLevelActivation(double)}.
	 */
	@Test
	public void testSetBaseLevelActivation() {
    node1.setBaseLevelActivation(0.4);
		
		assertEquals(0.4,node1.getBaseLevelActivation(),0.001);
	}

	/**
	 * Test method for {@link edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl#getBaseLevelActivation()}.
	 */
	@Test
	public void testGetBaseLevelActivation() {
	        node1.setBaseLevelActivation(0.3);
	        assertEquals (0.3, node1.getBaseLevelActivation(), 0.001);
	}

	/**
	 * Test method for {@link edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl#getLearnableRemovalThreshold()}.
	 */
	@Test
	public void testGetLearnableRemovalThreshold() {
		node1.setLearnableRemovalThreshold(0.2);
		assertEquals (0.2, node1.getLearnableRemovalThreshold(), 0.00001);
		//System.out.print(node1.getLearnableRemovalThreshold());
	}

	/**
	 * Test method for {@link edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl#setLearnableRemovalThreshold(double)}.
	 */
	@Test
	public void testSetLearnableRemovalThreshold() {
		node1.setLearnableRemovalThreshold(0.4);
		assertEquals(0.4, node1.getLearnableRemovalThreshold(), 0.0001);
		
		
	}

	/**
	 * Test method for {@link edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl#getTotalActivationStrategy()}.
	 */
	@Test
	public void testGetTotalActivationStrategy() {
		TotalActivationStrategy ts= new DefaultTotalActivationStrategy();
		node1.setTotalActivationStrategy(ts);
		assertEquals("problem with GetTotalActivationStrategy() ", ts,node1.getTotalActivationStrategy());
	}

	/**
	 * Test method for {@link edu.memphis.ccrg.lida.framework.shared.activation.LearnableImpl#setTotalActivationStrategy(edu.memphis.ccrg.lida.framework.shared.activation.TotalActivationStrategy)}.
	 */
	@Test
	public void testSetTotalActivationStrategy() {
		TotalActivationStrategy ts= new DefaultTotalActivationStrategy();
		node1.setTotalActivationStrategy(ts);
		assertEquals("problem with SetTotalActivationStrategy() ", ts,node1.getTotalActivationStrategy());
		
	}

}