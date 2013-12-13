package com.lotaris.maven.plugin.glassfish;

import com.lotaris.maven.plugin.glassfish.macro.AbstractMacro;
import com.lotaris.maven.plugin.glassfish.macro.UndedeployMacro;
import com.lotaris.maven.plugin.glassfish.model.Configuration;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.lotaris.maven.plugin.glassfish.model.UndeployConfiguration;

/**
 * Un-deploy an application on a glassfish domain
 *
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
@Mojo(name = "undeploy", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST, requiresProject = true)
public class UndeployGlassfishMojo extends GlassfishMojo {
	@Parameter(required = true)
	private UndeployConfiguration deployConfig;

	@Override
	protected AbstractMacro getMacro() {
		return new UndedeployMacro(configuration);
	}

	@Override
	protected Configuration buildConfiguration() {
		return new Configuration(getLog(), glassfish, domain, deployConfig);
	}
}
