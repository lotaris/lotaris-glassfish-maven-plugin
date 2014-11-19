package com.lotaris.maven.plugin.glassfish.command;

import com.lotaris.maven.plugin.glassfish.command.argument.Argument;
import com.lotaris.maven.plugin.glassfish.command.argument.AsadminArgumentName;
import com.lotaris.maven.plugin.glassfish.command.argument.CommandArgumentName;
import com.lotaris.maven.plugin.glassfish.model.Configuration;
import java.util.ArrayList;
import java.util.List;

/**
 * The Command Builder class provides the mechanism to build an ASADMIN command line
 * with the different parts of the commands:
 *	- ASADMIN utils args
 *  - Command name
 *  - Command arguments
 *
 * Once the builder has all the command lines stuff, you can retrieve the command line elements
 * ready to be used with a {@link ProcessBuilder}
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class CommandBuilder {
	/**
	 * The command name
	 */
	private final CommandName name;
	
	/**
	 * The ASADMIN util arguments
	 */
	private final List<String> asadminArguments;

	/**
	 * The command arguments
	 */
	private final List<String> commandArguments;
	
	/**
	 * A friendly message to add in case of error
	 */
	private String friendlyErrorMessage;
	
	/**
	 * Constructor
	 * 
	 * @param name The command name
	 */
	public CommandBuilder(CommandName name) {
		this.name = name;
		commandArguments = new ArrayList<>();
		asadminArguments = new ArrayList<>();
	}
	
	/**
	 * Add a command argument
	 * 
	 * @param argument The argument to add
	 * @return this
	 */
	public CommandBuilder addArgument(Argument<CommandArgumentName> argument) {
		if (argument != null) {
			commandArguments.addAll(argument.buildArgument());
		}
		return this;
	}
	
	/**
	 * @param message The friendly error message
	 * @return this
	 */
	public CommandBuilder setFriendlyErrorMessage(String message) {
		this.friendlyErrorMessage = message;
		return this;
	}

	/**
	 * @return Retrieve the friendly error message
	 */
	public String getFriendlyErrorMessage() {
		return friendlyErrorMessage;
	}
	
	/**
	 * Setup the main ASADMIN arguments like user/password, interactive mode
	 * trace and debug, host and port...
	 * 
	 * @param configuration The configuration to get the different value for the arguments
	 * @return this
	 */
	public CommandBuilder setupAsadminArguments(Configuration configuration) {
		asadminArguments.addAll(AsadminArgumentName.INTERACTIVE.create().buildArgument());

		// Trace arguments
		if (configuration.getGlassfish().isEcho()) {
			asadminArguments.addAll(AsadminArgumentName.ECHO.create().buildArgument());
		}
		asadminArguments.addAll(AsadminArgumentName.TERSE.create(configuration).buildArgument());
		
		// Credentials arguments
		asadminArguments.addAll(AsadminArgumentName.USER.create(configuration).buildArgument());
		asadminArguments.addAll(AsadminArgumentName.PASSWORDFILE.create(configuration).buildArgument());

		// Connection info
		if (configuration.getDomain().getHost() != null) {
			asadminArguments.addAll(AsadminArgumentName.HOST.create(configuration).buildArgument());
		}
		if (configuration.getDomain().getAdminPort() > 0) {
			asadminArguments.addAll(AsadminArgumentName.PORT.create(configuration).buildArgument());
		}
		
		return this;
	}
	
	/**
	 * Get the command list. Consists in a list of all different elements
	 * to be injected in {@link ProcessBuilder}
	 * 
	 * @param asadminPath The ASADMIN util path
	 * @return The command elements
	 */
	public List<String> getCommandLine(String asadminPath) {
		List<String> cmdLine = new ArrayList<>();
		
		// The command line tool
		cmdLine.add(asadminPath);
		
		// The command line util arguments
		if (!asadminArguments.isEmpty()) {
			cmdLine.addAll(asadminArguments);
		}
		
		// The command
		cmdLine.add(name.getCmd());
		
		// The command arguments
		if (!commandArguments.isEmpty()) {
			cmdLine.addAll(commandArguments);
		}
		
		return cmdLine;
	}
}
