package com.lotaris.maven.plugin.glassfish.macro;

import com.lotaris.maven.plugin.glassfish.model.Configuration;

import com.lotaris.maven.plugin.glassfish.model.JdbcResource;

import static com.lotaris.maven.plugin.glassfish.command.CommandFactory.*;

/**
 * The JDBC Resource macro manage the creation of the different JDBC resources (connection pools, resources, ...)
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class JdbcResourcesMacro extends AbstractMacro {
	/**
	 * Constructor
	 * 
	 * @param configuration The configuration
	 */
	public JdbcResourcesMacro(Configuration configuration) {
		super(configuration);

		for (JdbcResource resource : configuration.getDomain().getJdbcResources()) {
			registerCommand(new MacroCommand(buildCreatJdbcConnectionPool(configuration, resource), "Create JDBC connection pool [" + resource.getJndiName() + "]."));
			registerCommand(new MacroCommand(buildCreateJdbcResource(configuration, resource), "Create JDBC resource [" + resource.getJndiName() + "]."));
		}
	}
}
