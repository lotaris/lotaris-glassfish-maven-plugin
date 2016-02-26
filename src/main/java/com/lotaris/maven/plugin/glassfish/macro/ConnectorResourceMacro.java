package com.lotaris.maven.plugin.glassfish.macro;

import com.lotaris.maven.plugin.glassfish.model.Configuration;
import com.lotaris.maven.plugin.glassfish.model.ConnectorResource;

import static com.lotaris.maven.plugin.glassfish.command.CommandFactory.*;


/**
 * The Connector Resource macro manage the creation of the different connector resource
 * 
 * @author Valentin Delaye <valentin.delaye@novaccess.ch>
 */
public class ConnectorResourceMacro extends AbstractMacro {
	/**
	 * Constructor
	 * 
	 * @param configuration The configuration
	 */
	public ConnectorResourceMacro(Configuration configuration) {
		super(configuration);

		// Configure the Conector Resource if there are some
		if (configuration.getDomain().hasConnectorResources()) {
			
			for (ConnectorResource connectorResource : configuration.getDomain().getConnectorResources()) {
				// Create and register the command
				registerCommand(new MacroCommand(buildCreateConnectorConnectionPoolCommand(configuration, connectorResource), "Create the Connector resourc [" + connectorResource.getJndiName()+ "]."));
			}
		}
		
	}
}
