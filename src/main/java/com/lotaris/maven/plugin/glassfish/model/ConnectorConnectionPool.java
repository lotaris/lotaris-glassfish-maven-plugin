package com.lotaris.maven.plugin.glassfish.model;

import java.util.Set;
import org.apache.maven.plugins.annotations.Parameter;

/**
 *
 * @author Valentin Delaye <valentin.delaye@novaccess.ch>
 */
public class ConnectorConnectionPool {

	/**
	 * JNDI name of the connector connector pool
	 */
	@Parameter(required = true)
	private String jndiName;
	
	/**
	 * Name of the resource adapter
	 */
	@Parameter(required = true)
	private String raname;
	
	/**
	 * The name of the connection definition
	 */
	@Parameter(required = true)
	private ConnectionFactory.Type connectionDefinition;
	
	/**
	 * Ping during creation of the connection pool
	 */
	@Parameter(required = false, defaultValue = "true")
	private Boolean ping;
	
	/**
	 * Ping during creation of the connection pool
	 */
	@Parameter(required = false, defaultValue = "true")
	private Boolean isConnectValidateReq;
	
	@Parameter
	private Set<Property> properties;

	public String getJndiName() {
		return jndiName;
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	public String getRaname() {
		return raname;
	}

	public void setRaname(String raname) {
		this.raname = raname;
	}

	public ConnectionFactory.Type getConnectionDefinition() {
		return connectionDefinition;
	}

	public void setConnectionDefinition(ConnectionFactory.Type connectionDefinition) {
		this.connectionDefinition = connectionDefinition;
	}

	public Boolean getPing() {
		return ping;
	}

	public void setPing(Boolean ping) {
		this.ping = ping;
	}

	public Boolean getIsConnectValidateReq() {
		return isConnectValidateReq;
	}

	public void setIsConnectValidateReq(Boolean isConnectValidateReq) {
		this.isConnectValidateReq = isConnectValidateReq;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "jndiName=" + jndiName +
			", raname=" + raname +
			", connectionDefinition=" + connectionDefinition +
			", ping=" + ping +
			", isConnectValidateReq=" + isConnectValidateReq + '}';
	}
	
}
