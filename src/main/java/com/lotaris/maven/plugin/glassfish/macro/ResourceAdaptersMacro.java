package com.lotaris.maven.plugin.glassfish.macro;

import com.lotaris.maven.plugin.glassfish.model.Configuration;
import com.lotaris.maven.plugin.glassfish.model.ResourceAdapter;


/**
 * The Resource Adapter macro manage the deployment of the different resource adapter for a domain.
 *  
 * @author Valentin Delaye <valentin.delaye@novaccess.ch>
 */
public class ResourceAdaptersMacro extends AbstractMacro {

	public ResourceAdaptersMacro(Configuration configuration) {
		super(configuration);
		
		// Configure the JMS Hosts if there are some
		if (configuration.getDomain().hasResourceAdapters()) {
			for (ResourceAdapter resourceAdapter : configuration.getDomain().getResourceAdapters()) {
				// Create and register the command
				registerCommand(new MacroMacroCommand(new DeployResourceAdapterMacro(configuration, resourceAdapter), "Deploying resource adapter."));
			}
		}
		
	}

}
