package com.lotaris.maven.plugin.glassfish.macro;

import com.lotaris.maven.plugin.glassfish.model.Configuration;

import static com.lotaris.maven.plugin.glassfish.command.CommandFactory.*;

/**
 * Create domain macro is used to create a domain. Ensure that the prerequisites conditions are
 * matched to create a new domain (is a domain already exists, started, ...).
 * 
 * Once the domain is created, the different resources (jdbc, jms...) are created.
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class CreateDomainMacro extends AbstractMacro {
	/**
	 * Constructor
	 * 
	 * @param configuration The configuration
	 */
	public CreateDomainMacro(Configuration configuration) {
		super(configuration);

		if (isLocalDomain()) {
			throw new UnsupportedOperationException("The creation of a domain on a remote host is not supported at the moment.");
		}
		
		// Check if the domain exists
		if (configuration.getDomain().exists()) {
			// Check if we can reuse it
			if (configuration.getDomain().isReuse()) {
				registerCommand(new MacroCommand("Domain exists. Reusing it."));
			}
			else {
				// Check if the domain is started
				if (configuration.getDomain().isStarted()) {
					registerCommand(new MacroCommand(buildStopDomainCommand(configuration), "Domain is started. Stopping it."));
				}
				registerCommand(new MacroCommand(buildDeleteDomainCommand(configuration), "Domain exists. Deleting it."));
			}
		}
		
		registerCommand(new MacroCommand(buildCreateDomainCommand(configuration), "Creating domain."));
		registerCommand(new MacroCommand(buildStartDomainCommand(configuration), "Starting domain."));
		registerCommand(new MacroMacroCommand(new JvmOptionsMacro(configuration), "Managing the JVM options."));
		registerCommand(new MacroMacroCommand(new SetCommandMacro(configuration), "Setting additional domain properties"));
		registerCommand(new MacroCommand(buildSetLoggingAttributesCommand(configuration), "Setting the logging attributes."));
		registerCommand(new MacroMacroCommand(new JmsResourcesMacro(configuration), "Managing JMS Resources."));
		registerCommand(new MacroMacroCommand(new JdbcResourcesMacro(configuration), "Managing JDBC Resources."));
		registerCommand(new MacroMacroCommand(new ResourceAdaptersMacro(configuration), "Managing deployment of Resource Adapter"));
		registerCommand(new MacroMacroCommand(new ConnectorConnectionPoolsMacro(configuration), "Creating Connectors Connection Pools."));
		registerCommand(new MacroMacroCommand(new ConnectorResourceMacro(configuration), "Creating Connectors Resources."));
		registerCommand(new MacroCommand(buildStopDomainCommand(configuration), "Stopping domain."));
	}
}
