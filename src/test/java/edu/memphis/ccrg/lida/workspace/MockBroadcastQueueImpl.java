/*******************************************************************************
 * Copyright (c) 2009, 2011 The University of Memphis.  All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the LIDA Software Framework Non-Commercial License v1.0 
 * which accompanies this distribution, and is available at
 * http://ccrg.cs.memphis.edu/assets/papers/2010/LIDA-framework-non-commercial-v1.0.pdf
 *******************************************************************************/
package edu.memphis.ccrg.lida.workspace;

import edu.memphis.ccrg.lida.globalworkspace.BroadcastContent;
import edu.memphis.ccrg.lida.globalworkspace.Coalition;
import edu.memphis.ccrg.lida.workspace.workspacebuffers.BroadcastQueueImpl;

public class MockBroadcastQueueImpl extends BroadcastQueueImpl {

	public BroadcastContent broadcastContent;

	@Override
	public void receiveBroadcast(Coalition c) {
		this.broadcastContent = c.getContent();
	}

}
