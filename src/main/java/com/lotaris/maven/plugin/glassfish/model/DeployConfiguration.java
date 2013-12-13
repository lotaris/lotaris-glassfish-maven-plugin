package com.lotaris.maven.plugin.glassfish.model;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * The deployment configuration of an application
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class DeployConfiguration extends AbstractDeployConfiguration {
	@Parameter
	private String name;

	public DeployConfiguration() {
	}

	/**
	 * Constructor to copy redeploy configuration into a deploy configuration
	 * 
	 * @param rc Redeploy configuration to copy
	 */
	public DeployConfiguration(RedeployConfiguration rc) {
		super(rc.getFile(), rc.getForce(), rc.getUpload(), rc.getRetrieve(), rc.getDbVendorName(), rc.getCreateTables(), rc.getDropAndCreateTables(), rc.getUniqueTableNames(), 
			rc.getDeploymentPlan(), rc.getAltdd(), rc.getRuntimeAltdd(), rc.getDeploymentOrder(), rc.getEnabled(), rc.getGenerateRmiStubs(), rc.getContextRoot(), rc.getPreCompileJsp(), 
			rc.getVerify(), rc.getVirtualServers(), rc.getAvailabilityEnabled(), rc.getAsynReplication(), rc.getEnabled(), rc.getKeepState(), rc.getLibraries(), rc.getType(), 
			rc.getProperties());
		
		name = rc.getName();
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "super(" + super.toString() + "), " +
			"name=" + name;
	}
}
