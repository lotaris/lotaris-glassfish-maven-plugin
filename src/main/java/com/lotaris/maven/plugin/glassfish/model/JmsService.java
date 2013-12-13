package com.lotaris.maven.plugin.glassfish.model;

import java.util.Set;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Represent the information the configuration of JMS Service
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class JmsService {
	@Parameter
	private Set<Property> properties;

	public boolean hasProperties() {
		return properties != null && !properties.isEmpty();
	}
	
	public Set<Property> getProperties() {
		return properties;
	}

	@Override
	public String toString() {
		return 
			"properties=" + properties;
	}
}
