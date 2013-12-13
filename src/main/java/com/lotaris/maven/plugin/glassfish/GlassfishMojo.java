package com.lotaris.maven.plugin.glassfish;

import com.lotaris.maven.plugin.glassfish.command.CommandExecutor;
import com.lotaris.maven.plugin.glassfish.macro.AbstractMacro;
import com.lotaris.maven.plugin.glassfish.model.Configuration;
import com.lotaris.maven.plugin.glassfish.model.Glassfish;
import com.lotaris.maven.plugin.glassfish.model.Domain;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Shared code for the different Maven Goals to manage a Glassfish 3/4 domain
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public abstract class GlassfishMojo extends AbstractMojo {
	/**
	 * Container for domain configuration parameters.
	 */
	@Parameter(required = true)
	protected Domain domain;

	/**
	 * General Glassfish configuration
	 */
	@Parameter(required = true)
	protected Glassfish glassfish;
	
	/**
	 * shared configuration across all the commands
	 */
	protected Configuration configuration;

	/**
	 * First run a post configuration process to ensure that the configuration
	 * is well done and complete the missing elements and/or defaults value
	 * 
	 * @throws MojoExecutionException
	 * @throws MojoFailureException 
	 */
	public void execute() throws MojoExecutionException, MojoFailureException {
		postConfig();
		getMacro().execute(new CommandExecutor(configuration));
	}

	/**
	 * Process the post configuration actions to ensure that everything
	 * can be run correctly with the proper configuration at every level
	 * 
	 * @throws MojoConfigurationException 
	 */
	private void postConfig() throws MojoExecutionException {
		// Get the list of errors
		List<String> configErrors = getConfigErrors();
		if (!configErrors.isEmpty()) {
			for (String s : configErrors) {
				getLog().error(s);
			}
			throw new MojoExecutionException("Unable to execute the maven goal");
		}
		
		// Create the share configuration
		this.configuration = buildConfiguration();

		// Set the domain directory if not already set
		if (glassfish.getDomainDirectory() == null) {
			glassfish.setDomainDirectory(new File(glassfish.getGlassfishDirectory(), "domains")); 
		}
		
		// Do the same for the domain configuration
		if (domain.getDirectory() == null) {
			domain.setDirectory(glassfish.getDomainDirectory());
		}
		
		if (getLog().isDebugEnabled()) {
			getLog().debug("Finalized configuration: " + configuration);
		}
	}

	/**
	 * @return  Retrieve a list of configuration errors
	 */
	private List<String> getConfigErrors() {
		List<String> errors = new ArrayList<>();
		
		// Check if the password file is set
		if (glassfish.getPasswordFile() == null) {
			StringBuilder error = new StringBuilder()
				.append("inside the definition for plugin: 'maven-lotaris-glassfish-plugin' specify the following:\n\n")
				.append("<configuration>\n")
				.append("  ...\n")
				.append("  <adminPasswordFile>VALUE</adminPasswordFile>\n")
				.append("  ...\n")
				.append("</configuration>\n");
			errors.add(error.toString());
		}
		
		return errors;
	}
	
	/**
	 * Build the configuration
	 * 
	 * @return The configuration
	 */
	protected Configuration buildConfiguration() {
		return new Configuration(getLog(), glassfish, domain);
	}
	
	/**
	 * Retrieve the macro to run for the Maven Goal
	 * @return 
	 */
	protected abstract AbstractMacro getMacro();
	
}
