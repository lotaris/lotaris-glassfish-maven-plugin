package com.lotaris.maven.plugin.glassfish.macro;

import com.lotaris.maven.plugin.glassfish.command.CommandBuilder;
import com.lotaris.maven.plugin.glassfish.command.CommandExecutor;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Command wrapper to decorate the command with a descriptive text.
 * Add the possibility to create macro to run multiple commands in a batch way.
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class MacroCommand implements IMacroCommand {
	/**
	 * A command to execute
	 */
	private CommandBuilder cmd;
	
	/**
	 * A description text that represent the macro command
	 */
	private String descriptionText;

	/**
	 * Constructor
	 * 
	 * @param descriptionText  The description text
	 */
	public MacroCommand(String descriptionText) {
		this.descriptionText = descriptionText;
	}
	
	/**
	 * Constructor
	 * 
	 * @param cmd The macro command to execute
	 * @param descriptionText The description text
	 */
	public MacroCommand(CommandBuilder cmd, String descriptionText) {
		this.cmd = cmd;
		this.descriptionText = descriptionText;
	}

	@Override
	public String getDescriptionText() {
		return descriptionText;
	}

	@Override
	public void execute(CommandExecutor executor) throws MojoExecutionException, MojoFailureException {
		if (cmd != null) {
			executor.execute(cmd);
		}
	}
	
	
}
