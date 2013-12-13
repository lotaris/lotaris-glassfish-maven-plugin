package com.lotaris.maven.plugin.glassfish.model;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * A property configuration
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class Property {
	@Parameter(required = true)
	private String name;

	@Parameter(required = true)
	private String value;

	/**
	 * Constructor
	 */
	public Property() {}

	/**
	 * Constructor
	 * 
	 * @param name A name
	 * @param value A value
	 */
	public Property(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return 
			"name=" + name + ", " +
			"value=" + value;
	}
}
