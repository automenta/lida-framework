package edu.memphis.ccrg.lida.framework.tasks;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class FrameworkTaskImplTest {

	private TestTask task1;
	private MockTaskSpawner taskSpawner;
	
	@Before
	public void setUp() throws Exception {
		taskSpawner = new MockTaskSpawner();
		task1 = new TestTask(10, taskSpawner);
	}
	
	
	@Test
	public void testCall() {
		task1.call();
		assertTrue(task1.wasRun);
		assertEquals(task1, taskSpawner.lastReceived);
	}

	@Test
	public void testSetTaskStatus() {
		assertEquals(TaskStatus.WAITING, task1.getTaskStatus());
		
		task1.setTaskStatus(TaskStatus.FINISHED_WITH_RESULTS);
		assertEquals(TaskStatus.FINISHED_WITH_RESULTS, task1.getTaskStatus());
		
		task1.setTaskStatus(TaskStatus.CANCELED);
		assertEquals(TaskStatus.CANCELED, task1.getTaskStatus());
		
		task1.setTaskStatus(TaskStatus.FINISHED_WITH_RESULTS);
		assertEquals(TaskStatus.CANCELED, task1.getTaskStatus());
	}

	@Test
	public void testGetTaskId() {
		TestTask task2 = new TestTask(10, taskSpawner);
		assertNotSame(task1.getTaskId(), task2.getTaskId());
	}

	@Test
	public void testGetTicksPerStep() {
		assertEquals(10, task1.getTicksPerStep());	
	}

	@Test
	public void testStopRunning() {
		task1.stopRunning();
		assertEquals(TaskStatus.CANCELED, task1.getTaskStatus());
	}

	@Test
	public void testInitMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("arg0", 10.0);
		params.put("name", "Javier");
		
		task1.init(params);
		
		assertEquals(10.0, task1.getParam("arg0", null));
		assertEquals("Javier", task1.getParam("name", null));
	}

	@Test
	public void testGetParam() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("arg0", 10.0);
		params.put("name", "Javier");
		
		task1.init(params);
		
		assertEquals(5, task1.getParam("sdflkj", 5));
		assertNotSame(true, task1.getParam("name", true));
	}

	@Test
	public void testGetControllingTaskSpawner() {
		assertEquals(taskSpawner, task1.getControllingTaskSpawner());
	}
	
	@Test
	public void testToString() {
		assertEquals("testTask", task1.toString());
	}

}