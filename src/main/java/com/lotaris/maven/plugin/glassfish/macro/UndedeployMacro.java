package com.lotaris.maven.plugin.glassfish.macro;

import com.lotaris.maven.plugin.glassfish.model.Configuration;

import static com.lotaris.maven.plugin.glassfish.command.CommandFactory.*;

/**
 * Undeploy application macro take care of the requirements (like domain created and started)
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class UndedeployMacro extends AbstractMacro {
	/**
	 * Constructor
	 * 
	 * @param configuration The configuration
	 */
	public UndedeployMacro(Configuration configuration) {
		super(configuration);

		// Check if the domain does not exist
		if (configuration.getDomain().exists()) {
			// Check if the domain is not started
			if (isLocalDomain() && !configuration.getDomain().isStarted()) {
				registerCommand(new MacroMacroCommand(new StartDomainMacro(configuration), "Starting domain."));
			}
			
			registerCommand(new MacroCommand(buildUndeployCommand(configuration), "Undeploying application + [" + configuration.getUndeployConfiguration().getName() + "]."));	
		}
		
	}
}
