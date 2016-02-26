package com.lotaris.maven.plugin.glassfish.macro;

import com.lotaris.maven.plugin.glassfish.model.Configuration;
import com.lotaris.maven.plugin.glassfish.model.ConnectorConnectionPool;

import static com.lotaris.maven.plugin.glassfish.command.CommandFactory.*;


/**
 * The JMS Resource macro manage the creation of the different connector connection pool
 * 
 * @author Valentin Delaye <valentin.delaye@novaccess.ch>
 */
public class ConnectorConnectionPoolsMacro extends AbstractMacro {
	/**
	 * Constructor
	 * 
	 * @param configuration The configuration
	 */
	public ConnectorConnectionPoolsMacro(Configuration configuration) {
		super(configuration);

		// Configure the Conector Connection pool if there are some
		if (configuration.getDomain().hasConnectorConnectionPools()) {
			
			for (ConnectorConnectionPool connectionPool : configuration.getDomain().getConnectorConnectionPools()) {
				// Create and register the command
				registerCommand(new MacroCommand(buildCreateConnectorConnectionPoolCommand(configuration, connectionPool), "Create the Connector connection pool [" + connectionPool.getJndiName()+ "]."));
			}
		}
		
	}
}
