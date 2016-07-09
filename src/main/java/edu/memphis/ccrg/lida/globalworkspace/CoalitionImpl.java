/*******************************************************************************
 * Copyright (c) 2009, 2011 The University of Memphis.  All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the LIDA Software Framework Non-Commercial License v1.0 
 * which accompanies this distribution, and is available at
 * http://ccrg.cs.memphis.edu/assets/papers/2010/LIDA-framework-non-commercial-v1.0.pdf
 *******************************************************************************/
package edu.memphis.ccrg.lida.globalworkspace;

import edu.memphis.ccrg.lida.attentioncodelets.AttentionCodelet;
import edu.memphis.ccrg.lida.attentioncodelets.AttentionCodeletImpl;
import edu.memphis.ccrg.lida.framework.shared.Linkable;
import edu.memphis.ccrg.lida.framework.shared.NodeStructure;
import edu.memphis.ccrg.lida.framework.shared.UnmodifiableNodeStructureImpl;
import edu.memphis.ccrg.lida.framework.shared.activation.ActivatibleImpl;
import edu.memphis.ccrg.lida.framework.tasks.TaskManager;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The default implementation of {@link Coalition}. Wraps content entering the
 * {@link GlobalWorkspace} to compete for consciousness. Extends
 * {@link ActivatibleImpl}. Contains reference to the {@link AttentionCodelet}
 * that created it.
 * 
 * @author Ryan J. McCall
 * @see AttentionCodeletImpl
 */
public class CoalitionImpl extends ActivatibleImpl implements Coalition {

	private static final Logger logger = Logger.getLogger(CoalitionImpl.class
			.getCanonicalName());
	private static int idCounter = 0;
	/*
	 * unique id
	 */
	private int id;
	/**
	 * the {@link BroadcastContent} of the coalition
	 */
	protected NodeStructure broadcastContent;

	/**
	 * the {@link AttentionCodelet} that created the coalition
	 */
	protected AttentionCodelet creatingAttentionCodelet;

	/**
	 * Default constructor
	 */
	public CoalitionImpl() {
		super();
		id = idCounter++;
	}

	/**
	 * Constructs a {@link CoalitionImpl} with specified content that is being
	 * created by specified {@link AttentionCodelet}
	 * 
	 * @param ns
	 *            the {@link BroadcastContent}
	 * @param c
	 *            The {@link AttentionCodelet} that created this Coalition
	 * @see AttentionCodeletImpl
	 */
	public CoalitionImpl(NodeStructure ns, AttentionCodelet c) {
		this();
		broadcastContent = new UnmodifiableNodeStructureImpl(ns,true);
		creatingAttentionCodelet = c;
		if(creatingAttentionCodelet != null) {
			updateActivation();
		}else{
			logger.log(Level.WARNING, "Coalition received null AttentionCodelet",
					TaskManager.getCurrentTick());
		}
	}

	/**
	 * Calculates and sets the coalition's activation. This implementation uses the
	 * average total activation and total incentive salience of the broadcast content 
	 * multiplied by the attention codelet's base-level activation.
	 */
	protected void updateActivation() {
		double salienceSum = 0.0;
		for (Linkable lnk: broadcastContent.getLinkables()) {
			salienceSum += lnk.getTotalActivation()+lnk.getTotalIncentiveSalience();
		}
		double aveLinkableSalience = salienceSum/(2.0*broadcastContent.getLinkableCount());
		double a = creatingAttentionCodelet.getBaseLevelActivation()*aveLinkableSalience;
		setActivation(a);
	}

	/**
	 * Returns an {@link UnmodifiableNodeStructureImpl} containing the broadcast
	 * content. Note that {@link UnmodifiableNodeStructureImpl} cannot be
	 * modified.
	 */
	@Override
	public BroadcastContent getContent() {
		return (BroadcastContent) broadcastContent;
	}

	@Override
	public AttentionCodelet getCreatingAttentionCodelet() {
		return creatingAttentionCodelet;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof CoalitionImpl) {
			CoalitionImpl c = (CoalitionImpl) o;
			return id == c.id;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return id;
	}
}