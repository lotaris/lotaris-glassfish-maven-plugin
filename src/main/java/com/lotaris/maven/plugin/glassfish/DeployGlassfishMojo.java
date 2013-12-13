package com.lotaris.maven.plugin.glassfish;

import com.lotaris.maven.plugin.glassfish.macro.AbstractMacro;
import com.lotaris.maven.plugin.glassfish.model.Configuration;
import com.lotaris.maven.plugin.glassfish.model.DeployConfiguration;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.lotaris.maven.plugin.glassfish.macro.DeployMacro;

/**
 * Deploy an application on a Glassfish domain
 *
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
@Mojo(name = "deploy", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST, requiresProject = true)
public class DeployGlassfishMojo extends GlassfishMojo {
	@Parameter(required = true)
	private DeployConfiguration deployConfig;

	@Override
	protected AbstractMacro getMacro() {
		return new DeployMacro(configuration);
	}

	@Override
	protected Configuration buildConfiguration() {
		return new Configuration(getLog(), glassfish, domain, deployConfig);
	}
}
