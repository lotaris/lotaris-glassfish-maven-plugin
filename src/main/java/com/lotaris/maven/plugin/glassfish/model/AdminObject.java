package com.lotaris.maven.plugin.glassfish.model;

import java.util.Set;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Represents an admin object and configuration required for its creation.
 * 
 * @author Valentin Delaye <valentin.delaye@novaccess.ch>
 */
public class AdminObject {

	/**
	 * JNDI name of the admin object
	 */
	@Parameter(required = true)
	private String jndiName;
	
	/**
	 * Name of the resource adapter to use
	 */
	@Parameter(required = true)
	private String raname;
	
	/**
	 * Type (class name) of the resource
	 */
	@Parameter(required = true)
	private String restype;
	
	@Parameter
	private Set<Property> properties;

	public String getJndiName() {
		return jndiName;
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	public String getRaname() {
		return raname;
	}

	public void setRaname(String raname) {
		this.raname = raname;
	}

	public String getRestype() {
		return restype;
	}

	public void setRestype(String restype) {
		this.restype = restype;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if(properties != null) {
			for (Property p : properties) {
				builder.append(p).append(", ");
			}		
		}
		return 
			"jndiName=" + jndiName + ", " +
			"raname=" + raname + "," +
			"restype=" + restype + "," +
			"properties=" + builder.toString();
	}
}
