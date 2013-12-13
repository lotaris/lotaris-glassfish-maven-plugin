package com.lotaris.maven.plugin.glassfish.macro;

import com.lotaris.maven.plugin.glassfish.command.CommandExecutor;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Define what is a macro command to be run in a macro
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public interface IMacroCommand {
	/**
	 * Execute the macro command
	 * 
	 * @param executor The executor for the commands
	 * @throws MojoExecutionException
	 * @throws MojoFailureException 
	 */
	void execute(CommandExecutor executor) throws MojoExecutionException, MojoFailureException;
	
	/**
	 * @return Retrieve a description text for the macro command
	 */
	String getDescriptionText();
}
