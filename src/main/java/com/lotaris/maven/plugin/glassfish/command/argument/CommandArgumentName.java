package com.lotaris.maven.plugin.glassfish.command.argument;

/**
 * The command argument names are the names allowed in the commands
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public enum CommandArgumentName implements IArgumentName {
	/**
	 * The glassfish administration port
	 */
	ADMIN_PORT("adminport"),
	
	/**
	 * Description text
	 */
	DESCRIPTION("description"),
	
	/**
	 * Glassfish debug mode
	 */
	DEBUG("debug"),

	/**
	 * Deploys the application using a Java EE standard deployment descriptor that 
	 * resides outside of the application archive. 
	 */
	DEP_ALTDD("altdd"),

	/**
	 * This option controls whether web session and SFSB states for which high availability 
	 * is enabled are first buffered and then replicated using a separate asynchronous thread.
	 */
	DEP_ASYNCHRONOUS_REPLICATION("asyncreplication"),

	/**
	 * This option controls whether high-availability is enabled for web sessions 
	 * and for stateful session bean (SFSB) checkpointing and potentially passivation. 
	 */
	DEP_AVAILABILITY_ENABLED("availabilityenabled"),

	/**
	 * If set to true, deletes all the connection pools and connector 
	 * resources associated with the resource adapter being undeployed. 
	 */
	DEP_CASCADE("cascade"),
	
	/**
	 * Valid only if the archive is a web module. It is ignored for other
	 * archive types; defaults to filename without extension.
	 */
	DEP_CONTEXT_ROOT("contextroot"),

	/**
	 * If specified as true, creates tables at deployment of an application with unmapped CMP beans. 
	 */
	DEP_CREATE_TABLES("createtables"),

	/**
	 * Specifies the name of the database vendor for which tables are created.
	 */
	DEP_DB_VENDOR_NAME("dbvendorname"),

	/**
	 * Specifies the deployment order of the application. 
	 */
	DEP_DEPLOYMENT_ORDER("deploymentorder"),

	/**
	 * Deploys the deployment plan, which is a JAR file that contains GlassFish Server descriptors.
	 */
	DEP_DEPLOYMENT_PLAN("deploymentplan"),

	/**
	 * If specified as true when the component is redeployed, the tables created by 
	 * the previous deployment are dropped before creating the new tables.
	 */
	DEP_DROP_AND_CREATE_TABLES("dropandcreatetables"),

	/**
	 * If set to true, drops the tables that the application created 
	 * by using CMP beans during deployment.
	 */
	DEP_DROP_TABLES("droptables"),
	
	/**
	 * Allows users to access the application.
	 */
	DEP_ENABLED("enabled"),

	/**
	 * The file to deploy
	 */
	DEP_FILE(null),
	
	/**
	 * If set to true, redeploys the component even if the specified component has 
	 * already been deployed or already exists.
	 */
	DEP_FORCE("force"),
	
	/**
	 * If set to true, static RMI-IIOP stubs are generated and put into the client.jar.
	 */
	DEP_GENERATE_RMI_STUBS("generatermistubs"),
	
	/**
	 * This option controls whether web sessions, SFSB instances, and persistently created 
	 * EJB timers are retained between redeployments.
	 */
	DEP_KEEP_STATE("keepstate"),
	
	/**
	 * A comma-separated list of library JAR files. Specify the library JAR files 
	 * by their relative or absolute paths.
	 */
	DEP_LIBRARIES("libraries"),

	/**
	 * This option controls whether the deployed application is available for load balancing. 
	 */
	DEP_LOAD_BALANCING_ENABLED("lenabled"),

	/**
	 * The name of deployable component
	 */
	DEP_NAME("name"),
	
	/**
	 * By default this option does not allow the JSP to be precompiled during deployment.
	 */
	DEP_PRE_COMPILE_JSP("precompilejsp"),
	
	/**
	 * Optional keyword-value pairs that specify additional properties for the deployment.
	 */
	DEP_PROPERTY("properties"),

	/**
	 * Retrieves the client stub JAR file from the server machine to the local directory.
	 */
	DEP_RETRIEVE("retrieve"),
	
	/**
	 * Deploys the application using a GlassFish Server runtime deployment 
	 * descriptor that resides outside of the application archive.
	 */
	DEP_RUNTIME_ATLDD("runtimealtdd"),

	/**
	 * The packaging archive type of the component that is being deployed.
	 */
	DEP_TYPE("type"),
	
	/**
	 * Guarantees unique table names for all the beans and results in a hash code added to the table names.
	 */
	DEP_UNIQUE_TABLE_NAMES("uniquetablenames"),

	/**
	 * Specifies whether the subcommand uploads the file to the DAS.
	 */
	DEP_UPLOAD("upload"),
	
	/**
	 * If set to true and the required verifier packages are installed from the Update 
	 * Tool, the syntax and semantics of the deployment descriptor is verified. 
	 */
	DEP_VERIFY("verify"),
	
	/**
	 * One or more virtual server IDs. Multiple IDs are separated by commas.
	 */
	DEP_VIRTUAL_SERVERS("virtualservers"),

	/**
	 * The directory of the domains
	 */
	DOMAIN_DIRECTORY("domaindir"),
	
	/**
	 * The domain name
	 */
	DOMAIN_NAME(null),
	
	/**
	 * Domain properties
	 */
	DOMAIN_PROPERTIES("domainproperties"),

	/**
	 * Allows to build an argument of property types without an argument name
	 */
	EMPTY_NAME_PROPERTY(null),
	
	/**
	 * Glassfish instance port (in general, HTTP port)
	 */
	INSTANCE_PORT("instanceport"),
	
	/**
	 * Define if a non JEE app can use the JDBC pool
	 */
	JDBC_ALLOW_NON_COMPONENT_CALLERS("allownoncomponentcallers"),
	
	/**
	 * Specifies whether a connection is associated with the thread to
	 * enable the thread to reuse the JDBC connection. 
	 */
	JDBC_ASSOCIATE_WITH_THREAD("associatewiththread"),
	
	/**
	 * Specifies the interval, in seconds, between successive attempts to
	 * create a connection in a JDBC connection pool.
	 */
	JDBC_CREATION_INTERVAL("creationretryinterval"),
	
	/**
	 * Specifies the maximum number of times that GlassFish Server retries
	 * to create a connection if the initial attempt fails in a JDBC pool.
	 */
	JDBC_CREATION_RETRY_ATTEMPTS("creationretryattempts"),
	
	/**
	 * Points the JDBC connection pool to associate to a JDBC resource
	 */
	JDBC_CONNECTION_POOL_ID("connectionpoolid"),
	
	/**
	 * Data source class name for the JDBC connection pool
	 */
	JDBC_DATA_SOURCE_CLASS_NAME("datasourceclassname"),
	
	/**
	 * The name of the vendor-supplied JDBC driver class for a JDBC connection.
	 */
	JDBC_DRIVER_CLASS_NAME("driverclassname"),
	
	/**
	 * To close all connection if one failure occurs for JDBC connection pool
	 */
	JDBC_FAIL_CONNECTION("failconnection"),
	
	/**
	 * Max idle timeout to keep a connection open in JDBC pool
	 */
	JDBC_IDLE_TIMEOUT("idletimeout"),
	
	/**
	 * SQL String to be executed once connection is established
	 */
	JDBC_INIT_SQL("initsql"),
	
	/**
	 * Is the JDBC connection required a validation
	 */
	JDBC_IS_CONNECTION_VALIDATION_REQUIRED("isconnectvalidatereq"),
	
	/**
	 * Is the JDBC connection isolation is guaranteed
	 */
	JDBC_ISOLATION_GUARANTEED("isisolationguaranteed"),
	
	/**
	 * The level of isolation for a JDBC connection
	 */
	JDBC_ISOLATION_LEVEL("isolationlevel"),
	
	/**
	 * Specifies whether a physical connection should be associated with 
	 * the logical connection only when the physical connection is used,
	 * and disassociated when the transaction is completed.
	 */
	JDBC_LAZY_CONNECTION_ASSOCIATION("lazyconnectionassociation"),
	
	/**
	 * Specifies whether a resource to a transaction is enlisted only when 
	 * a method actually uses the resource in a JDBC connection pool.
	 */
	JDBC_LAZY_CONNECTION_ENLISTMENT("lazyconnectionenlistment"),
	
	/**
	 * Specifies whether leaked connections are restored to the JDBC 
	 * connection pool after leak connection tracing is complete
	 */
	JDBC_LEAK_RECLAIM("leakreclaim"),

	/**
	 * Specifies the amount of time, in seconds, for which connection
   * leaks in a JDBC connection pool are to be traced.
	 */
	JDBC_LEAK_TIMEOUT("leaktimeout"),
	
	/**
	 * Specifies whether a connection that is selected from the 
	 * JDBC pool should be matched by the resource adaptor. 
	 */
	JDBC_MATCH_CONNECTIONS("matchconnections"),
	
	/**
	 * Specifies the maximum number of times that a JDBC 
	 * connection can be reused.
	 */
	JDBC_MAX_CONNECTION_USAGE_COUNT("maxconnectionusagecount"),
	
	/**
	 * The number of connection in a JDBC pool
	 */
	JDBC_MAX_POOL_SIZE("maxpoolsize"),
	
	/**
	 * Maximum time before timeout is sent for a connection in JDBC pool
	 */
	JDBC_MAX_WAIT("maxwait"),
	
	/**
	 * Determine the behavior of transaction for the connection of the JDBC pool
	 */
	JDBC_NON_TRANSACTIONAL_CONNECTIONS("nontransactionalconnections"),

	/**
	 * Specifies if the pool is pinged during JDBC pool creation or
	 * reconfiguration to identify and warn of any erroneous values for 
	 * its attributes.
	 */
	JDBC_PING("ping"),
	
	/**
	 * The number of connection to add/remove when timeouts occurs in JDBC pool
	 */
	JDBC_POOL_RESIZE("poolresize"),
	
	/**
	 * Specifies if connection pooling is enabled for the JDBC pool.
	 */
	JDBC_POOLING("pooling"),
	
	/**
	 * A list of one or more custom modules that provide custom 
	 * logging of database activities for a JDBC connection pool.
	 */
	JDBC_SQL_TRACE_LISTENERS("sqltracelisteners"),
	
	/**
	 * The number of SQL statements to be cached using the default 
	 * caching mechanism (Least Recently Used). 
	 */
	JDBC_STATEMENT_CACHE_SIZE("statementcachesize"),
	
	/**
	 * Specifies whether leaked statements are reclaimed after the statements 
	 * leak in JDBC connection
	 */
	JDBC_STATEMENT_LEAK_RECLAIM("statementleakreclaim"),
	
	/**
	 * Specifies the amount of time, in seconds, after which any statements 
	 * that have not been closed by an application are to be detected in a
	 * JDBC connection.
	 */
	JDBC_STATEMENT_LEAK_TIMEOUT("statementleaktimeout"),
	
	/**
	 * Specifies the length of time in seconds after which a query that 
	 * is not completed is terminated in JDBC connection.
	 */
	JDBC_STATEMENT_TIMEOUT("statementtimeout"),
	
	/**
	 * The number of connection established and maintained in a JDBC pool
	 */
	JDBC_STEADY_POOL_SIZE("steadypoolsize"),
	
	/**
	 * Specifies the time interval in seconds between successive request 
	 * to validate a connection at most once for a connection in a JDBC pool
	 */
	JDBC_VALIDATE_AT_MOST_ONCE_PERIOD("validateatmostonceperiod"),
	
	/**
	 * The name of the class that provides custom validation when 
	 * the value of validation method is custom-validation.
	 */
	JDBC_VALIDATION_CLASS_NAME("validationclassname"),
	
	/**
	 * The validation method to use to validate a JDBC connection
	 */
	JDBC_VALIDATION_METHOD("validationmethod"),
	
	/**
	 * The name of the validation table for the JDBC validation method by table
	 */
	JDBC_VALIDATION_TABLE("validationtable"),
	
	/**
	 * Specifies whether the pooling infrastructure provides 
	 * wrapped JDBC objects to applications. 
	 */
	JDBC_WRAP_JDBC_OBJECTS("wrapjdbcobjects"),
	
	/**
	 * Destination name of a JMS resource
	 */
	JMS_DESTINATION_NAME(null),

	/**
	 * Destination type of a JMS resource
	 */
	JMS_DESTINATION_TYPE("desttype"),
	
	/**
	 * The JMS MQ host to contact JMSService
	 */
	JMS_HOST_MQHOST("mqhost"),
	
	/**
	 * The JMS MQ password to contact JMSService
	 */
	JMS_HOST_MQPASSWORD("mqpassword"),
	
	/**
	 * The JMS MQ port to contact JMSService
	 */
	JMS_HOST_MQPORT("mqport"),

	/**
	 * The JMS MQ user to contact JMSService
	 */
	JMS_HOST_MQUSER("mquser"),
	
	/**
	 * The JMS Host name
	 */
	JMS_HOST_NAME(null),
	
	/**
	 * JNDI name of a resource (JDBC/JMS)
	 */
	JNDI_NAME(null),
	
	/**
	 * JVM Options
	 */
	JVM_OPTIONS(null),
	
	/**
	 * Properties
	 */
	PROPERTY("property"),
	
	/**
	 * Resource type for JMS/JDBC
	 */
	RESOURCE_TYPE("restype"),
	
	/**
	 * Argument for the set command
	 */
	SET_ATTRIBUTE(null);
	
	/**
	 * Argument name
	 */
	private String argName;

	/**
	 * Constructor
	 * 
	 * @param argName The argument name
	 */
	private CommandArgumentName(String argName) {
		this.argName = argName;
	}

	@Override
	public String getArgName() {
		return argName;
	}
	
	@Override
	public boolean isBoolean() {
		return false;
	}
	
	@Override
	public String getDefaultValue() {
		return null;
	}
}
