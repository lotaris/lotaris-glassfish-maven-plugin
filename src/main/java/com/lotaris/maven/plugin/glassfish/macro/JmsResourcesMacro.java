package com.lotaris.maven.plugin.glassfish.macro;

import com.lotaris.maven.plugin.glassfish.model.Configuration;
import com.lotaris.maven.plugin.glassfish.model.ConnectionFactory;

import static com.lotaris.maven.plugin.glassfish.command.CommandFactory.*;
import com.lotaris.maven.plugin.glassfish.model.JmsHost;
import com.lotaris.maven.plugin.glassfish.model.JmsResource;
import com.lotaris.maven.plugin.glassfish.model.Property;

/**
 * The JMS Resource macro manage the creation of the different JMS resources (connection factories, resources, ...)
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class JmsResourcesMacro extends AbstractMacro {
	/**
	 * Constructor
	 * 
	 * @param configuration The configuration
	 */
	public JmsResourcesMacro(Configuration configuration) {
		super(configuration);

		// Delete the default JMS host
		if (configuration.getGlassfish().isDeleteDefaultJmsHost()) {
			registerCommand(new MacroCommand(buildDeleteDefaultJmsHost(configuration), "Delete the default JMS Host."));
		}
		
		// Configure the JMS Hosts if there are some
		if (configuration.getGlassfish().hasJmsHosts()) {
			for (JmsHost jmsHost : configuration.getGlassfish().getJmsHosts()) {
				// Create and register the command
				registerCommand(new MacroCommand(buildCreateJmsHostCommand(configuration, jmsHost), "Create the JMS Host [" + jmsHost.getName() + "]."));
				
				// Check if there are properties for the host
				if (jmsHost.hasProperties()) {
					for (Property property : jmsHost.getProperties()) {
						// Create and register the command
						registerCommand(
							new MacroCommand(
								buildSetJmsHostAttributeCommand(configuration, jmsHost, property), 
								"Set the JMS Host [" + jmsHost.getName() + "] attribute [" + property.getName() + "]."
							)
						);
					}
				}
			}
		}
		
		// Configure the JMS Service
		if (configuration.getGlassfish().getJmsService() != null && configuration.getGlassfish().getJmsService().hasProperties()) {
			for (Property property : configuration.getGlassfish().getJmsService().getProperties()) {
				// Create and register the command
				registerCommand(
					new MacroCommand(
						buildSetJmsServiceAttributeCommand(configuration, property), 
						"Set the JMS service attribute [" + property.getName() + "]."
					)
				);
			}
		}
		
		if (configuration.getDomain() != null) {
			// Create every connection factory
			if (configuration.getDomain().getConnectionFactories() != null) {
				for (ConnectionFactory connectionFactory : configuration.getDomain().getConnectionFactories()) {
					registerCommand(new MacroCommand(buildCreateJmsResourceCommand(configuration, connectionFactory), "Creation of the connection factory [" + connectionFactory.getJndiName() + "]."));
				}
			}
		
			// Create every JMS resource with its physical destination
			if (configuration.getDomain().getJmsResources() != null) {
				for (JmsResource resource : configuration.getDomain().getJmsResources()) {
					if (resource.isCreatePhysicalDestination()) {
						registerCommand(new MacroCommand(buildCreateJmsDestination(configuration, resource), "Creation of the JMS physical destination [" + resource .getJndiName() + "]."));
					}
					registerCommand(new MacroCommand(buildCreateJmsResourceCommand(configuration, resource), "Creation of the JMS resource [" + resource.getJndiName() + "]."));
				}
			}
		}
	}
}
