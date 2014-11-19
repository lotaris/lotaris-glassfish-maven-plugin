package com.lotaris.maven.plugin.glassfish.macro;

import com.lotaris.maven.plugin.glassfish.command.CommandExecutor;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * A macro macro command allows running a macro as it is a macro command. It serves
 * as a wrapper for the {@link AbstractMacro}
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class MacroMacroCommand implements IMacroCommand {
	/**
	 * The macro to run as a command macro
	 */
	private final AbstractMacro macro;

	/**
	 * The descriptive text
	 */
	private final String descriptionText;

	/**
	 * Constructor
	 * 
	 * @param macro The macro to run as a macro command
	 * @param descriptionText The descriptive text
	 */
	public MacroMacroCommand(AbstractMacro macro, String descriptionText) {
		this.macro = macro;
		this.descriptionText = descriptionText;
	}

	@Override
	public void execute(CommandExecutor executor) throws MojoExecutionException, MojoFailureException {
		macro.execute(executor);
	}
	
	@Override
	public String getDescriptionText() {
		return descriptionText;
	}
}
