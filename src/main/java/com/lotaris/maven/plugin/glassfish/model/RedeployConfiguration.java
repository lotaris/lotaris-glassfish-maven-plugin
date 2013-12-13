package com.lotaris.maven.plugin.glassfish.model;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * The re-deployment configuration of an application
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class RedeployConfiguration extends AbstractDeployConfiguration {
	@Parameter(required = true)
	private String name;

	public String getName() {
		return name;
	}
	
	public DeployConfiguration getDeployConfiguration() {
		return new DeployConfiguration(this);
	}
	
	@Override
	public String toString() {
		return "super(" + super.toString() + "), " +
			"name=" + name;
	}
}
