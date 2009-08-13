package edu.memphis.ccrg.lida.pam.featuredetector;

import java.util.Map;

import edu.memphis.ccrg.lida.pam.PamNode;
import edu.memphis.ccrg.lida.pam.PamNodeImpl;
import edu.memphis.ccrg.lida.pam.PerceptualAssociativeMemory;
import edu.memphis.ccrg.lida.sensorymemory.SensoryMemory;
import edu.memphis.ccrg.lida.shared.ActivatibleImpl;
/**
 * This class implements the FeatureDetector interface and provides 
 * hook default methods. 
 * Users should extend this class and overwrite the detect() and burstPam() methods.
 * A convenience init() method is added to initialize the class. This method can be 
 * overwritten as well.
 * This implementation is oriented to detect features from sensoryMemory, but the implementation 
 * can be used to detect and burstActivation from other modules, like Workspace, emotions or internal states.
 * 
 * @author Ryan McCall - Javier Snaider
 *
 */
public class FeatureDetectorImpl extends ActivatibleImpl implements
		FeatureDetector {

	private PamNode pamNode;
	private PerceptualAssociativeMemory pam;
	protected SensoryMemory sm;

	public FeatureDetectorImpl(PamNode n, SensoryMemory sm,
			PerceptualAssociativeMemory pam) {
		this.pam = pam;
		this.sm = sm;
		pamNode = n;
	}

	public FeatureDetectorImpl(FeatureDetectorImpl n) {
		this.pam = n.pam;
		this.sm = n.sm;
		pamNode = n.pamNode;
	}

	public void setNode(PamNode node) {
		pamNode = (PamNodeImpl) node;
	}
	public PamNode getPamNode() {
		return pamNode;
	}

	public void executeDetection() {
		excitePam(detect());
	}
	
	public double detect() {
		return 0.0;
	}
	
	public void excitePam(double amount){
		pam.receiveActivationBurst(pamNode, amount);
	}
	
	/**
	 * @return the SensoryMemory
	 */
	protected SensoryMemory getSensoryMemory() {
		return sm;
	}

	public void init(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
	}
	
}// class