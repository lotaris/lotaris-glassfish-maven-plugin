package com.lotaris.maven.plugin.glassfish.model;

import java.io.File;
import java.util.Set;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * The glassfish configuration
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class Glassfish {
	/**
	 * The root directory of the Glassfish installation to be used
	 */
	@Parameter(required = true)
	private File home;
	
	/**
	 * The directory into which domains are deployed
	 */
	@Parameter(defaultValue = "${home}/domains")
	private File domainDirectory;
	
	/**
	 * Debug Glassfish output
	 */
	@Parameter(defaultValue = "false")
	private boolean debug = false;
	
	/**
	 * Force component deployment
	 */
	@Parameter(defaultValue = "false")
	private boolean force = false;
	
	/**
	 * Echo Glassfish asadmin commands
	 */
	@Parameter(defaultValue = "false")
	private boolean echo = false;
	
	/**
	 * Terse Glassfish output
	 */
	@Parameter(defaultValue = "true")
	private boolean terse = false;
	
	/**
	 * Automatically create the domain if it does not already exist
	 */
	@Parameter(defaultValue = "true")
	private boolean autoCreate = true;
	
	@Parameter(defaultValue = "false")
	private boolean deleteDefaultJmsHost = false;
	
	/**
	 * The asadmin user to create for domain administration.
	 */
	@Parameter
	private String user;
	
	/**
	 * Location of the asadmin style password file (if you do not want to provide the password in your POM)
	 */
	@Parameter(required = true)
	private String passwordFile;
	
	/**
	 * Define a list of defaults value for the configuration of JMS related
	 */
	@Parameter
	private Set<Property> jmsDefaults;
	
	/**
	 * Define a list of defaults value for the configuration of JDBC related
	 */
	@Parameter
	private Set<Property> jdbcDefaults;
	
	/**
	 * Define a set of hosts to create
	 */
	@Parameter
	private Set<JmsHost> jmsHosts;
		
	/**
	 * Represent information to configure the JMS service
	 */
	@Parameter
	private JmsService jmsService;
	
	public File getDomainDirectory() {
		return domainDirectory;
	}

	public void setDomainDirectory(File domainDirectory) {
		this.domainDirectory = domainDirectory;
	}

	public File getGlassfishDirectory() {
		return home;
	}

	public boolean isDebug() {
		return debug;
	}

	public boolean isForce() {
		return force;
	}

	public boolean isEcho() {
		return echo;
	}

	public boolean isTerse() {
		return terse;
	}

	public String getUser() {
		return user;
	}

	public String getPasswordFile() {
		return passwordFile;
	}

	public boolean isAutoCreate() {
		return autoCreate;
	}

	public boolean isDeleteDefaultJmsHost() {
		return deleteDefaultJmsHost;
	}
	
	public Set<Property> getJmsDefaults() {
		return jmsDefaults;
	}

	public Set<Property> getJdbcDefaults() {
		return jdbcDefaults;
	}

	public boolean hasJmsHosts() {
		return jmsHosts != null && !jmsHosts.isEmpty();
	}
	
	public Set<JmsHost> getJmsHosts() {
		return jmsHosts;
	}
	
	public JmsService getJmsService() {
		return jmsService;
	}
	
	@Override
	public String toString() {
		return 
			"autoCreate=" + autoCreate + ", " +
			"debug=" + debug + ", " +
			"deleteDefaultJmsHost=" + deleteDefaultJmsHost + ", " + 
			"domainDirectory=" + domainDirectory + ", " +
			"echo=" + echo + ", " +
			"force=" + force + ", " +
			"glassfishDirectory=" + home + ", " +
			"jdbcDefaults=" + jdbcDefaults + ", " +
			"jmsDefaults=" + jmsDefaults + ", " +
			"jmsHosts=" + jmsHosts + ", " +
			"jmsService=" + jmsService + ", " +
			"passwordFile=" + passwordFile + ", " +
			"terse=" + terse + ", " +
			"user=" + user;
	}
}
