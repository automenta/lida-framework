package edu.memphis.ccrg.lida.framework;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.memphis.ccrg.lida.environment.EnvironmentImpl;
import edu.memphis.ccrg.lida.framework.gui.LidaGui;
import edu.memphis.ccrg.lida.framework.gui.LidaGuiController;
import edu.memphis.ccrg.lida.framework.gui.LidaGuiControllerImpl;
import edu.memphis.ccrg.lida.sensorymemory.SensoryMemoryImpl;

/**
 * For specific lida agent implementations create a main class simply 
 * need call this class's start method with particular parameters for that agent:
 * Environment, SensoryMemory and config file path.
 * 
 * @author ryanjmccall
 *
 */
public class LidaFactory {
	
	private static Logger logger = Logger.getLogger("lida.framework.LidaFactory");
	private static String commandsPropertiesPath = "configs/guiCommands.properties";

	public static void start(final EnvironmentImpl environment,
							  final SensoryMemoryImpl sensoryMemory, 
							  final Properties lidaProperties,
							  final Properties panelsProperties) {
		java.awt.EventQueue.invokeLater(new Runnable(){
			public void run(){					
	        	//Create the model 
	        	Lida lida = new Lida(environment, sensoryMemory, lidaProperties);
	        	//Create the controller
	        	LidaGuiController controller = new LidaGuiControllerImpl(lida, commandsPropertiesPath);
	        	LidaGui lidaGui = new LidaGui(lida, controller, panelsProperties);
	        	//Start the GUI
	            lidaGui.setVisible(true);
	        }//run
		});//invokeLater
		
	}//method

}//class