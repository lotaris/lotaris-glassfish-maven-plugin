package com.lotaris.maven.plugin.glassfish;

import com.lotaris.maven.plugin.glassfish.macro.AbstractMacro;
import com.lotaris.maven.plugin.glassfish.macro.RedeployMacro;
import com.lotaris.maven.plugin.glassfish.model.Configuration;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import com.lotaris.maven.plugin.glassfish.model.RedeployConfiguration;

/**
 * Re-deploy an application on a glassfish domain
 *
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
@Mojo(name = "redeploy", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST, requiresProject = true)
public class RedeployGlassfishMojo extends GlassfishMojo {
	@Parameter(required = true)
	private RedeployConfiguration deployConfig;

	@Override
	protected AbstractMacro getMacro() {
		return new RedeployMacro(configuration);
	}

	@Override
	protected Configuration buildConfiguration() {
		return new Configuration(getLog(), glassfish, domain, deployConfig);
	}
}
