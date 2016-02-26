package com.lotaris.maven.plugin.glassfish.command;

import com.lotaris.maven.plugin.glassfish.model.Configuration;
import com.lotaris.maven.plugin.glassfish.model.ConnectionFactory;
import com.lotaris.maven.plugin.glassfish.model.ConnectorConnectionPool;

import com.lotaris.maven.plugin.glassfish.model.JmsResource;
import com.lotaris.maven.plugin.glassfish.model.Property;
import java.util.HashSet;
import java.util.Set;

import static com.lotaris.maven.plugin.glassfish.command.CommandName.*;
import static com.lotaris.maven.plugin.glassfish.command.argument.CommandArgumentFactory.*;
import static com.lotaris.maven.plugin.glassfish.command.argument.CommandArgumentName.*;

import com.lotaris.maven.plugin.glassfish.model.DeployConfiguration;
import com.lotaris.maven.plugin.glassfish.model.JdbcResource;
import com.lotaris.maven.plugin.glassfish.model.JmsHost;
import com.lotaris.maven.plugin.glassfish.model.RedeployConfiguration;

/**
 * The command factory create the ASADMIN commands
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class CommandFactory {
	/**
	 * Build the create domain command
	 * 
	 * @param configuration The configuration to enrich the command
	 * @return The command
	 */
	public static CommandBuilder buildCreateDomainCommand(Configuration configuration) {
		return create(CREATE_DOMAIN, configuration).
			addArgument(buildAdminPortArgument(configuration)).
			addArgument(buildInstancePortArgument(configuration)).
			addArgument(buildDomainDirectoryArgument(configuration)).
			addArgument(buildDomainPropertiesArgument(configuration)).
			addArgument(buildDomainNameArgument(configuration)).
			setFriendlyErrorMessage("Unable to create the domain.");
	}
	
	/**
	 * Build the create JDBC connection pool command
	 * 
	 * @param configuration The configuration to enrich the command
	 * @param resource The resource to create the command specifically for it
	 * @return The command
	 */
	public static CommandBuilder buildCreatJdbcConnectionPool(Configuration configuration, JdbcResource resource) {
		// Check if the description exists or create one default
		String description = resource.getPoolDescription();
		if (description == null) {
			description = "Connection pool for " + resource.getJndiName().replace("jdbc/", "");
		}
		
		// Retrieve the properties or create an empty one
		Set<Property> properties = resource.getProperties();
		if (properties == null) {
			properties = new HashSet<>();
		}
		
		// Set the user in the properties
		if (resource.getUser() != null) {
			properties.add(new Property("User", resource.getUser()));
		}
		
		// Set the password in the properties
		if (resource.getPassword() != null) {
			properties.add(new Property("Password", resource.getPassword()));
		}
		
		// Set the url in the properties
		if (resource.getUrl() != null) {
			properties.add(new Property("Url", resource.getUrl()));
		}
		
		return create(CREATE_JDBC_CONNECTION_POOL, configuration).
			addArgument(buildStringArgument(JDBC_DATA_SOURCE_CLASS_NAME, resource.getDataSourceClassName())).
			addArgument(buildStringArgument(RESOURCE_TYPE, resource.getType())).
			addArgument(buildIntegerArgument(JDBC_STEADY_POOL_SIZE, resource.getSteadyPoolSize())).
			addArgument(buildIntegerArgument(JDBC_MAX_POOL_SIZE, resource.getMaxPoolSize())).
			addArgument(buildIntegerArgument(JDBC_MAX_WAIT, resource.getMaxWait())).
			addArgument(buildIntegerArgument(JDBC_POOL_RESIZE, resource.getPoolResize())).
			addArgument(buildIntegerArgument(JDBC_IDLE_TIMEOUT, resource.getIdleTimeout())).
			addArgument(buildStringArgument(JDBC_INIT_SQL, resource.getInitSql())).
			addArgument(buildStringArgument(JDBC_ISOLATION_LEVEL, resource.getIsolationLevel())).
			addArgument(buildBooleanArgument(JDBC_ISOLATION_GUARANTEED, resource.getIsolationGuaranteed())).
			addArgument(buildBooleanArgument(JDBC_IS_CONNECTION_VALIDATION_REQUIRED, resource.getIsConnectValidateReq())).
			addArgument(buildStringArgument(JDBC_VALIDATION_METHOD, resource.getValidationMethod())).
			addArgument(buildStringArgument(JDBC_VALIDATION_TABLE, resource.getValidationTable())).
			addArgument(buildBooleanArgument(JDBC_FAIL_CONNECTION, resource.getFailConnection())).
			addArgument(buildBooleanArgument(JDBC_ALLOW_NON_COMPONENT_CALLERS, resource.getAllowNonComponentCallers())).
			addArgument(buildBooleanArgument(JDBC_NON_TRANSACTIONAL_CONNECTIONS, resource.getNonTransactionalConnections())).
			addArgument(buildIntegerArgument(JDBC_VALIDATE_AT_MOST_ONCE_PERIOD, resource.getValidateAtMostOncePeriod())).
			addArgument(buildIntegerArgument(JDBC_LEAK_TIMEOUT, resource.getLeakTimeout())).
			addArgument(buildBooleanArgument(JDBC_LEAK_RECLAIM, resource.getLeakReclaim())).
			addArgument(buildIntegerArgument(JDBC_STATEMENT_LEAK_TIMEOUT, resource.getStatementLeakTimeout())).
			addArgument(buildBooleanArgument(JDBC_STATEMENT_LEAK_RECLAIM, resource.getStatementLeakReclaim())).
			addArgument(buildIntegerArgument(JDBC_CREATION_RETRY_ATTEMPTS, resource.getCreationRetryAttempts())).
			addArgument(buildIntegerArgument(JDBC_CREATION_INTERVAL, resource.getCreationRetryInterval())).
			addArgument(buildStringArgument(JDBC_SQL_TRACE_LISTENERS, resource.getSqlTraceListeners())).
			addArgument(buildIntegerArgument(JDBC_STATEMENT_TIMEOUT, resource.getStatementTimeout())).
			addArgument(buildBooleanArgument(JDBC_LAZY_CONNECTION_ENLISTMENT, resource.getLazyConnectionEnlistment())).
			addArgument(buildBooleanArgument(JDBC_LAZY_CONNECTION_ASSOCIATION, resource.getLazyConnectionAssociation())).
			addArgument(buildBooleanArgument(JDBC_ASSOCIATE_WITH_THREAD, resource.getAssociateWithThread())).
			addArgument(buildStringArgument(JDBC_DRIVER_CLASS_NAME, resource.getDriverClassName())).
			addArgument(buildBooleanArgument(JDBC_MATCH_CONNECTIONS, resource.getMatchConnections())).
			addArgument(buildIntegerArgument(JDBC_MAX_CONNECTION_USAGE_COUNT, resource.getMaxConnectionUsageCount())).
			addArgument(buildBooleanArgument(JDBC_PING, resource.getPing())).
			addArgument(buildBooleanArgument(JDBC_POOLING, resource.getPooling())).
			addArgument(buildIntegerArgument(JDBC_STATEMENT_CACHE_SIZE, resource.getStatementCacheSize())).
			addArgument(buildStringArgument(JDBC_VALIDATION_CLASS_NAME, resource.getValidationClassName())).
			addArgument(buildBooleanArgument(JDBC_WRAP_JDBC_OBJECTS, resource.getWrapJdbcObjects())).
			addArgument(buildStringArgument(DESCRIPTION, description)).
			addArgument(buildPropertyArgument(properties)).
			addArgument(buildStringArgument(JNDI_NAME, resource.getPoolId())).
			setFriendlyErrorMessage("Unable to create the JDBC connection pool [" + resource.getJndiName() + "].");
	}
	
	/**
	 * Build the create JDBC resource command
	 * 
	 * @param configuration The configuration to enrich the command
	 * @param resource The resource to create the command specifically for it
	 * @return The command
	 */
	public static CommandBuilder buildCreateJdbcResource(Configuration configuration, JdbcResource resource) {
		// Check if there is a description or create a default one
		String description = resource.getDescription();
		if (description == null) {
			description = "JDBC resource for " + resource.getJndiName().replace("jdbc/", "");
		}

		return create(CREATE_JDBC_RESOURCE, configuration).
			addArgument(buildStringArgument(JDBC_CONNECTION_POOL_ID, resource.getPoolId())).
			addArgument(buildStringArgument(DESCRIPTION, description)).
			addArgument(buildPropertyArgument(resource.getPoolProperties())).
			addArgument(buildStringArgument(JNDI_NAME, resource.getJndiName())).
			setFriendlyErrorMessage("Unable to create the JDBC resource [" + resource.getJndiName() + "].");
	}
	
	/**
	 * Build the command to set the connection pool to the Glassfish JDBC timer resource (jdbc/__TimerPool) with the given JDBC connection pool.
	 * 
	 * @param configuration The configuration to enrich the command
	 * @param resource The resource to create the command specifically for it
	 * @return The command
	 */
	public static CommandBuilder buildSetTimerResourceConnectionPool(Configuration configuration, JdbcResource resource) {
		Property property = new Property("domain.resources.jdbc-resource.jdbc/__TimerPool.pool-name", resource.getPoolId());
		return buildSetCommand(configuration, property);
	}

	/**
	 * Create the JMS Host creation command
	 * 
	 * @param configuration The configuration to enrich the command
	 * @param jmsHost The JMS Host informations
	 * @return The command
	 */
	public static CommandBuilder buildCreateJmsHostCommand(Configuration configuration, JmsHost jmsHost) {
		return create(CREATE_JMS_HOST, configuration).
			addArgument(buildStringArgument(JMS_HOST_MQHOST, jmsHost.getMqHost())).
			addArgument(buildIntegerArgument(JMS_HOST_MQPORT, jmsHost.getMqPort())).
			addArgument(buildStringArgument(JMS_HOST_MQUSER, jmsHost.getMqUser())).
			addArgument(buildStringArgument(JMS_HOST_MQPASSWORD, jmsHost.getMqPassword())).
			addArgument(buildStringArgument(JMS_HOST_NAME, jmsHost.getName())).
			setFriendlyErrorMessage("Unable to create the JMS Host [" + jmsHost.getName() + "]");
	}
	
	/**
	 * Build the create JMS destination command
	 * 
	 * @param configuration The configuration to enrich the command
	 * @param resource The resource to create the command specifically for it
	 * @return The command
	 */
	public static CommandBuilder buildCreateJmsDestination(Configuration configuration, JmsResource resource) {
		// Check if a name is set or create a default one
		String name = resource.getName();
		if (name == null) {
			name = resource.getJndiName().replace("jms/", "");
		}
		
		return create(CREATE_JMS_DESTINATION, configuration).
			addArgument(buildStringArgument(JMS_DESTINATION_TYPE, resource.getType().name().toLowerCase())).
			addArgument(buildStringArgument(JMS_DESTINATION_NAME, name)).
			setFriendlyErrorMessage("Unable to create physical destionation [" + name + "].");
	}
	
	/**
	 * Build the create JMS Connection Factory command
	 * 
	 * @param configuration The configuration to enrich the command
	 * @param connectionFactory The connection factory to create the command specifically for it
	 * @return The command
	 */
	public static CommandBuilder buildCreateJmsResourceCommand(Configuration configuration, ConnectionFactory connectionFactory) {
		String jndiName = connectionFactory.getJndiName();
		
		// Check if there is description or create a default one
		String description = connectionFactory.getDescription();
		if (description == null) {
			description = jndiName.replace("jms/", "");
		}

		// Retrieve the properties or create an empty set
		Set<Property> properties = connectionFactory.getProperties();
		if (properties == null) {
			properties = new HashSet<>();
		}
		
		// Set the user if none is provided
		if (connectionFactory.getUser() != null) {
			properties.add(new Property("UserName", connectionFactory.getUser()));
		}
		
		// Set the password if none is provided
		if (connectionFactory.getPassword() != null) {
			properties.add(new Property("Password", connectionFactory.getPassword()));
		}
		
		// Set the address list if none is provided
		if (connectionFactory.getAddressList() != null) {
			properties.add(new Property("AddressList", connectionFactory.getAddressList()));
		}

		return create(CREATE_JMS_RESOURCE, configuration).
			addArgument(buildStringArgument(RESOURCE_TYPE, connectionFactory.getType().getClazz())).
			addArgument(buildStringArgument(DESCRIPTION, description)).
			addArgument(buildPropertyArgument(properties)).
			addArgument(buildStringArgument(JNDI_NAME, jndiName)).
			setFriendlyErrorMessage("Unable to create the JMS resource [" + connectionFactory.getJndiName() + "].");
	}
	
	/**
	 * Build the JMS Resource command
	 * 
	 * @param configuration The configuration to enrich the command
	 * @param resource The resource to create the command specifically for it
	 * @return The command
	 */
	public static CommandBuilder buildCreateJmsResourceCommand(Configuration configuration, JmsResource resource) {
		String jndiName = resource.getJndiName();

		// Check if a name is present or create a default one
		String name = resource.getName();
		if (name == null) {
			name = jndiName.replace("jms/", "");
		}

		// Check if there is a description or create a default one
		String description = resource.getDescription();
		if (description == null) {
			description = name;
		}
		
		// Retrieve the properties or create an empty set
		Set<Property> properties = resource.getProperties();
		if (properties == null) {
			properties = new HashSet<>();
		}

		// Set the name as a property
		properties.add(new Property("Name", name));
		
		return create(CREATE_JMS_RESOURCE, configuration).
			addArgument(buildStringArgument(RESOURCE_TYPE, resource.getType().getClazz())).
			addArgument(buildStringArgument(DESCRIPTION, description)).
			addArgument(buildPropertyArgument(properties)).
			addArgument(buildStringArgument(JNDI_NAME, jndiName)).
			setFriendlyErrorMessage("Unable to create the JMS resource [" + resource.getJndiName() + "].");
		
	}

	/**
	 * Build create JVM options command
	 * 
	 * @param configuration The configuration to get the JVM options to create
	 * @return The command
	 */
	public static CommandBuilder buildCreateJvmOptionsCommand(Configuration configuration) {
		return create(CREATE_JVM_OPTIONS, configuration).
			addArgument(buildJvmOptionsArgument(configuration.getDomain().getCreateJvmOptions())).
			setFriendlyErrorMessage("Unable to create JVM options.");
	}
	
	/**
	 * Build the delete JMS host for the default JMS host
	 * 
	 * @param configuration The configuration to get the options
	 * @return The command
	 */
	public static CommandBuilder buildDeleteDefaultJmsHost(Configuration configuration) {
		return create(DELETE_JMS_HOST, configuration).
			addArgument(buildStringArgument(JMS_HOST_NAME, "default_JMS_host")).
			setFriendlyErrorMessage("Unable to delete the default JMS host [default_JMS_host]");
	}
	
	/**
	 * Build the delete domain command
	 * 
	 * @param configuration The configuration to enrich the command
	 * @return The command
	 */
	public static CommandBuilder buildDeleteDomainCommand(Configuration configuration) {
		return create(DELETE_DOMAIN, configuration).
			addArgument(buildDomainDirectoryArgument(configuration)).
			addArgument(buildDomainNameArgument(configuration)).
			setFriendlyErrorMessage("Unable to delete the domain.");
	}
	
	/**
	 * Build the delete JVM Options command
	 * 
	 * @param configuration The configuration to get the JVM options to delete
	 * @return The command
	 */
	public static CommandBuilder buildDeleteJvmOptionsCommand(Configuration configuration) {
		return create(DELETE_JVM_OPTIONS, configuration).
			addArgument(buildJvmOptionsArgument(configuration.getDomain().getDeleteJvmOption())).
			setFriendlyErrorMessage("Unable to delete JVM options.");
	}
	
	/**
	 * Build the deploy command with a custom deploy configuration
	 * 
	 * @param configuration The configuration to get the options for the deployment
	 * @param deployConfig The deployment configuration
	 * @return The command
	 */
	public static CommandBuilder buildDeployCommand(Configuration configuration, DeployConfiguration deployConfig) {

		return create(DEPLOY, configuration).
			addArgument(buildBooleanArgument(DEP_FORCE, deployConfig.getForce())).
			addArgument(buildStringArgument(DEP_VIRTUAL_SERVERS, deployConfig.getVirtualServers())).
			addArgument(buildStringArgument(DEP_CONTEXT_ROOT, deployConfig.getContextRoot())).
			addArgument(buildBooleanArgument(DEP_PRE_COMPILE_JSP, deployConfig.getPreCompileJsp())).
			addArgument(buildBooleanArgument(DEP_VERIFY, deployConfig.getVerify())).
			addArgument(buildStringArgument(DEP_NAME, deployConfig.getName())).
			addArgument(buildBooleanArgument(DEP_UPLOAD, deployConfig.getUpload())).
			addArgument(buildStringArgument(DEP_RETRIEVE, deployConfig.getRetrieve())).
			addArgument(buildStringArgument(DEP_DB_VENDOR_NAME, deployConfig.getDbVendorName())).
			addArgument(buildBooleanArgument(DEP_CREATE_TABLES, deployConfig.getCreateTables())).
			addArgument(buildBooleanArgument(DEP_DROP_AND_CREATE_TABLES, deployConfig.getDropAndCreateTables())).
			addArgument(buildBooleanArgument(DEP_UNIQUE_TABLE_NAMES, deployConfig.getUniqueTableNames())).
			addArgument(buildStringArgument(DEP_DEPLOYMENT_PLAN, deployConfig.getDeploymentPlan())).
			addArgument(buildStringArgument(DEP_ALTDD, deployConfig.getAltdd())).
			addArgument(buildStringArgument(DEP_RUNTIME_ATLDD, deployConfig.getRuntimeAltdd())).
			addArgument(buildIntegerArgument(DEP_DEPLOYMENT_ORDER, deployConfig.getDeploymentOrder())).
			addArgument(buildBooleanArgument(DEP_ENABLED, deployConfig.getEnabled())).
			addArgument(buildBooleanArgument(DEP_GENERATE_RMI_STUBS, deployConfig.getGenerateRmiStubs())).
			addArgument(buildBooleanArgument(DEP_AVAILABILITY_ENABLED, deployConfig.getAvailabilityEnabled())).
			addArgument(buildBooleanArgument(DEP_ASYNCHRONOUS_REPLICATION, deployConfig.getAsynReplication())).
			addArgument(buildBooleanArgument(DEP_LOAD_BALANCING_ENABLED, deployConfig.getLenabled())).
			addArgument(buildBooleanArgument(DEP_KEEP_STATE, deployConfig.getKeepState())).
			addArgument(buildStringArgument(DEP_LIBRARIES, deployConfig.getLibraries())).
			addArgument(buildStringArgument(DEP_TYPE, deployConfig.getType())).
			addArgument(buildDeploymentPropertyArgument(deployConfig.getProperties())).
			addArgument(buildStringArgument(DEP_FILE, deployConfig.getFile())).
			setFriendlyErrorMessage("Unable to deploy the component.");		
	}
	
	/**
	 * Build the deploy command
	 * 
	 * @param configuration The configuration to get the options for the deployment
	 * @return The command
	 */
	public static CommandBuilder buildDeployCommand(Configuration configuration) {
		return buildDeployCommand(configuration, configuration.getDeployConfiguration());
	}
	
	/**
	 * Build the deploy command
	 * 
	 * @param configuration The configuration to get the options for the deployment
	 * @return The command
	 */
	public static CommandBuilder buildRedeployCommand(Configuration configuration) {
		RedeployConfiguration redepConfig = configuration.getRedeployConfiguration();
		
		return create(DEPLOY, configuration).
			addArgument(buildStringArgument(DEP_NAME, redepConfig.getName())).
			addArgument(buildBooleanArgument(DEP_UPLOAD, redepConfig.getUpload())).
			addArgument(buildStringArgument(DEP_RETRIEVE, redepConfig.getRetrieve())).
			addArgument(buildStringArgument(DEP_DB_VENDOR_NAME, redepConfig.getDbVendorName())).
			addArgument(buildBooleanArgument(DEP_CREATE_TABLES, redepConfig.getCreateTables())).
			addArgument(buildBooleanArgument(DEP_DROP_AND_CREATE_TABLES, redepConfig.getDropAndCreateTables())).
			addArgument(buildBooleanArgument(DEP_UNIQUE_TABLE_NAMES, redepConfig.getUniqueTableNames())).
			addArgument(buildStringArgument(DEP_DEPLOYMENT_PLAN, redepConfig.getDeploymentPlan())).
			addArgument(buildStringArgument(DEP_ALTDD, redepConfig.getAltdd())).
			addArgument(buildStringArgument(DEP_RUNTIME_ATLDD, redepConfig.getRuntimeAltdd())).
			addArgument(buildIntegerArgument(DEP_DEPLOYMENT_ORDER, redepConfig.getDeploymentOrder())).
			addArgument(buildBooleanArgument(DEP_ENABLED, redepConfig.getEnabled())).
			addArgument(buildBooleanArgument(DEP_GENERATE_RMI_STUBS, redepConfig.getGenerateRmiStubs())).
			addArgument(buildStringArgument(DEP_CONTEXT_ROOT, redepConfig.getContextRoot())).
			addArgument(buildBooleanArgument(DEP_PRE_COMPILE_JSP, redepConfig.getPreCompileJsp())).
			addArgument(buildBooleanArgument(DEP_VERIFY, redepConfig.getVerify())).
			addArgument(buildStringArgument(DEP_VIRTUAL_SERVERS, redepConfig.getVirtualServers())).
			addArgument(buildBooleanArgument(DEP_AVAILABILITY_ENABLED, redepConfig.getAvailabilityEnabled())).
			addArgument(buildBooleanArgument(DEP_ASYNCHRONOUS_REPLICATION, redepConfig.getAsynReplication())).
			addArgument(buildBooleanArgument(DEP_LOAD_BALANCING_ENABLED, redepConfig.getLenabled())).
			addArgument(buildBooleanArgument(DEP_KEEP_STATE, redepConfig.getKeepState())).
			addArgument(buildStringArgument(DEP_LIBRARIES, redepConfig.getLibraries())).
			addArgument(buildStringArgument(DEP_TYPE, redepConfig.getType())).
			addArgument(buildDeploymentPropertyArgument(redepConfig.getProperties())).
			addArgument(buildStringArgument(DEP_FILE, redepConfig.getFile())).
			setFriendlyErrorMessage("Unable to re-deploy the component.");
	}
	
	/**
	 * Build the set command
	 * 
	 * @param configuration The configuration to enrich the command
	 * @param property The property
	 * @return The command
	 */
	public static CommandBuilder buildSetCommand(Configuration configuration, Property property) {
		return create(SET, configuration).
			addArgument(buildSetAttributeArgument(property)).
			setFriendlyErrorMessage("Unable to set the value [" + property.getValue() + "] for name [" + property.getName() + "]");
	}
	
	/**
	 * Build the set command dedicated to set JMS Service attributes
	 * 
	 * @param configuration The configuration to enrich the command
	 * @param property The property
	 * @return The command
	 */
	public static CommandBuilder buildSetJmsServiceAttributeCommand(Configuration configuration, Property property) {
		if (!property.getName().startsWith("server.jms-service.")) {
			property.setName("server.jms-service." + property.getName());
		}
		
		return buildSetCommand(configuration, property);
	}
	
	/**
	 * Build the set command dedicated to set JMS Host attributes
	 * 
	 * @param configuration The configuration to enrich the command
	 * @param jmsHost The JMS host
	 * @param property The property
	 * @return The command
	 */
	public static CommandBuilder buildSetJmsHostAttributeCommand(Configuration configuration, JmsHost jmsHost, Property property) {
		if (!property.getName().startsWith("server.jms-service.jms-host." + jmsHost.getName() + ".")) {
			property.setName("server.jms-service.jms-host." + jmsHost.getName() + "." + property.getName());
		}
	
		return buildSetCommand(configuration, property);
	}
	
	/**
	 * Build the set logging attributes command
	 * 
	 * @param configuration The configuration to enrich the command
	 * @return The command
	 */
	public static CommandBuilder buildSetLoggingAttributesCommand(Configuration configuration) {
		if (configuration.getDomain().getLoggingAttributes() != null && !configuration.getDomain().getLoggingAttributes().isEmpty()) {
			return create(SET_LOG_ATTRIBUTES, configuration).
				addArgument(buildPropertyWithoutNameArgument(configuration.getDomain().getLoggingAttributes())).
				setFriendlyErrorMessage("Unable to set one or more logging attributes to the domain.");
		}
		else {
			return null;
		}
	}
		
	/**
	 * Build the start domain command
	 * 
	 * @param configuration The configuration to enrich the command
	 * @return The command
	 */
	public static CommandBuilder buildStartDomainCommand(Configuration configuration) {
		return create(START_DOMAIN, configuration).
			addArgument(buildDebugArgument(configuration)).
			addArgument(buildDomainDirectoryArgument(configuration)).
			addArgument(buildDomainNameArgument(configuration)).
			setFriendlyErrorMessage("Unable to start the domain.");
	}
	
	/**
	 * Build the stop domain command
	 * 
	 * @param configuration The configuration to enrich the command
	 * @return The command
	 */
	public static CommandBuilder buildStopDomainCommand(Configuration configuration) {
		return create(STOP_DOMAIN, configuration).
			addArgument(buildDomainDirectoryArgument(configuration)).
			addArgument(buildDomainNameArgument(configuration)).
			setFriendlyErrorMessage("Unable to stop the domain.");
	}

	/**
	 * Build the un-deploy command
	 * 
	 * @param configuration The configuration to enrich the command
	 * @return The command
	 */
	public static CommandBuilder buildUndeployCommand(Configuration configuration) {
		return create(UNDEPLOY, configuration).
			addArgument(buildBooleanArgument(DEP_DROP_TABLES, configuration.getUndeployConfiguration().getDropTables())).
			addArgument(buildBooleanArgument(DEP_CASCADE, configuration.getUndeployConfiguration().getCascade())).
			addArgument(buildStringArgument(DEP_FILE, configuration.getUndeployConfiguration().getName())).
			setFriendlyErrorMessage("Unable to undeploy the component.");
	}
	
	/**
	 * Build a create-connector-connection-pool command
	 * 
	 * @param configuration The configuration to enrich the command
	 * @param connectorConnectionPool The connector connection pool
	 * @return The command
	 */
	public static CommandBuilder buildCreateConnectorConnectionPoolCommand(Configuration configuration, ConnectorConnectionPool connectorConnectionPool) {
		return create(CREATE_CONNECTOR_CONNECTION_POOL, configuration).
			addArgument(buildStringArgument(RANAME, connectorConnectionPool.getRaname())).
			addArgument(buildStringArgument(CONNECTION_DEFINITION, connectorConnectionPool.getConnectionDefinition().getClazz())).
			addArgument(buildBooleanArgument(PING, connectorConnectionPool.getPing())).
			addArgument(buildBooleanArgument(IS_CONNECT_VALIDATE_REQ, connectorConnectionPool.getIsConnectValidateReq())).
			addArgument(buildPropertyArgument(connectorConnectionPool.getProperties())).
			addArgument(buildStringArgument(JNDI_NAME, connectorConnectionPool.getJndiName())).
			setFriendlyErrorMessage("Unable to create the connector connection pool.");		
	}
	
	/**
	 * Helper to build the main part of the command that is always standard through
	 * every command.
	 * 
	 * @param name The command name for which the command is created
	 * @param configuration The configuration to get the different values to enrich the command
	 * @return The command to be enriched with the real command arguments
	 */
	private static CommandBuilder create(CommandName name, Configuration configuration) {
		return new CommandBuilder(name).
			setupAsadminArguments(configuration);
	}
}
