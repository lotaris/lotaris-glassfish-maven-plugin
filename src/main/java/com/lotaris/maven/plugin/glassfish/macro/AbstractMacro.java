package com.lotaris.maven.plugin.glassfish.macro;

import com.lotaris.maven.plugin.glassfish.command.CommandExecutor;
import com.lotaris.maven.plugin.glassfish.model.Configuration;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Base macro to run grouped commands
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public abstract class AbstractMacro {
	/**
	 * The general configuration to use
	 */
	protected Configuration configuration;

	/**
	 * The list of macro commands to run
	 */
	private final List<IMacroCommand> commands = new ArrayList<>();
	
	/**
	 * Constructor
	 * 
	 * @param configuration The configuration
	 */
	public AbstractMacro(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * Execute the macro
	 * 
	 * @param executor The executor to run the commands of the macro
	 * @throws MojoExecutionException
	 * @throws MojoFailureException 
	 */
	public void execute(CommandExecutor executor) throws MojoExecutionException, MojoFailureException {
		// Run each macro command
		for (IMacroCommand macroCommand : commands) {
			configuration.getLog().info("*****> " + macroCommand.getDescriptionText() + " <*****");
			macroCommand.execute(executor);
		}
	}

	/**
	 * Register a macro command to run
	 * 
	 * @param macroCommand The macro command to register
	 */
	protected void registerCommand(IMacroCommand macroCommand) {
		if (macroCommand != null) {
			commands.add(macroCommand);
		}
	}
	
	/**
	 * @return True if the domain is a local domain
	 */
	protected boolean isLocalDomain() {
		return configuration.isLocalDomain();
	}
}
