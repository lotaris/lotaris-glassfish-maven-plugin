package com.lotaris.maven.plugin.glassfish.model;

import java.util.Set;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Represent the information for the hosts to add
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class JmsHost {
	@Parameter(required = true)
	private String name;
	
	@Parameter(required = true)
	private String mqHost;
	
	@Parameter
	private Integer mqPort;
	
	@Parameter
	private String mqUser;
	
	@Parameter
	private String mqPassword;
	
	@Parameter
	private Set<Property> properties;

	public String getMqHost() {
		return mqHost;
	}

	public String getName() {
		return name;
	}

	public String getMqPassword() {
		return mqPassword;
	}

	public Integer getMqPort() {
		return mqPort;
	}

	public boolean hasProperties() {
		return properties != null && !properties.isEmpty();
	}
	
	public Set<Property> getProperties() {
		return properties;
	}

	public String getMqUser() {
		return mqUser;
	}

	@Override
	public String toString() {
		return 
			"mqhost=" + mqHost + ", " +
			"mqpassword=" + mqPassword + ", " +
			"mqport=" + mqPort + ", " +
			"mquser=" + mqUser + ", " +
			"name=" + name + ", " + 
			"properties=" + properties;
	}
}
