package com.lotaris.maven.plugin.glassfish.command.argument;

/**
 * Define what is a an argument name
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public interface IArgumentName {
	/**
	 * @return Retrieve the textual name of an argument
	 */
	String getArgName();
	
	/**
	 * @return Retrieve if the argument is a boolean or not
	 */
	boolean isBoolean();
	
	/**
	 * @return Retrieve an eventual default value
	 */
	String getDefaultValue();
}
