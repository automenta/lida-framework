package edu.memphis.ccrg.lida.actionselection.behaviornetwork;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.memphis.ccrg.lida.actionselection.Behavior;
import edu.memphis.ccrg.lida.framework.strategies.StrategyImpl;
import edu.memphis.ccrg.lida.framework.tasks.TaskManager;

/**
 * Strategy that determines how a predecessor is excited.
 * @author Ryan J. McCall
 *
 */
public class PredecessorExciteStrategy extends StrategyImpl implements BehaviorExciteStrategy {
	
	private static final Logger logger = Logger.getLogger(PredecessorExciteStrategy.class.getCanonicalName());
	
	private final double DEFAULT_EXCITATION_FACTOR = 0.6;
	
	private double predecessorExcitationFactor = DEFAULT_EXCITATION_FACTOR;

	@Override
	public void init(Map<String, ?> parameters) {
		predecessorExcitationFactor = (Double) parameters.get("factor");
	}
	
	@Override
	public void exciteBehavior(Behavior sourceBehavior, Behavior recipientBehavior){
		double amount = (sourceBehavior.getActivation() * predecessorExcitationFactor)
						/ sourceBehavior.getUnsatisfiedContextCount();
		recipientBehavior.excite(amount);
		logger.log(Level.FINEST, sourceBehavior.getActivation() + " "
				+ sourceBehavior.getLabel() + "<--" + amount
				+ " to " + recipientBehavior, TaskManager
				.getCurrentTick());
//		double granted = (behavior.getActivation() * predecessorExcitationFactor)
//		/ behavior.getUnsatisfiedContextCount();
//predecessor.excite(granted);
	}

}
