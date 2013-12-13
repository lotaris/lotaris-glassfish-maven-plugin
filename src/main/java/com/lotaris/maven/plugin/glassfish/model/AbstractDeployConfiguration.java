package com.lotaris.maven.plugin.glassfish.model;

import java.util.Set;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Define the different options that are available to deploy/redeploy commands
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public abstract class AbstractDeployConfiguration {
	@Parameter(required = true)
	private String file;
	
	@Parameter
	private Boolean force;
	
	@Parameter
	private Boolean upload;
	
	@Parameter
	private String retrieve;
	
	@Parameter
	private String dbVendorName;
	
	@Parameter
	private Boolean createTables;
	
	@Parameter
	private Boolean dropAndCreateTables;
	
	@Parameter
	private Boolean uniqueTableNames;
	
	@Parameter
	private String deploymentPlan;
	
	@Parameter
	private String altdd;
	
	@Parameter
	private String runtimeAltdd;
	
	@Parameter
	private Integer deploymentOrder;
	
	@Parameter
	private Boolean enabled;
	
	@Parameter
	private Boolean generateRmiStubs;
	
	@Parameter
	private String contextRoot;
	
	@Parameter
	private Boolean preCompileJsp;
	
	@Parameter
	private Boolean verify;
	
	@Parameter
	private String virtualServers;
	
	@Parameter
	private Boolean availabilityEnabled;
	
	@Parameter
	private Boolean asynReplication;
	
	@Parameter
	private Boolean lenabled;
	
	@Parameter
	private Boolean keepState;
	
	@Parameter
	private String libraries;
	
	@Parameter
	private String type;
	
	@Parameter
	private Set<Property> properties;

	public AbstractDeployConfiguration() {
	}

	protected AbstractDeployConfiguration(String file, Boolean force, Boolean upload, String retrieve, String dbVendorName, 
	Boolean createTables, Boolean dropAndCreateTables, Boolean uniqueTableNames, String deploymentPlan, String altdd, String runtimeAltdd, 
	Integer deploymentOrder, Boolean enabled, Boolean generateRmiStubs, String contextRoot, Boolean preCompileJsp, Boolean verify, 
	String virtualServers, Boolean availabilityEnabled, Boolean asynReplication, Boolean lenabled, Boolean keepState, String libraries, 
	String type, Set<Property> properties) {
	
		this.file = file;
		this.force = force;
		this.upload = upload;
		this.retrieve = retrieve;
		this.dbVendorName = dbVendorName;
		this.createTables = createTables;
		this.dropAndCreateTables = dropAndCreateTables;
		this.uniqueTableNames = uniqueTableNames;
		this.deploymentPlan = deploymentPlan;
		this.altdd = altdd;
		this.runtimeAltdd = runtimeAltdd;
		this.deploymentOrder = deploymentOrder;
		this.enabled = enabled;
		this.generateRmiStubs = generateRmiStubs;
		this.contextRoot = contextRoot;
		this.preCompileJsp = preCompileJsp;
		this.verify = verify;
		this.virtualServers = virtualServers;
		this.availabilityEnabled = availabilityEnabled;
		this.asynReplication = asynReplication;
		this.lenabled = lenabled;
		this.keepState = keepState;
		this.libraries = libraries;
		this.type = type;
		this.properties = properties;
	}
	
	public Boolean getUpload() {
		return upload;
	}

	public String getRetrieve() {
		return retrieve;
	}

	public String getDbVendorName() {
		return dbVendorName;
	}

	public Boolean getCreateTables() {
		return createTables;
	}

	public Boolean getUniqueTableNames() {
		return uniqueTableNames;
	}

	public String getDeploymentPlan() {
		return deploymentPlan;
	}

	public String getAltdd() {
		return altdd;
	}

	public String getRuntimeAltdd() {
		return runtimeAltdd;
	}

	public Integer getDeploymentOrder() {
		return deploymentOrder;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public Boolean getGenerateRmiStubs() {
		return generateRmiStubs;
	}

	public String getContextRoot() {
		return contextRoot;
	}

	public Boolean getPreCompileJsp() {
		return preCompileJsp;
	}

	public Boolean getVerify() {
		return verify;
	}

	public String getVirtualServers() {
		return virtualServers;
	}

	public Boolean getAvailabilityEnabled() {
		return availabilityEnabled;
	}

	public Boolean getAsynReplication() {
		return asynReplication;
	}

	public Boolean getLenabled() {
		return lenabled;
	}

	public Boolean getKeepState() {
		return keepState;
	}

	public String getLibraries() {
		return libraries;
	}

	public String getType() {
		return type;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public String getFile() {
		return file;
	}

	public Boolean getDropAndCreateTables() {
		return dropAndCreateTables;
	}

	public Boolean getForce() {
		return force;
	}
	
	@Override
	public String toString() {
		return 
			"altdd=" + altdd + ", " + 
			"asynReplication=" + asynReplication + ", " + 
			"availabilityEnabled=" + availabilityEnabled + ", " + 
			"contextRoot=" + contextRoot + ", " + 
			"createTables=" + createTables + ", " + 
			"deploymentOrder=" + deploymentOrder + ", " + 
			"deploymentPlan=" + deploymentPlan + ", " +
			"dbVendorName=" + dbVendorName + ", " + 
			"dropAndCreateTables=" + dropAndCreateTables + ", " +
			"enabled=" + enabled + ", " + 
			"file=" + file + ", " + 
			"force=" + force + ", " +
			"generateRmiStubs=" + generateRmiStubs + ", " + 
			"keepState=" + keepState + ", " + 
			"lenabled=" + lenabled + ", " + 
			"libraries=" + libraries + ", " + 
			"preCompileJsp=" + preCompileJsp + ", " + 
			"properties=" + properties + ", " + 
			"retrieve=" + retrieve + ", " + 
			"runtimeAltdd=" + runtimeAltdd + ", " + 
			"type=" + type + ", " + 
			"uniqueTableNames=" + uniqueTableNames + ", " + 
			"upload=" + upload + ", " + 
			"verify=" + verify + ", " + 
			"virtualServers=" + virtualServers;
	}
	
	
}
