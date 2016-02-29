package com.lotaris.maven.plugin.glassfish.model;

import java.util.Set;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Configuration for a resource adapter.
 * 
 * @author Valentin Delaye <valentin.delaye@novaccess.ch>
 */
public class ResourceAdapter {
	
	/**
	 * The deploy configuration for the resource adapter
	 */
	@Parameter(required = true)
	private DeployConfiguration deployConfig;
	
	/**
	 * A set of additional properties to configure
	 */
	@Parameter
	private Set<Property> properties;

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}
	
	public boolean hasProperties() {
		return this.properties != null && !this.properties.isEmpty();
	}
	
	public DeployConfiguration getDeployConfig() {
		return deployConfig;
	}

	public void setDeployConfig(DeployConfiguration deployConfig) {
		this.deployConfig = deployConfig;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		String props = null;
		if(properties != null) {
			for (Property p : properties) {
				builder.append(p).append(", ");
			}
			props = builder.toString().replaceAll(", $", "");
		}
		return 
			"deployConfig=" + deployConfig + ", " +
			"properties=" + props;
	}
	
}
