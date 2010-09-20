package edu.memphis.ccrg.lida.framework.mockclasses;

import edu.memphis.ccrg.lida.actionselection.ActionSelection;
import edu.memphis.ccrg.lida.actionselection.ActionSelectionListener;
import edu.memphis.ccrg.lida.actionselection.behaviornetwork.main.ExpectationListener;
import edu.memphis.ccrg.lida.actionselection.behaviornetwork.main.PreafferenceListener;
import edu.memphis.ccrg.lida.framework.tasks.TaskSpawner;

public class ActionSelectionImpl implements ActionSelection {

	@Override
	public void addActionSelectionListener(ActionSelectionListener listener) {
		

	}

	@Override
	public void selectAction() {
		System.out.println("action selected");

	}

	@Override
	public void setTaskSpawner(TaskSpawner taskSpawner) {
		

	}

	@Override
	public void triggerActionSelection() {
		
		selectAction();
	}

	@Override
	public Object getState() {
		
		return null;
	}

	@Override
	public boolean setState(Object content) {
		
		return false;
	}

	@Override
	public void setExpectationListener(ExpectationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPreafferenceListener(PreafferenceListener listener) {
		// TODO Auto-generated method stub
		
	}

}
