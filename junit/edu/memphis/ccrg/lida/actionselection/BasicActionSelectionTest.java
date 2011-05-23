package edu.memphis.ccrg.lida.actionselection;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import edu.memphis.ccrg.lida.framework.mockclasses.MockSensoryMotorMemory;
import edu.memphis.ccrg.lida.proceduralmemory.Scheme;
import edu.memphis.ccrg.lida.proceduralmemory.SchemeImpl;

public class BasicActionSelectionTest {
	
	private BasicActionSelection as;
	private Behavior behav1,behav2;	
	private MockSensoryMotorMemory smm = new MockSensoryMotorMemory();
	private Scheme scheme1,scheme2 ;
		
	private AgentAction action1 = new AgentActionImpl() {
		@Override
		public void performAction() {
		}
	};
	
	private AgentAction action2 = new AgentActionImpl() {
		@Override
		public void performAction() {
		}
	};
	
	@Before
	public void setUp() throws Exception {			
		scheme1= new SchemeImpl("scheme1",action1);
		scheme2= new SchemeImpl("scheme2",action2);
		
		as = new BasicActionSelection();
						
		behav1 = scheme1.getInstantiation();
		behav2 = scheme2.getInstantiation();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetModuleContent() {
		as.receiveBehavior(behav1);
		Collection<Behavior> content = (Collection<Behavior>) as.getModuleContent("behaviors");
		
		assertTrue("Problem with GetModuleContent", content.size()==1);
		assertTrue("Problem with GetModuleContent", content.contains(behav1));
		assertTrue("Problem with GetModuleContent", !content.contains(behav2));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testReceiveBehavior() {
		as.receiveBehavior(behav1);
		as.receiveBehavior(behav2);
		Collection<Behavior> content = (Collection<Behavior>) as.getModuleContent("behaviors");
		
		assertTrue("Problem with ReceiveBehavior", content.size()==2);
		assertTrue("Problem with ReceiveBehavior", content.contains(behav1));
		assertTrue("Problem with ReceiveBehavior", content.contains(behav2));
	}

	@Test
	public void testSelectAction() {
		behav1.setActivation(0.1);
		behav2.setActivation(0.2);
		as.receiveBehavior(behav1);
		as.receiveBehavior(behav2);
		as.addListener(smm);		
		
		assertNull(smm.action);
		assertFalse(smm.actionReceived);
		
		as.selectAction();
		
		assertTrue("Problem with SelectAction",smm.actionReceived);
		assertEquals("Problem with SelectAction",action2,smm.action);
		
	}

//	@Test
//	public void testGetState() {
//		as.receiveBehavior(behav1);
//		as.receiveBehavior(behav2);
//		
//		Object[] state = (Object[])as.getState();
//		Collection<Behavior> content = (Collection<Behavior>)state[0];
//		
//		assertTrue("Problem with GetState", content.size()==2);
//	}

}