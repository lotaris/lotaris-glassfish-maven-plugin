package com.lotaris.maven.plugin.glassfish.command.argument;

import com.lotaris.maven.plugin.glassfish.model.Configuration;

/**
 * The ASADMIN argument names are the names allowed in the util arguments.
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public enum AsadminArgumentName implements IArgumentName {
	/**
	 * Echo argument name to show more info from ASADMIN command line
	 */
	ECHO("echo", true),

	/**
	 * Host to contact
	 */
	HOST("host") {
		@Override
		public Argument create(Configuration configuration) {
			return create().setValue(configuration.getDomain().getHost());
		}
	},
	
	/**
	 * Enable/disable the interactive mode
	 */
	INTERACTIVE("interactive", "false", true),
	
	/**
	 * Location of the password file
	 */
	PASSWORDFILE("passwordfile") {
		@Override
		public Argument create(Configuration configuration) {
			return super.create().setValue(configuration.getGlassfish().getPasswordFile());
		}
	},

	/**
	 * The port to contact
	 */
	PORT("port") {
		@Override
		public Argument create(Configuration configuration) {
			return create().setValue(configuration.getDomain().getAdminPort());
		}
	},
	
	/**
	 * To augment the verbosity of the ASADMIN logs
	 */
	TERSE("terse", true) {
		@Override
		public Argument create(Configuration configuration) {
			return super.create().setValue(configuration.getGlassfish().isTerse());
		}
	},
	
	/**
	 * The user
	 */
	USER("user") {
		@Override
		public Argument create(Configuration configuration) {
			return super.create().setValue(configuration.getGlassfish().getUser());
		}
	};
	
	/**
	 * Define if the argument is a boolean (yes/no, true/false)
	 */
	private boolean isBoolean = false;
	
	/**
	 * The argument name
	 */
	private String argName;
	
	/**
	 * Define if the argument has a default value
	 */
	private String defaultValue = null;

	/**
	 * Constructor
	 * 
	 * @param argName The argument name
	 */
	private AsadminArgumentName(String argName) {
		this.argName = argName;
	}
	
	/**
	 * Constructor
	 * 
	 * @param argName The argument name
	 * @param defaultValue The default value
	 */
	private AsadminArgumentName(String argName, String  defaultValue) {
		this(argName);
		this.defaultValue = defaultValue;
	}
	
	/**
	 * Constructor
	 * 
	 * @param argName The argument name
	 * @param defaultValue The default value
	 * @param isBoolean Enable/Disable the boolean mode of the argument
	 */
	private AsadminArgumentName(String argName, String defaultValue, boolean isBoolean) {
		this(argName, defaultValue);
		this.isBoolean = isBoolean;
	}
	
	/**
	 * Constructor
	 * 
	 * @param argName The argument name
	 * @param isBoolean Enable/Disable the boolean mode of the argument
	 */
	private AsadminArgumentName(String argName, boolean isBoolean) {
		this(argName, null, isBoolean);
	}

	@Override
	public String getArgName() {
		return argName;
	}
	
	@Override
	public boolean isBoolean() {
		return isBoolean;
	}
	
	@Override
	public String getDefaultValue() {
		return defaultValue;
	}
	
	/**
	 * @return Empty argument just set with its name
	 */
	public Argument<AsadminArgumentName> create() {
		return new Argument<>(this);
	}
	
	/**
	 * @param configuration The configuration to enrich the argument
	 * @return Empty argument just set with its name
	 */
	public Argument<AsadminArgumentName> create(Configuration configuration) {
		return create();
	}
}
