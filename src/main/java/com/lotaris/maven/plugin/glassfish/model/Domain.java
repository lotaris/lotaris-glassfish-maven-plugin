package com.lotaris.maven.plugin.glassfish.model;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Set;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * The domain configuration
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class Domain {
	/**
	 * The target host
	 */
	@Parameter
	private String host;
	
	/**
	 * The directory in which this domain should be created (if other than the Glassfish default). Overrides the value of domainDirectory in global configuration.
	 * This value is ignored if the host is other than localhost.
	 */
	@Parameter(defaultValue = "${glassfish.home}/domains")
	private File directory;
	
	/**
	 * The name of the domain (will be used as directory name)
	 */
	@Parameter(required = true)
	private String name;

	/**
	 * The administration port
	 */
	@Parameter(required = true)
	private int adminPort;
	
	/**
	 * The HTTP port (instance port)
	 */
	@Parameter
	private int httpPort;
	
	/**
	 * The HTTPS port
	 */
	@Parameter
	private int httpsPort;
	
	/**
	 * The IIOP port
	 */
	@Parameter
	private int iiopPort;
	
	/**
	 * The IIOP SSL port
	 */
	@Parameter
	private int iiopsPort;
	
	/**
	 * The IIOP SSL MutualAuth port
	 */
	@Parameter
	private int iiopsmPort;
	
	/**
	 * The JMS port
	 */
	@Parameter
	private int jmsPort;
	
	/**
	 * The JMX port
	 */
	@Parameter
	private int jmxPort;
	
	/**
	 * The Debugger port
	 */
	@Parameter
	private int debuggerPort;
	
	/**
	 * The OSGI Shell Telnet port
	 */
	@Parameter
	private int osgiShellTelnetPort;
	
	/**
	 * Decide if a domain can be reused or not
	 */
	@Parameter(defaultValue = "false")
	private boolean reuse;
	
	/**
	 * A list of JVM options to create
	 */
	@Parameter
	private Set<String> createJvmOptions;
	
	/**
	 * A list of JVM options to delete
	 */
	@Parameter
	private Set<String> deleteJvmOption;
	
	/**
	 * Define the properties to set for the logging configuration
	 */
	@Parameter
	private Set<Property> loggingAttributes;
	
	/**
	 * Additional properties to configure for the domain
	 */
	@Parameter
	private Set<Property> properties;
	
	/**
	 * A list of connection factories to create
	 */
	@Parameter
	private Set<ConnectionFactory> connectionFactories;
	
	/**
	 * Define a set of connector connection pool to create
	 */
	@Parameter
	private Set<ConnectorConnectionPool> connectorConnectionPools;
	
	/**
	 * Define a set of connector resource to create
	 */
	@Parameter
	private Set<ConnectorResource> connectorResources;
	
	/**
	 * A list of JMS resources to create (resources + physical destinations)
	 */
	@Parameter
	private Set<JmsResource> jmsResources;

	/**
	 * A list of JDBC resources to create (resources + pools)
	 */
	@Parameter
	private Set<JdbcResource> jdbcResources;
	
	/**
	 * A list of Resource Adapter to deploy on the domain
	 */
	@Parameter
	private Set<ResourceAdapter> resourceAdapters;
	
	public String getName() {
		return name;
	}

	public int getAdminPort() {
		return adminPort;
	}

	public int getHttpPort() {
		return httpPort;
	}

	public int getHttpsPort() {
		return httpsPort;
	}

	public int getIiopPort() {
		return iiopPort;
	}

	public int getIiopsPort() {
		return iiopsPort;
	}

	public int getIiopsmPort() {
		return iiopsmPort;
	}

	public int getJmsPort() {
		return jmsPort;
	}

	public int getJmxPort() {
		return jmxPort;
	}

	public int getDebuggerPort() {
		return debuggerPort;
	}

	public int getOsgiShellTelnetPort() {
		return osgiShellTelnetPort;
	}

	public boolean isReuse() {
		return reuse;
	}

	public Set<String> getCreateJvmOptions() {
		return createJvmOptions;
	}

	public Set<String> getDeleteJvmOption() {
		return deleteJvmOption;
	}

	public Set<Property> getLoggingAttributes() {
		return loggingAttributes;
	}
	
	public Set<Property> getProperties() {
		return properties;
	}

	public Set<ConnectionFactory> getConnectionFactories() {
		return connectionFactories;
	}

	public Set<JmsResource> getJmsResources() {
		return jmsResources;
	}

	public Set<JdbcResource> getJdbcResources() {
		return jdbcResources;
	}
	
	public Set<ConnectorConnectionPool> getConnectorConnectionPools() {
		return connectorConnectionPools;
	}

	public void setConnectorConnectionPools(Set<ConnectorConnectionPool> connectorConnectionPools) {
		this.connectorConnectionPools = connectorConnectionPools;
	}
	
	public boolean hasConnectorConnectionPools() {
		return connectorConnectionPools != null && !connectorConnectionPools.isEmpty();
	}

	public Set<ConnectorResource> getConnectorResources() {
		return connectorResources;
	}

	public void setConnectorResources(Set<ConnectorResource> connectorResources) {
		this.connectorResources = connectorResources;
	}
	
	public boolean hasConnectorResources() {
		return connectorResources != null && !connectorResources.isEmpty();
	}	

	public Set<ResourceAdapter> getResourceAdapters() {
		return resourceAdapters;
	}

	public void setResourceAdapters(Set<ResourceAdapter> resourceAdapters) {
		this.resourceAdapters = resourceAdapters;
	}
	
	public boolean hasResourceAdapters() {
		return resourceAdapters != null && !resourceAdapters.isEmpty();
	}
	
	/**
	 * @return True if the domain exists (if the directory of the domain exists)
	 */
	public boolean exists() {
		return (host == null || "localhost".equals(host)) ? directory.exists() && Arrays.asList(directory.list()).contains(name) : false;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return True if the admin port respond to a socket solicitation
	 */
	public boolean isStarted() {
		try {
			Socket socket = new Socket(host, adminPort);
			socket.close();
			return true;
		}
		catch (IOException e) {
			return false;
		}
	}

	public File getDirectory() {
		return directory;
	}

	public void setDirectory(File directory) {
		this.directory = directory;
	}

	@Override
	public String toString() {
		return 
			"createJvmOptions=" + createJvmOptions + ", " +
			"adminPort=" + adminPort + ", " + 
			"connectionFactories=" + connectionFactories + ", " +
			"debuggerPort=" + debuggerPort + ", " +
			"deleteJvmOptions=" + deleteJvmOption + ", " +
			"directory=" + directory + ", " + 
			"host=" + host + ", " + 
			"httpPort=" + httpPort + ", " +
			"httpsPort=" + httpsPort + ", " + 
			"iiopPort=" + iiopPort + ", " +
			"iiopsPort=" + iiopsPort + ", " +
			"iipsmPort=" + iiopsmPort + ", " +
			"jdbcResources=" + jdbcResources + ", " +
			"jmsPort=" + jmsPort + ", " + 
			"jmsResources" + jmsResources + ", " + 
			"jmxPort=" + jmxPort + ", " + 
			"loggingAttributes=" + loggingAttributes + ", " +
			"name=" + name + ", " + 
			"properties=" + properties + ", " +
			"reuse=" + reuse;
	}
}
