package com.lotaris.maven.plugin.glassfish.model;

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

	public DeployConfiguration getDeployConfig() {
		return deployConfig;
	}

	public void setDeployConfig(DeployConfiguration deployConfig) {
		this.deployConfig = deployConfig;
	}

}
