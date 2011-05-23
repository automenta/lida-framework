package edu.memphis.ccrg.lida.proceduralmemory;

import java.util.ArrayList;
import java.util.Collection;

import edu.memphis.ccrg.lida.framework.FrameworkModuleImpl;
import edu.memphis.ccrg.lida.framework.ModuleListener;
import edu.memphis.ccrg.lida.framework.shared.NodeStructure;

public class MockProceduralMemory extends FrameworkModuleImpl implements ProceduralMemory {

	@Override
	public void activateSchemes(NodeStructure broadcastContent) {
		// not implemented

	}

	@Override
	public void addScheme(Scheme s) {
		// not implemented

	}

	@Override
	public void addSchemes(Collection<Scheme> schemes) {
		// not implemented

	}

	@Override
	public boolean containsScheme(Scheme s) {
		// not implemented
		return false;
	}

	@Override
	public SchemeActivationStrategy getSchemeActivationStrategy() {
		// not implemented
		return null;
	}

	@Override
	public int getSchemeCount() {
		// not implemented
		return 0;
	}
	
	public Collection<Scheme> getTestInstantiated(){
		return instantiated;
	}
	
	public void clearInstantiated(){
		instantiated.clear();
	}
	
	private Collection<Scheme> instantiated = new ArrayList<Scheme>();

	@Override
	public void createInstantiation(Scheme s) {
		instantiated.add(s);
	}

	@Override
	public void setSchemeActivationStrategy(SchemeActivationStrategy b) {
		// not implemented

	}

	@Override
	public void addListener(ModuleListener listener) {
		// not implemented

	}

	@Override
	public void decayModule(long ticks) {
		// not implemented

	}

	@Override
	public Object getModuleContent(Object... params) {
		// not implemented
		return null;
	}

	@Override
	public void init() {
		// not implemented

	}
}
