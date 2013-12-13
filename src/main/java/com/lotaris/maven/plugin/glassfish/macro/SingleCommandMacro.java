package com.lotaris.maven.plugin.glassfish.macro;

import com.lotaris.maven.plugin.glassfish.command.CommandBuilder;
import com.lotaris.maven.plugin.glassfish.model.Configuration;

/**
 * The single command macro can be used to just run one command in the macro mecanism that is
 * used everywhere to run the commands.
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class SingleCommandMacro extends AbstractMacro {
	/**
	 * Constructor
	 * 
	 * @param configuration The configuration
	 * @param command The command to run
	 * @param descriptionText The descriptive text of the command
	 */
	public SingleCommandMacro(Configuration configuration, CommandBuilder command, String descriptionText) {
		super(configuration);
		registerCommand(new MacroCommand(command, descriptionText));
	}
}
