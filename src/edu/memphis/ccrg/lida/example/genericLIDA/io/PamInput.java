package edu.memphis.ccrg.lida.example.genericLIDA.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.memphis.ccrg.lida.example.genericLIDA.pam.VisionFeatureDetector;
import edu.memphis.ccrg.lida.perception.PamNode;
import edu.memphis.ccrg.lida.perception.PamNodeImpl;
import edu.memphis.ccrg.lida.perception.PerceptualAssociativeMemory;
import edu.memphis.ccrg.lida.perception.featuredetector.FeatureDetector;
import edu.memphis.ccrg.lida.sensoryMemory.SensoryMemory;
import edu.memphis.ccrg.lida.shared.Link;
import edu.memphis.ccrg.lida.shared.NodeFactory;

public class PamInput {

	//TODO return a boolean for success/fail?
	public void read(PerceptualAssociativeMemory pam, SensoryMemory sm, String inputPath) {

		Map<String, Object> params = new HashMap<String, Object>();
		double upscale = 0.7, 
			   downscale = 0.5, 
			   selectivity = 0.9;		
		params.put("upscale", upscale);
		params.put("downscale", downscale);
		params.put("selectivity", selectivity);		
		pam.setParameters(params);
		
		//ADD NODES, FeatureDetectors, and LINKS
		Set<PamNode> nodes = new HashSet<PamNode>();
    	NodeFactory factory = NodeFactory.getInstance();
    	factory.addNodeType("PamNodeImpl", "edu.memphis.ccrg.lida.perception.PamNodeImpl");
    	PamNodeImpl gold = (PamNodeImpl)factory.getNode("PamNodeImpl", 1, "gold");
    	nodes.add(gold);
    	
    	//Feature detectors
    	List<FeatureDetector> featureDetectors = new ArrayList<FeatureDetector>();
    	//VisionDetectBehavior featureDetectorBehavior = new VisionDetectBehavior(); 
		featureDetectors.add(new VisionFeatureDetector(gold,sm,pam));
		
		Set<Link> links = new HashSet<Link>();    	
		pam.addToPAM(nodes, featureDetectors, links);
		
		try{
			//FileReader fr = new FileReader(inputPath);
		}catch(Exception e){
			System.out.println(e);
		}
		
	}//method
}//class