package com.lotaris.maven.plugin.glassfish.macro;

import com.lotaris.maven.plugin.glassfish.model.Configuration;
import com.lotaris.maven.plugin.glassfish.model.ResourceAdapter;

import static com.lotaris.maven.plugin.glassfish.command.CommandFactory.*;

/**
 * Deploy resource adapter macro
 * 
 * @author Valentin Delaye <valentin.delaye@novaccess.ch>
 */
public class DeployResourceAdapterMacro extends AbstractMacro {
	/**
	 * Constructor
	 * 
	 * @param configuration The configuration
	 * @param resourceAdapter The resource adapter to deploy
	 */
	public DeployResourceAdapterMacro(Configuration configuration, ResourceAdapter resourceAdapter) {
		super(configuration);
		registerCommand(new MacroCommand(buildDeployCommand(configuration, resourceAdapter.getDeployConfig()), "Deploying resource adapter [" + resourceAdapter.getDeployConfig().getName() + "]."));
	}
}
