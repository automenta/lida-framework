package edu.memphis.ccrg.lida.framework.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.memphis.ccrg.lida.framework.FrameworkModule;
import edu.memphis.ccrg.lida.framework.mockclasses.MockFrameworkModule;

public class TaskManagerTest {
	
	private TaskManager tm;
	private TaskSpawner taskSpawner;
	
	@Before
	public void setUp() throws Exception {
		tm = new TaskManager(10, 20);
		taskSpawner = new MockTaskSpawner();
	}

	@After
	public void tearDown() throws Exception{
		tm.reset();
	}
	
	@Test
	public void testGetCurrentTick() {
		TestTask task = new TestTask(10);
		tm.scheduleTask(task, 40);
		assertEquals(40,tm.getMaxTick());
		assertEquals(0, TaskManager.getCurrentTick());		
		tm.resumeTasks();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(task.wasRun);
		assertEquals(40, TaskManager.getCurrentTick());		
	}

	@Test
	public void testGetMaxTick() {
		assertEquals(0,tm.getMaxTick());
		TestTask task = new TestTask(10);
		tm.scheduleTask(task, 40);
		assertEquals(40,tm.getMaxTick());
		task = new TestTask(10);
		tm.scheduleTask(task, 80);
		assertEquals(80,tm.getMaxTick());
	}

	@Test
	public void testGetEndOfNextInterval() {
		tm.setInIntervalMode(true);
		tm.addTicksToExecute(20);
		assertEquals(20, tm.getEndOfNextInterval());
		TestTask task = new TestTask(10);
		tm.scheduleTask(task, 100);
		assertEquals(20, tm.getEndOfNextInterval());
	}

	@Test
	public void testSetTickDuration() {
		assertEquals(10, tm.getTickDuration());
		tm.setTickDuration(-1);
		assertEquals(10, tm.getTickDuration());
		tm.setTickDuration(1000);
		assertEquals(1000, tm.getTickDuration());
	}

	@Test
	public void testIsInIntervalMode() {
		assertTrue(!tm.isInIntervalMode());
		tm.setInIntervalMode(true);
		assertTrue(tm.isInIntervalMode());		
		tm.setInIntervalMode(false);
		assertTrue(!tm.isInIntervalMode());		
	}

	@Test
	public void testGetTaskQueue() {
		assertEquals(0,TaskManager.getCurrentTick());
		TestTask task = new TestTask(10);
		task.setControllingTaskSpawner(taskSpawner);
		tm.scheduleTask(task, 40);

		TestTask task2 = new TestTask(10);
		task.setControllingTaskSpawner(taskSpawner);
		tm.scheduleTask(task2, 80);

		Map<Long,Set<FrameworkTask>>queue =tm.getTaskQueue();
		assertTrue(queue != null);
		Set<FrameworkTask>q=queue.get(40L);
		assertTrue(q.contains(task));
		q=queue.get(80L);
		assertTrue(q.contains(task2));
	}

	@Test
	public void testIsTasksPaused() {
		assertTrue(tm.isTasksPaused());
		tm.resumeTasks();
		assertTrue(!tm.isTasksPaused());
	}

	@Test
	public void testPauseTasks() {
		TestTask task1 = new TestTask(10);
		task1.setControllingTaskSpawner(taskSpawner);
		tm.scheduleTask(task1, 10);

		TestTask task2 = new TestTask(10);
		task2.setControllingTaskSpawner(taskSpawner);
		tm.scheduleTask(task2, 20);
		
		tm.resumeTasks();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		tm.pauseTasks();
		
		TestTask task3 = new TestTask(10);
		task3.setControllingTaskSpawner(taskSpawner);
		tm.scheduleTask(task3, 30);

		TestTask task4 = new TestTask(10);
		task4.setControllingTaskSpawner(taskSpawner);
		tm.scheduleTask(task4, 40);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue(task1.wasRun);
		assertTrue(task2.wasRun);
		assertTrue(!task3.wasRun);
		assertTrue(!task4.wasRun);
		assertEquals(20, TaskManager.getCurrentTick());
		assertEquals(60, tm.getMaxTick());
	}

	@Test
	public void testResumeTasks() {
		TestTask task1 = new TestTask(10);
		task1.setControllingTaskSpawner(taskSpawner);
		tm.scheduleTask(task1, 10);

		TestTask task2 = new TestTask(10);
		task2.setControllingTaskSpawner(taskSpawner);
		tm.scheduleTask(task2, 20);
		
		tm.resumeTasks();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		tm.pauseTasks();
		
		TestTask task3 = new TestTask(10);
		task3.setControllingTaskSpawner(taskSpawner);
		tm.scheduleTask(task3, 30);

		TestTask task4 = new TestTask(10);
		task4.setControllingTaskSpawner(taskSpawner);
		tm.scheduleTask(task4, 40);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue(task1.wasRun);
		assertTrue(task2.wasRun);
		assertTrue(!task3.wasRun);
		assertTrue(!task4.wasRun);
		assertEquals(20, TaskManager.getCurrentTick());
		assertEquals(60, tm.getMaxTick());
		
		tm.resumeTasks();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue(task3.wasRun);
		assertTrue(task4.wasRun);
		assertEquals(60, TaskManager.getCurrentTick());
		assertEquals(60, tm.getMaxTick());
	}

	@Test
	public void testCancelTask() {
		TestTask task1 = new TestTask(10);
		task1.setControllingTaskSpawner(taskSpawner);
		tm.scheduleTask(task1, 10);

		TestTask task2 = new TestTask(10);
		task2.setControllingTaskSpawner(taskSpawner);
		tm.scheduleTask(task2, 20);
		
		assertTrue(tm.cancelTask(task1));
		
		Map<Long,Set<FrameworkTask>>queue =tm.getTaskQueue();
		assertTrue(queue != null);
		Set<FrameworkTask>q=queue.get(10L);
		assertTrue(!q.contains(task1));
		q=queue.get(20L);
		assertTrue(q.contains(task2));	
		tm.resumeTasks();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(!tm.cancelTask(task2));
	}

	@Test
	public void testAddTicksToExecute() {
		tm.setTickDuration(1);
		assertTrue(!tm.isInIntervalMode());
		tm.addTicksToExecute(100);
		assertEquals(0,tm.getEndOfNextInterval());
		tm.setInIntervalMode(true);
		assertTrue(tm.isInIntervalMode());		
		tm.addTicksToExecute(100);
		assertEquals(100,tm.getEndOfNextInterval());
		tm.addTicksToExecute(100);
		assertEquals(200,tm.getEndOfNextInterval());
		tm.addTicksToExecute(-100);
		assertEquals(200,tm.getEndOfNextInterval());
		TestTask task1 = new TestTask(10);
		
		tm.setInIntervalMode(false);
		task1.setControllingTaskSpawner(taskSpawner);
		tm.scheduleTask(task1, 500);
		tm.resumeTasks();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(task1.wasRun);
		assertEquals(500,TaskManager.getCurrentTick());
		tm.setInIntervalMode(true);
		assertTrue(tm.isInIntervalMode());		
		tm.addTicksToExecute(100);
		assertEquals(600,tm.getEndOfNextInterval());
	
	}

	@Test
	public void testScheduleTask() {
		TestTask task1 = new TestTask(10);
		task1.setControllingTaskSpawner(taskSpawner);
		tm.scheduleTask(task1, 10);

		TestTask task2 = new TestTask(10);
		task2.setControllingTaskSpawner(taskSpawner);
		tm.scheduleTask(task2, 20);
		
		Map<Long,Set<FrameworkTask>>queue =tm.getTaskQueue();
		assertTrue(queue != null);
		Set<FrameworkTask>q=queue.get(10L);
		assertTrue(q.contains(task1));
		q=queue.get(20L);
		assertTrue(q.contains(task2));	
		tm.resumeTasks();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(task1.wasRun);
		assertTrue(task2.wasRun);
		
		TestTask task3 = new TestTask(10);
		task3.setControllingTaskSpawner(taskSpawner);
		tm.scheduleTask(task3, 20);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(task3.wasRun);
		
		assertTrue(!tm.scheduleTask(null, 10));
		assertTrue(!tm.scheduleTask(task1, -10));
		
	}

	@Test
	public void testSetDecayingModules() {
		List<FrameworkModule> modules = new ArrayList<FrameworkModule>();
		MockFrameworkModule module = new MockFrameworkModule();
		modules.add(module);
		tm.setDecayingModules(modules);
		TestTask task1 = new TestTask(10);
		task1.setControllingTaskSpawner(taskSpawner);
		tm.scheduleTask(task1, 10);
		tm.resumeTasks();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(task1.wasRun);
		assertTrue(module.wasDecayed);
		assertEquals(10L,module.decayTicks);
		module.wasDecayed=false;
		tm.scheduleTask(task1, 10);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(module.wasDecayed);
	}

	@Test
	public void testToString() {
		assertEquals("TaskManager",tm.toString());
	}

}
