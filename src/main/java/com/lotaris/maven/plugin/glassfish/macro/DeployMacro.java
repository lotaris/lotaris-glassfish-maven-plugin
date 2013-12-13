package com.lotaris.maven.plugin.glassfish.macro;

import com.lotaris.maven.plugin.glassfish.model.Configuration;

import static com.lotaris.maven.plugin.glassfish.command.CommandFactory.*;

/**
 * Deploy application macro take care of the requirements (like domain created and started)
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class DeployMacro extends AbstractMacro {
	/**
	 * Constructor
	 * 
	 * @param configuration The configuration
	 */
	public DeployMacro(Configuration configuration) {
		super(configuration);

		// Check if the domain does not exist
		if (!configuration.getDomain().exists()) {
			registerCommand(new MacroMacroCommand(new CreateDomainMacro(configuration), "Creating domain."));
		}
		
		// Check if the domain is not started
		else if (!configuration.getDomain().isStarted()) {
			registerCommand(new MacroMacroCommand(new StartDomainMacro(configuration), "Starting domain."));
		}
		
		registerCommand(new MacroCommand(buildDeployCommand(configuration), "Deploying application [" + configuration.getDeployConfiguration().getName() + "]."));
	}
}
