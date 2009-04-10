package edu.memphis.ccrg.lida.perception;

import edu.memphis.ccrg.lida.shared.strategies.DetectBehavior;
import edu.memphis.ccrg.lida.wumpusWorld.b_sensoryMemory.SensoryContentImpl;

public interface FeatureDetector {
	public void detect(SensoryContentImpl sm); 
	public void setDetectBehavior(DetectBehavior b);
}
