package edu.memphis.ccrg.lida.actionselection;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.memphis.ccrg.lida.actionselection.behaviornetwork.main.Behavior;
import edu.memphis.ccrg.lida.actionselection.behaviornetwork.main.ExpectationListener;
import edu.memphis.ccrg.lida.actionselection.behaviornetwork.main.PreafferenceListener;
import edu.memphis.ccrg.lida.actionselection.behaviornetwork.main.Stream;
import edu.memphis.ccrg.lida.framework.LidaModuleImpl;
import edu.memphis.ccrg.lida.framework.ModuleListener;
import edu.memphis.ccrg.lida.framework.ModuleName;
import edu.memphis.ccrg.lida.framework.gui.events.FrameworkGuiEvent;
import edu.memphis.ccrg.lida.framework.gui.events.FrameworkGuiEventListener;
import edu.memphis.ccrg.lida.framework.gui.events.TaskCountEvent;
import edu.memphis.ccrg.lida.framework.tasks.LidaTaskManager;
import edu.memphis.ccrg.lida.framework.tasks.TaskSpawner;
import edu.memphis.ccrg.lida.proceduralmemory.ProceduralMemoryListener;
import edu.memphis.ccrg.lida.proceduralmemory.Scheme;

/**
 * Rudimentary action selection that selects all behaviors sent to it which are
 * above the selectionThreshold.  Only selects an action every 'selectionFrequency' number of 
 * cycles it is run
 * 
 * @author Ryan J McCall
 *
 */
public class ActionSelectionImpl extends LidaModuleImpl implements ActionSelection, ProceduralMemoryListener{
	
	private static Logger logger = Logger.getLogger("lida.actionselection.ActionSelectionImpl");
	
	private double selectionThreshold = 0.95;
	
	private int selectionFrequency = 100, coolDownCounter = 0;
	
	private Queue<Scheme> behaviors = new ConcurrentLinkedQueue<Scheme>();
	private AtomicBoolean actionSelectionStarted = new AtomicBoolean(false);
	private List<FrameworkGuiEventListener> guis = new ArrayList<FrameworkGuiEventListener>();
	private ActionSelectionDriver asd=new ActionSelectionDriver();
	
	public ActionSelectionImpl( ) {
		super(ModuleName.ActionSelection);
		
	}

	private List<ActionSelectionListener> listeners = new ArrayList<ActionSelectionListener>();

	public void addActionSelectionListener(ActionSelectionListener listener) {
		listeners.add(listener);
	}
	
	public void receiveBehavior(Behavior b) {
		if(b.getActivation() > selectionThreshold){
			if(coolDownCounter == selectionFrequency){
				logger.log(Level.FINE, 
						   "selecting behavior " + b.getLabel() + " " + b.getId() + " " + b.getActionId() + " activ. " + b.getActivation(), 
						   LidaTaskManager.getActualTick());
				sendAction(b.getActionId());
				coolDownCounter = 0;
			}else
				coolDownCounter++;
			
			logger.log(Level.FINE, "Selected action: " + b.getActionId(), LidaTaskManager.getActualTick());
		}
	}
	
	public void sendAction(long schemeActionId){
		for(ActionSelectionListener l: listeners)
			l.receiveActionId(schemeActionId);
	}

	public Object getModuleContent() {
		return null;
	}
	public void addListener(ModuleListener listener) {
		if (listener instanceof ActionSelectionListener){
			addActionSelectionListener((ActionSelectionListener)listener);
		}
	}

	@Override
	public void triggerActionSelection() {
		if (actionSelectionStarted.compareAndSet(false, true)) {
			selectAction();
		}
	}

	@Override
	public void selectAction() {
		
		Scheme coal;
		coal = chooseBehavior();
		if (coal != null) {
			behaviors.remove(coal);
		}
	
	if (coal != null) {
		long id = coal.getId();
		for (ActionSelectionListener bl : listeners) {
			bl.receiveActionId(id);
			
		}
		FrameworkGuiEvent ge = new TaskCountEvent(ModuleName.ActionSelection, behaviors.size()+"");
		sendEventToGui(ge);
	}
	logger.log(Level.FINE,"Action Selection Performed at tick: {0}",LidaTaskManager.getActualTick());

	asd.resetTriggers();
	actionSelectionStarted.set(false);
		
	}
	
	public void sendEventToGui(FrameworkGuiEvent evt) {
		for (FrameworkGuiEventListener fg : guis)
			fg.receiveFrameworkGuiEvent(evt);
	}

	public Scheme chooseBehavior() {
		Scheme chosenBehav = null;
		for (Scheme c : behaviors) {
			if (chosenBehav == null
					|| c.getActivation() > chosenBehav.getActivation()) {
				chosenBehav = c;
			}
		}// for
		return chosenBehav;
	}// method

	public boolean addBehavior(Scheme behavior) {
		if (behaviors.add(behavior)) {
			logger.log(Level.FINE,"New Behavior added",LidaTaskManager.getActualTick());
			asd.newBehaviorEvent(behaviors);
			return true;
		} else {
			return false;
		}
	}// method
	
	

	@Override
	public void setTaskSpawner(TaskSpawner taskSpawner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveStream(Stream s) {
		// TODO Auto-generated method stub
		
	}

        public Object getState() {
            Object[] state = new Object[4];
            state[0] = this.behaviors;
            state[1] = null;
            state[2] = null;
            state[3] = null;
            return state;
        }
        @SuppressWarnings("unchecked")
		public boolean setState(Object content) {
            if (content instanceof Object[]) {
                Object[] state = (Object[])content;
                if (state.length == 4) {
                    try {
                        this.behaviors = (Queue<Scheme>)state[0];
                        return true;
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
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
}//class
