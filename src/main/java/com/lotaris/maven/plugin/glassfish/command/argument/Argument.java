package com.lotaris.maven.plugin.glassfish.command.argument;

import com.lotaris.maven.plugin.glassfish.model.Property;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * This argument class allows storing element that represent an argument
 * of the ASADMIN util
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 * @param <Name> The type of the argument (differentiate the argument of ASADMIN util and argument of sub-commands)
 */
public class Argument<Name extends IArgumentName> {
	/**
	 * Syntax markers
	 */
	private static final String ARG_SUFFIX = "--";
	private static final String ARG_EQUAL = "=";
	
	/**
	 * The argument name
	 */
	private Name name;
	
	/**
	 * The argument value
	 */
	private String value;
	
	/**
	 * Constructor
	 * 
	 * @param name The argument name
	 */
	public Argument(Name name) {
		this.name = name;
	}

	public IArgumentName getName() {
		return name;
	}
	
	public Argument setValue(String value) {
		this.value = value;
		return this;
	}
	
	public Argument setValue(boolean value) {
		this.value = "" + value;
		return this;
	}
	
	public Argument setValue(int value) {
		this.value = "" + value;
		return this;
	}
	
	public Argument setPropertiesValue(Set<Property> value) {
		this.value = formatProperties(value);
		return this;
	}
	
	public Argument setStringsValue(Set<String> value) {
		this.value = formatStrings(value);
		return this;
	}
	
	/**
	 * Build the argument based on the current configuration
	 * 
	 * @return The list of argument elements ready to be injected in {@link ProcessBuilder}
	 */
	public List<String> buildArgument() {
		List<String> elements = new ArrayList<>();
		
		// Check if the argument has not a name
		if (name.getArgName() == null) {
			// Check if there is no value
			if (value == null) {
				// Check if the default value exists
				if (name.getDefaultValue() != null) {
					elements.add(name.getDefaultValue());
				}
					
				// A default value must be present when there is no value
				else {
					throw new IllegalArgumentException("You must provide a value or a default value for an argument without a name.");
				}
			}
			
			// A value is present
			else {
				elements.add(value);
			}
		}
		
		// A name is present
		else {
			// Check if there is no value
			if (value == null) {
				// Check if there is no default value
				if (name.getDefaultValue() != null) {
					// Check if the argument has an equal syntax
					if (name.isBoolean()) {
						elements.add(ARG_SUFFIX + name.getArgName() + ARG_EQUAL + name.getDefaultValue());
					}

					// Not an equal syntax
					else {
						elements.add(ARG_SUFFIX + name.getArgName());
						elements.add(name.getDefaultValue());
					}
				}
				else {
					elements.add(ARG_SUFFIX + name.getArgName());
				}
			}

			// There is a value
			else {
				// Check if the argument is an equal syntax
				if (name.isBoolean()) {
					elements.add(ARG_SUFFIX + name.getArgName() + ARG_EQUAL + value);
				}

				// Not an equal syntax
				else {
					elements.add(ARG_SUFFIX + name.getArgName());
					elements.add(value);
				}
			}
		}
		return elements;
	}
	
	/**
	 * Escape command line value
	 * 
	 * @param value The value to escape
	 * @param chars The characters to be escaped
	 * @return The value escaped
	 */
	private static String escape(String value, String chars) {
		return escape(value, chars, "\\\\");
	}

	/**
	 * Escape command line value
	 * 
	 * @param value The value to escape
	 * @param chars The characters to be escaped
	 * @param escapeSequence The escape sequence to use
	 * @return The value escaped
	 */
	private static String escape(String value, String chars, String escapeSequence) {
		String escaped = value;
		
		if (escaped == null) {
			return "";
		}
		
		for (char ch : chars.toCharArray()) {
			escaped = escaped.replaceAll(String.valueOf(ch), escapeSequence + ch);
		}
	
		return escaped;
	}

	/**
	 * Format a property collection (apply the escaping)
	 * 
	 * @param objectProperties The properties to format
	 * @return The properties formatted
	 */
	private static String formatProperties(Set<Property> objectProperties) {
		if (objectProperties != null && !objectProperties.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			
			for (Property property : objectProperties) {
				sb.append(':').append(escape(property.getName(), "=;:")).append("=").append(escape(property.getValue(), "=;:"));
			}

			return sb.substring(1);
		}
		else {
			return "";
		}
	}
	
	/**
	 * Set a list of string
	 * 
	 * @param strings The set of string to format
	 * @return The set of string formated
	 */
	private static String formatStrings(Set<String> strings) {
		if (strings != null && !strings.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			
			for (String str : strings) {
				sb.append(':').append(escape(str, "=;:"));
			}

			return sb.substring(1);
		}
		else {
			return "";
		}
	}
}
