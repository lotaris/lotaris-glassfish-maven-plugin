package com.lotaris.maven.plugin.glassfish;

import com.lotaris.maven.plugin.glassfish.macro.AbstractMacro;
import com.lotaris.maven.plugin.glassfish.macro.CreateDomainMacro;
import com.lotaris.maven.plugin.glassfish.model.Configuration;
import com.lotaris.maven.plugin.glassfish.model.ConnectionFactory;
import com.lotaris.maven.plugin.glassfish.model.JdbcResource;
import com.lotaris.maven.plugin.glassfish.model.Property;
import java.lang.reflect.Field;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Create a new domain in a local or remote Glassfish instance
 *
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
@Mojo(name = "create-domain", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST, requiresProject = true)
public class CreateDomainGlassfishMojo extends GlassfishMojo {
	@Override
	protected AbstractMacro getMacro() {
		return new CreateDomainMacro(configuration);
	}
	
	@Override
	protected Configuration buildConfiguration() {
		Configuration config = new Configuration(getLog(), glassfish, domain);
		
		if (domain.getConnectionFactories() != null) {
			// Ensure that the configuration of JMS Connection factories are well configured
			for (ConnectionFactory connectionFactory : domain.getConnectionFactories()) {
				// Try to set the default values to the connection factories
				for (Property property : glassfish.getJmsDefaults()) {
					try {
						setFieldValue(connectionFactory, property);
					}
					catch (NoSuchFieldException nsfe) {
						getLog().warn("No field " + property.getName() + " presents on connection factory object.");
					}
					catch (IllegalAccessException iae) {
						getLog().warn("Unable to set " + property.getName() + " on connection factory object.");
					}
				}
			}
		}
		
		if (domain.getJdbcResources() != null) {
			// Ensure that the configuration of JDBC resources are well configured
			for (JdbcResource jdbcResource : domain.getJdbcResources()) {
				// Try to set the default values to the JDBC resources
				for (Property property : glassfish.getJdbcDefaults()) {
					try {
						setFieldValue(jdbcResource, property);
					}
					catch (NoSuchFieldException nsfe) {
						getLog().warn("No field " + property.getName() + " presents on JDBC resource object.");
					}
					catch (IllegalAccessException iae) {
						getLog().warn("Unable to set " + property.getName() + " on JDBC resource object.");
					}
				}
			}
		}
		
		return config;
	}
	
	/**
	 * Reflection mechanism to allow the possibility to configure default values
	 * when a value is not set on the specific object.
	 * 
	 * For example, when you have multiple Connection Factory configured and all
	 * have the same addressList value, you would to configure it only once for all
	 * the Connection Factory.
	 * 
	 * For that, you can configure the value through the jmsDefaults properties. Once
	 * it is done, this value will be set on every Connection Factory where the addressList
	 * field is null. If the destination field is not null, the default value is ignored.
	 * 
	 * @param obj The object to set the default value
	 * @param property The property to get the field name and the value
	 * @throws NoSuchFieldException No field with the property name exists on the object
	 * @throws IllegalAccessException Unable to set the value due to security issue
	 */
	private void setFieldValue(Object obj, Property property) throws NoSuchFieldException, IllegalAccessException {
		// Retrive the field and make it accessible to set the default value
		Field field = obj.getClass().getDeclaredField(property.getName());
		field.setAccessible(true);

		// If a value is already set, do not set the default value
		if (field.get(obj) == null) {
			// Check the type of field to set it correctly
			if (field.getType() == Integer.class) {
				field.set(obj, Integer.valueOf(property.getValue()));
			}
			else if (field.getType() == Boolean.class) {
				field.set(obj, Boolean.valueOf(property.getValue()));
			}
			else {
				field.set(obj, property.getValue());
			}
		} 

		field.setAccessible(false);
	}
	
}
