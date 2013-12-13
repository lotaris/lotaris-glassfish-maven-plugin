package com.lotaris.maven.plugin.glassfish.macro;

import com.lotaris.maven.plugin.glassfish.model.Configuration;

import static com.lotaris.maven.plugin.glassfish.command.CommandFactory.*;
import com.lotaris.maven.plugin.glassfish.model.Property;

/**
 * This macro take care of creating set configuration for a domain
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class SetCommandMacro extends AbstractMacro {
	/**
	 * Constructor
	 * 
	 * @param configuration The configuration
	 */
	public SetCommandMacro(Configuration configuration) {
		super(configuration);

		if (configuration.getDomain().getProperties() != null) {
			for (Property property : configuration.getDomain().getProperties()) {
				registerCommand(new MacroCommand(buildSetCommand(configuration, property), "Setting domain property: " + property.getName()));
			}
		}
  }
}
