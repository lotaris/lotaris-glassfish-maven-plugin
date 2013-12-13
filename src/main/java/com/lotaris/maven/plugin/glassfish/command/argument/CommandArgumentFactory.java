package com.lotaris.maven.plugin.glassfish.command.argument;

import com.lotaris.maven.plugin.glassfish.model.Configuration;
import com.lotaris.maven.plugin.glassfish.model.Property;
import java.util.HashSet;
import java.util.Set;

import static com.lotaris.maven.plugin.glassfish.command.argument.CommandArgumentName.*;

/**
 * Command argument factory. Serves to build the different arguments used in the
 * commnds.
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class CommandArgumentFactory {
	/**
	 * Standard builder of argument of String type
	 * 
	 * @param name The argument name
	 * @param value The argument value
	 * @return The argument
	 */
	public static Argument<CommandArgumentName> buildStringArgument(CommandArgumentName name, String value) {
		if (value != null && !value.isEmpty()) {
			return create(name).setValue(value);
		}
		else {
			return null;
		}
	}
	
	/**
	 * Standard builder of argument of Integer type
	 * 
	 * @param name The argument name
	 * @param value The argument value
	 * @return The argument
	 */
	public static Argument<CommandArgumentName> buildIntegerArgument(CommandArgumentName name, Integer value) {
		if (value != null) {
			return create(name).setValue(value);
		}
		else {
			return null;
		}
	}
	
	/**
	 * Standard builder of argument of Boolean type
	 * 
	 * @param name The argument name
	 * @param value The argument value
	 * @return The argument
	 */
	public static Argument<CommandArgumentName> buildBooleanArgument(CommandArgumentName name, Boolean value) {
		if (value != null) {
			return create(name).setValue(value);
		}
		else { 
			return null;
		}
	}
	
	/**
	 * Build admin port argument
	 * 
	 * @param configuration The configuration to retrieve the port value
	 * @return The argument
	 */
	public static Argument<CommandArgumentName> buildAdminPortArgument(Configuration configuration) {
		return buildIntegerArgument(ADMIN_PORT, configuration.getDomain().getAdminPort());
	}
	
	/**
	 * Build debug argument
	 * 
	 * @param configuration The configuration to retrieve the debug mode value
	 * @return The argument
	 */
	public static Argument<CommandArgumentName> buildDebugArgument(Configuration configuration) {
		return buildBooleanArgument(DEBUG, configuration.getGlassfish().isDebug());
	}

	/**
	 * Build domain directory argument
	 * 
	 * @param configuration The configuration to retrieve the domain directory value
	 * @return The argument
	 */
	public static Argument<CommandArgumentName> buildDomainDirectoryArgument(Configuration configuration) {
		return buildStringArgument(DOMAIN_DIRECTORY, configuration.getDomain().getDirectory().getAbsolutePath());
	}
	
	/**
	 * Build domain name argument
	 * 
	 * @param configuration The configuration to retrieve the domain name value
	 * @return The argument
	 */
	public static Argument<CommandArgumentName> buildDomainNameArgument(Configuration configuration) {
		return buildStringArgument(DOMAIN_NAME, configuration.getDomain().getName());
	}
	
	/**
	 * Build domain property argument
	 * 
	 * @param configuration The configuration to retrive the domain properties
	 * @return The argument
	 */
	public static Argument<CommandArgumentName> buildDomainPropertiesArgument(Configuration configuration) {
		// Populate the domain properties
		Set<Property> domainProperties = new HashSet<>();

		addPortAsProperty(domainProperties, "http.ssl.port", configuration.getDomain().getHttpsPort());
		addPortAsProperty(domainProperties, "orb.listener.port", configuration.getDomain().getIiopPort());
		addPortAsProperty(domainProperties, "jms.port", configuration.getDomain().getJmsPort());
		addPortAsProperty(domainProperties, "domain.jmxPort", configuration.getDomain().getJmxPort());
		addPortAsProperty(domainProperties, "orb.ssl.port", configuration.getDomain().getIiopsPort());
		addPortAsProperty(domainProperties, "orb.mutualauth.port", configuration.getDomain().getIiopsmPort());
		addPortAsProperty(domainProperties, "java.debugger.port", configuration.getDomain().getDebuggerPort());
		addPortAsProperty(domainProperties, "osgi.shell.telnet.port", configuration.getDomain().getOsgiShellTelnetPort());

		return create(DOMAIN_PROPERTIES).setPropertiesValue(domainProperties);
	}
	
	/**
	 * Build the instance port argument
	 * 
	 * @param configuration The configuration to retrieve the instance port value
	 * @return The argument
	 */
	public static Argument<CommandArgumentName> buildInstancePortArgument(Configuration configuration) {
		return buildIntegerArgument(INSTANCE_PORT, configuration.getDomain().getHttpPort());
	}
	
	/**
	 * Build the JVM options argument
	 * 
	 * @param jvmOptions The JVM options to set to the argument
	 * @return The argument
	 */
	public static Argument<CommandArgumentName> buildJvmOptionsArgument(Set<String> jvmOptions) {
		return create(JVM_OPTIONS).setStringsValue(jvmOptions);
	}
	
	/**
	 * Build the standard property argument without a name
	 * 
	 * @param properties The properties to set to the argument
	 * @return The argument
	 */
	public static Argument<CommandArgumentName> buildPropertyWithoutNameArgument(Set<Property> properties) {
		return buildPropertiesArgument(EMPTY_NAME_PROPERTY, properties);
	}

	/**
	 * Build the standard property argument
	 * 
	 * @param properties The properties to set to the argument
	 * @return The argument
	 */
	public static Argument<CommandArgumentName> buildPropertyArgument(Set<Property> properties) {
		return buildPropertiesArgument(PROPERTY, properties);
	}

	/**
	 * Build the standard set attribute
	 * 
	 * @param key The key
	 * @param value The value
	 * @return The argument
	 */
	public static Argument<CommandArgumentName> buildSetAttributeArgument(Property property) {
		Set<Property> props = new HashSet<>();
		props.add(property);
		return buildPropertiesArgument(SET_ATTRIBUTE, props);
	}
	
	/**
	 * Build the deployment property argument
	 * 
	 * @param properties The properties to set to the argument
	 * @return The argument
	 */
	public static Argument<CommandArgumentName> buildDeploymentPropertyArgument(Set<Property> properties) {
		return buildPropertiesArgument(DEP_PROPERTY, properties);
	}

	/**
	 * Create an argument with its name
	 * 
	 * @param name The name of the argument
	 * @return The argument
	 * @throws IllegalArgumentException If the name is null
	 */
	private static Argument<CommandArgumentName> create(CommandArgumentName name) {
		if (name == null) {
			throw new IllegalArgumentException("The name must not be null.");
		}
		
		return new Argument<>(name);
	}

	/**
	 * Add a port as a property. Ensure that port is > 0 and < 65536
	 * 
	 * @param properties The properties where to add the port
	 * @param name The name of the property port name
	 * @param port The port to add to the properties
	 */
	private static void addPortAsProperty(Set<Property> properties, String name, int port) {
		if (port > 0 && port < 65536) {
			properties.add(new Property(name, "" + port));
		}
	}
	
	/**
	 * Build a property argument
	 * 
	 * @param name The name of property argument
	 * @param properties The properties of the argument
	 * @return The argument
	 */
	private static Argument<CommandArgumentName> buildPropertiesArgument(CommandArgumentName name, Set<Property> properties) {
		if (properties != null && !properties.isEmpty()) {
			return create(name).setPropertiesValue(properties);
		}
		else {
			return null;
		}
	}
}
