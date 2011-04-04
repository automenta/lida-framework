/*******************************************************************************

 * Copyright (c) 2009, 2010 The University of Memphis.  All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the LIDA Software Framework Non-Commercial License v1.0 
 * which accompanies this distribution, and is available at
 * http://ccrg.cs.memphis.edu/assets/papers/2010/LIDA-framework-non-commercial-v1.0.pdf
 *******************************************************************************/
package edu.memphis.ccrg.lida.attentioncodelets;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.memphis.ccrg.lida.framework.LidaModule;
import edu.memphis.ccrg.lida.framework.initialization.ModuleUsage;
import edu.memphis.ccrg.lida.framework.shared.NodeStructure;
import edu.memphis.ccrg.lida.framework.tasks.CodeletImpl;
import edu.memphis.ccrg.lida.framework.tasks.LidaTaskManager;
import edu.memphis.ccrg.lida.globalworkspace.BroadcastContent;
import edu.memphis.ccrg.lida.globalworkspace.Coalition;
import edu.memphis.ccrg.lida.globalworkspace.CoalitionImpl;
import edu.memphis.ccrg.lida.globalworkspace.GlobalWorkspace;
import edu.memphis.ccrg.lida.workspace.workspaceBuffer.WorkspaceBuffer;

/**
 * Abstract implementation of {@link AttentionCodelet} that checks the CSM for desired
 * content.  If this is found it creates a
 * {@link Coalition} and adds it to the {@link GlobalWorkspace}.
 * 
 * @author Ryan J McCall
 * 
 */
public abstract class AttentionCodeletImpl extends CodeletImpl implements
		AttentionCodelet {

	private static final Logger logger = Logger
			.getLogger(AttentionCodeletImpl.class.getCanonicalName());

	/**
	 * Where codelet will look for and retrieve content from
	 */
	protected WorkspaceBuffer currentSituationalModel;

	protected GlobalWorkspace globalWorkspace;

	public AttentionCodeletImpl() {
		super();
	}

	/**
	 * Sets associated Module
	 * 
	 * @param module
	 *            the module to be associated with
	 * @param usage
	 *            - way of associating the module
	 */
	@Override
	public void setAssociatedModule(LidaModule module, String usage) {
		if (usage.equals(ModuleUsage.TO_READ_FROM)) {
			if (module instanceof WorkspaceBuffer) {
				currentSituationalModel = (WorkspaceBuffer) module;
			}
		} else if (usage.equals(ModuleUsage.TO_WRITE_TO)) {
			if (module instanceof GlobalWorkspace) {
				globalWorkspace = (GlobalWorkspace) module;
			}
		}else{
			logger.log(Level.WARNING, "Module useage not supported",
					LidaTaskManager.getCurrentTick());
		}
	}

	/**
	 * On finding sought content in CSM, create a coalition and put it in the
	 * global workspace
	 */
	@Override
	protected void runThisLidaTask() {
		if (hasSoughtContent(currentSituationalModel)) {
			NodeStructure csmContent = retreiveWorkspaceContent(currentSituationalModel);
			if (csmContent.getLinkableCount() > 0) {
				globalWorkspace.addCoalition(new CoalitionImpl(
						(BroadcastContent) csmContent, getActivation(), this));
				logger.log(Level.FINER, this + " adds coalition",
						LidaTaskManager.getCurrentTick());
			}
		}
	}

	@Override
	public String toString() {
		return "AttentionCodelet-" + getTaskId();
	}

}