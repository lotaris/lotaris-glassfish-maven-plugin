package com.lotaris.maven.plugin.glassfish.command;

/**
 * List of the available command of ASADMIN util
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public enum CommandName {
	/**
	 * Creation of a new domain
	 */
	CREATE_DOMAIN("create-domain"),

	/**
	 * Create a JDBC connection pool
	 */
	CREATE_JDBC_CONNECTION_POOL("create-jdbc-connection-pool"),
	
	/**
	 * Create the JMS host
	 */
	CREATE_JMS_HOST("create-jms-host"),
	
	/**
	 * Create JDBC resource
	 */
	CREATE_JDBC_RESOURCE("create-jdbc-resource"),
	
	/**
	 * Create a JMS physical destination like queues and topics
	 */
	CREATE_JMS_DESTINATION("create-jmsdest"),
	
	/**
	 * Creation of a new JMS resource like connection factories, queues and topics
	 */
	CREATE_JMS_RESOURCE("create-jms-resource"),

	/**
	 * Creation of a new JVM option
	 */
	CREATE_JVM_OPTIONS("create-jvm-options"),
	
	/**
	 * Delete an existing domain
	 */
	DELETE_DOMAIN("delete-domain"),
	
	/**
	 * Allow to delete a JMS host
	 */
	DELETE_JMS_HOST("delete-jms-host"), 
	
	/**
	 * Delete an existing JVM option
	 */
	DELETE_JVM_OPTIONS("delete-jvm-options"),
	
	/**
	 * Deploy command
	 */
	DEPLOY("deploy"),
	
	/**
	 * Redeploy command
	 */
	REDEPLOY("redeploy"),
	
	/**
	 * The set command
	 */
	SET("set"),
	
	/**
	 * Gives the possibility to modify the logging attributes
	 */
	SET_LOG_ATTRIBUTES("set-log-attributes"),
	
	/**
	 * Start an existing domain and not already started
	 */
	START_DOMAIN("start-domain"),
	
	/**
	 * Stop an existing domain and not already stopped
	 */
	STOP_DOMAIN("stop-domain"),
	
	/**
	 * Undeploy an application
	 */
	UNDEPLOY("undeploy"),
	
	/**
	 * Create a connector connection pool
	 */
	CREATE_CONNECTOR_CONNECTION_POOL("create-connector-connection-pool"),
	
	/**
	 * Create a connector resource
	 */
	CREATE_CONNECTOR_RESOURCE("create-connector-resource");
	
	/**
	 * Command name used in the command line
	 */
	private String cmdName;

	/**
	 * Constructor
	 * 
	 * @param cmdName The command name
	 */
	CommandName(String cmdName) {
		this.cmdName = cmdName;
	}

	/**
	 * @return Retrieve the command name
	 */
	public String getCmd() {
		return cmdName;
	}
}
