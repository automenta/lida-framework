package edu.memphis.ccrg.lida.pam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import edu.memphis.ccrg.lida.framework.tasks.FrameworkTask;
import edu.memphis.ccrg.lida.framework.tasks.TaskSpawner;
import edu.memphis.ccrg.lida.framework.tasks.TaskSpawnerImpl;

public class PamMockTaskSpawner extends TaskSpawnerImpl implements TaskSpawner {
	public List<FrameworkTask> tasks = new ArrayList<FrameworkTask>(); 

	@Override
	public void addTask(FrameworkTask task) {
		tasks.add(task);
		task.setControllingTaskSpawner(this);
		try {
			task.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean cancelTask(FrameworkTask task) {
		System.out.println(task+" removed! ");
		return tasks.remove(task);
	}

	@Override
	public Collection<FrameworkTask> getRunningTasks() {
		return Collections.unmodifiableCollection(tasks);
	}

	@Override
	public void receiveFinishedTask(FrameworkTask task) {

	}

	@Override
	public void addTasks(Collection<? extends FrameworkTask> initialTasks) {
		for(FrameworkTask t: initialTasks){
			addTask(t);
		}
	}
	@Override
	public void init() {
		// not implemented
		
	}

	@Override
	public boolean containsTask(FrameworkTask t) {
		return tasks.contains(t);
	}
}
