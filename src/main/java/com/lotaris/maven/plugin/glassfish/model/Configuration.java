package com.lotaris.maven.plugin.glassfish.model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.maven.plugin.logging.Log;

/**
 * Configuration used to build and run the ASADMIN commands
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class Configuration {
	/**
	 * The glassfish configuration
	 */
	private Glassfish glassfish;
	
	/**
	 * The domain configuration
	 */
	private Domain domain;

	/**
	 * Deployment configuration
	 */
	private DeployConfiguration deployConfiguration;
	
	/**
	 * Redeployment configuration
	 */
	private RedeployConfiguration redeployConfiguration;
	
	/**
	 * Undeployment configuration
	 */
	private UndeployConfiguration undeployConfiguration;
	
	/**
	 * Keep the domain address to be able to know if the domain
	 * is a local domain or a remote domain
	 */
	private InetAddress domainAddress;	
	
	/**
	 * Logger
	 */
	private Log log;
	
	/**
	 * Constructor
	 * 
	 * @param log A logger
	 * @param glassfish The glassfish configuration
	 * @param domain The domain configuration
	 */
	public Configuration(Log log, Glassfish glassfish, Domain domain) {
		this.glassfish = glassfish;
		this.domain = domain;
		this.log = log;

		try {
			domainAddress = InetAddress.getByName(domain.getHost());
		}
		catch (UnknownHostException uhe) {
			throw new IllegalArgumentException(uhe);
		}
	}

	/**
	 * Constructor
	 * 
	 * @param log A logger
	 * @param glassfish The glassfish configuration
	 * @param domain The domain configuration
	 * @param deployConfiguration A deploy configuration
	 */
	public Configuration(Log log, Glassfish glassfish, Domain domain, DeployConfiguration deployConfiguration) {
		this(log, glassfish, domain);
		this.deployConfiguration = deployConfiguration;
	}

	/**
	 * Constructor
	 * 
	 * @param log A logger
	 * @param glassfish The glassfish configuration
	 * @param domain The domain configuration
	 * @param redeployConfiguration A re-deploy configuration
	 */
	public Configuration(Log log, Glassfish glassfish, Domain domain, RedeployConfiguration redeployConfiguration) {
		this(log, glassfish, domain);
		this.redeployConfiguration = redeployConfiguration;
	}

	/**
	 * Constructor
	 * 
	 * @param log A logger
	 * @param glassfish The glassfish configuration
	 * @param domain The domain configuration
	 * @param undeployConfiguration A un-deploy configuration
	 */
	public Configuration(Log log, Glassfish glassfish, Domain domain, UndeployConfiguration undeployConfiguration) {
		this(log, glassfish, domain);
		this.undeployConfiguration = undeployConfiguration;
	}

	public Domain getDomain() {
		return domain;
	}

	public Glassfish getGlassfish() {
		return glassfish;
	}

	public DeployConfiguration getDeployConfiguration() {
		return deployConfiguration;
	}

	public void setDeployConfiguration(DeployConfiguration deployConfiguration) {
		this.deployConfiguration = deployConfiguration;
	}
	
	public RedeployConfiguration getRedeployConfiguration() {
		return redeployConfiguration;
	}

	public UndeployConfiguration getUndeployConfiguration() {
		return undeployConfiguration;
	}
	
	public boolean isLocalDomain() {
		return domainAddress.isLoopbackAddress();
	}
	
	public Log getLog() {
		return log;
	}

	@Override
	public String toString() {
		return 
			"deploytConfiguration=" + deployConfiguration + ", " +
			"domain=" + domain + ", " +
			"glassfish=" + glassfish + ", " +
			"redeployConfiguration=" + redeployConfiguration + ", " +
			"undeployConfiguration" + undeployConfiguration;
	}
}
