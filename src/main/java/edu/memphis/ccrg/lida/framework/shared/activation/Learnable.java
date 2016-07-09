/*******************************************************************************
 * Copyright (c) 2009, 2011 The University of Memphis.  All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the LIDA Software Framework Non-Commercial License v1.0 
 * which accompanies this distribution, and is available at
 * http://ccrg.cs.memphis.edu/assets/papers/2010/LIDA-framework-non-commercial-v1.0.pdf
 *******************************************************************************/
/**
 * 
 */
package edu.memphis.ccrg.lida.framework.shared.activation;

import edu.memphis.ccrg.lida.framework.strategies.DecayStrategy;
import edu.memphis.ccrg.lida.framework.strategies.ExciteStrategy;
import edu.memphis.ccrg.lida.framework.strategies.TotalActivationStrategy;

/**
 * An {@link Activatible} that additionally has a base-level activation. It is
 * used mostly for learning. The classes that implement this interface have a
 * decay and reinforce base level activation. If the base level activation
 * reaches zero, the element should be deleted (decay away)
 * 
 * @author Javier Snaider
 * @author Ryan J. McCall
 */
public interface Learnable extends Activatible {

	/**
	 * Default removal threshold for Learnable. With this value the Learnable
	 * will never decay away because base-level activation is never negative.
	 */
    double DEFAULT_LEARNABLE_REMOVAL_THRESHOLD = -1.0;

	/**
	 * Default base-level activation for Learnables.
	 */
    double DEFAULT_BASE_LEVEL_ACTIVATION = 0.5;
	
	/**
	 * Default base-level incentive salience for {@link Learnable}.
	 */
    double DEFAULT_BASE_LEVEL_INCENTIVE_SALIENCE = 0.0;
	
	/**
	 * Gets the base-level incentive salience of the {@link Learnable}.
	 * @return an amount of base-level incentive salience
	 */
    double getBaseLevelIncentiveSalience();

	/**
	 * Sets the base-level incentive salience of the Learnable
	 * @param s an amount of base-level incentive salience
	 */
    void setBaseLevelIncentiveSalience(double s);
	
	/**
	 * Positively updates the base-level incentive salience of this learnable by specified amount.
	 * @param a amount of reinforcment
	 */
    void reinforceBaseLevelIncentiveSalience(double a);
	/**
	 * Decays base-level incentive salience for specified number of ticks.
	 * @param t time ticks base-level incentive salience is to be decayed for
	 */
    void decayBaseLevelIncentiveSalience(long t);
	
	/**
	 * Returns base level activation.
	 * 
	 * @return activation representing the degree this Learnable has been
	 *         learned.
	 */
    double getBaseLevelActivation();

	/**
	 * Set base level activation. Used for initialization, not during regular
	 * execution, use {@link #reinforceBaseLevelActivation(double)} instead.
	 * 
	 * @param amount
	 *            new base level activation amount
	 */
    void setBaseLevelActivation(double amount);

	/**
	 * The Base Level activation of this node is increased using the excitation
	 * value as a parameter for the ExciteStrategy. This is primarily used for
	 * learning.
	 * 
	 * @param amount
	 *            the value to be used to increase the Base Level activation of
	 *            this node
	 */
    void reinforceBaseLevelActivation(double amount);

	/**
	 * decay the Base Level activation using the decay strategy. The decay
	 * depends on the time since the last decaying. It is indicated by the
	 * parameter ticks.
	 * 
	 * @param ticks
	 *            the number of ticks to decay
	 */
    void decayBaseLevelActivation(long ticks);

	/**
	 * Sets BaseLevelExciteStrategy
	 * 
	 * @param strategy
	 *            the Excite strategy for the current activation.
	 */
    void setBaseLevelExciteStrategy(ExciteStrategy strategy);

	/**
	 * Gets BaseLevelExciteStrategy
	 * 
	 * @return the excite strategy
	 */
    ExciteStrategy getBaseLevelExciteStrategy();

	/**
	 * Sets decay strategy for the Base Level activation
	 * 
	 * @param strategy
	 *            the decay strategy for the Base Level activation.
	 */
    void setBaseLevelDecayStrategy(DecayStrategy strategy);

	/**
	 * Gets decay strategy for the Base Level activation
	 * 
	 * @return the decay strategy for the Base Level activation.
	 */
    DecayStrategy getBaseLevelDecayStrategy();

	/**
	 * Sets learnableRemovalThreshold
	 * 
	 * @param threshold
	 *            threshold for removal of this Learnable
	 */
    void setBaseLevelRemovalThreshold(double threshold);

	/**
	 * Gets learnableRemovalThreshold
	 * 
	 * @return threshold for removal of this learnable
	 */
    double getLearnableRemovalThreshold();

	/**
	 * Returns {@link TotalActivationStrategy}
	 * 
	 * @return Strategy this Learnable uses to calculate total activation.
	 */
    TotalActivationStrategy getTotalActivationStrategy();

	/**
	 * Sets {@link TotalActivationStrategy}
	 * 
	 * @param strategy
	 *            Strategy this Learnable uses to calculate total activation.
	 */
    void setTotalActivationStrategy(TotalActivationStrategy strategy);

}