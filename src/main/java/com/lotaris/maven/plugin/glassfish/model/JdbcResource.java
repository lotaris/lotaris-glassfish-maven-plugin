package com.lotaris.maven.plugin.glassfish.model;

import java.util.Set;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * A JDBC Resource configuration. See the ASADMIN help to get more information on
 * the different configurable values.
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class JdbcResource {
	@Parameter(required = true)
	private String jndiName;
	
	@Parameter
	private String description;
	
	@Parameter
	private String poolDescription;
	
	@Parameter
	private String type;
	
	@Parameter
	private String dataSourceClassName;
	
	@Parameter
	private Integer steadyPoolSize;
	
	@Parameter
	private Integer maxPoolSize;
	
	@Parameter
	private Integer maxWait;
	
	@Parameter
	private Integer poolResize;
	
	@Parameter
	private Integer idleTimeout;
	
	@Parameter
	private String initSql;

	@Parameter
	private String isolationLevel;
	
	@Parameter
	private Boolean isolationGuaranteed;
	
	@Parameter
	private Boolean isConnectValidateReq;
	
	@Parameter
	private String validationMethod;
	
	@Parameter
	private String validationTable;
	
	@Parameter
	private Boolean failConnection;
	
	@Parameter
	private Boolean allowNonComponentCallers;
	
	@Parameter
	private Boolean nonTransactionalConnections;
	
	@Parameter
	private Integer validateAtMostOncePeriod;
	
	@Parameter
	private Integer leakTimeout;
	
	@Parameter
	private Boolean leakReclaim;
	
	@Parameter
	private Integer statementLeakTimeout;
	
	@Parameter
	private Boolean statementLeakReclaim;
	
	@Parameter
	private Integer creationRetryAttempts;
	
	@Parameter
	private Integer creationRetryInterval;
	
	@Parameter
	private String sqlTraceListeners;
	
	@Parameter
	private Integer statementTimeout;
	
	@Parameter
	private Boolean lazyConnectionEnlistment;
	
	@Parameter
	private Boolean lazyConnectionAssociation;
	
	@Parameter
	private Boolean associateWithThread;
	
	@Parameter
	private String driverClassName;
	
	@Parameter
	private Boolean matchConnections;
	
	@Parameter
	private Integer maxConnectionUsageCount;
	
	@Parameter
	private Boolean ping;
	
	@Parameter
	private Boolean pooling;
	
	@Parameter
	private Integer statementCacheSize;
	
	@Parameter
	private Boolean timerPool;
	
	@Parameter
	private String validationClassName;
	
	@Parameter
	private Boolean wrapJdbcObjects;
	
	@Parameter
	private Set<Property> poolProperties;
	
	@Parameter
	private Set<Property> properties;
	
	@Parameter
	private String url;
	
	@Parameter
	private String user;
	
	@Parameter
	private String password;

	public String getJndiName() {
		return jndiName;
	}

	public String getDescription() {
		return description;
	}

	public String getPoolDescription() {
		return poolDescription;
	}

	public String getDataSourceClassName() {
		return dataSourceClassName;
	}

	public String getType() {
		return type;
	}

	public Integer getSteadyPoolSize() {
		return steadyPoolSize;
	}

	public Integer getMaxPoolSize() {
		return maxPoolSize;
	}

	public Integer getMaxWait() {
		return maxWait;
	}

	public Integer getPoolResize() {
		return poolResize;
	}

	public Integer getIdleTimeout() {
		return idleTimeout;
	}

	public String getInitSql() {
		return initSql;
	}

	public String getIsolationLevel() {
		return isolationLevel;
	}

	public Boolean getIsolationGuaranteed() {
		return isolationGuaranteed;
	}

	public Boolean getIsConnectValidateReq() {
		return isConnectValidateReq;
	}

	public String getValidationMethod() {
		return validationMethod;
	}

	public String getValidationTable() {
		return validationTable;
	}

	public Boolean getFailConnection() {
		return failConnection;
	}

	public Boolean getAllowNonComponentCallers() {
		return allowNonComponentCallers;
	}

	public Boolean getNonTransactionalConnections() {
		return nonTransactionalConnections;
	}

	public Integer getValidateAtMostOncePeriod() {
		return validateAtMostOncePeriod;
	}

	public Integer getLeakTimeout() {
		return leakTimeout;
	}

	public Boolean getLeakReclaim() {
		return leakReclaim;
	}

	public Integer getStatementLeakTimeout() {
		return statementLeakTimeout;
	}

	public Boolean getStatementLeakReclaim() {
		return statementLeakReclaim;
	}

	public Integer getCreationRetryAttempts() {
		return creationRetryAttempts;
	}

	public Integer getCreationRetryInterval() {
		return creationRetryInterval;
	}

	public String getSqlTraceListeners() {
		return sqlTraceListeners;
	}

	public Integer getStatementTimeout() {
		return statementTimeout;
	}

	public Boolean getLazyConnectionEnlistment() {
		return lazyConnectionEnlistment;
	}

	public Boolean getLazyConnectionAssociation() {
		return lazyConnectionAssociation;
	}

	public Boolean getAssociateWithThread() {
		return associateWithThread;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public Boolean getMatchConnections() {
		return matchConnections;
	}

	public Integer getMaxConnectionUsageCount() {
		return maxConnectionUsageCount;
	}

	public Boolean getPing() {
		return ping;
	}

	public Boolean getPooling() {
		return pooling;
	}

	public Integer getStatementCacheSize() {
		return statementCacheSize;
	}

	public String getValidationClassName() {
		return validationClassName;
	}

	public Boolean getTimerPool() {
		return timerPool;
	}

	public void setTimerPool(Boolean timerPool) {
		this.timerPool = timerPool;
	}
	
	public Boolean getWrapJdbcObjects() {
		return wrapJdbcObjects;
	}

	public Set<Property> getPoolProperties() {
		return poolProperties;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * @return A pool id based on the JDNI name
	 */
	public String getPoolId() {
		return jndiName.replace("jdbc/", "") + "-pool";
	}
	
	@Override
	public String toString() {
		return ""
			+ "allowNonComponentCallers=" + allowNonComponentCallers + ", "
			+ "associateWithThread=" + associateWithThread + ", "
			+ "creationRetryAttempts=" + creationRetryAttempts + ", "
			+ "creationRetryInterval=" + creationRetryInterval + ", "
			+ "dataSourceClassName=" + dataSourceClassName + ", "
			+ "description=" + description + ", "
			+ "driverClassName=" + driverClassName + ", "
			+ "failConnection=" + failConnection + ", "
			+ "idleTimeout=" + idleTimeout + ", "
			+ "isConnectValidateReq=" + isConnectValidateReq + ", "
			+ "isolationGuaranteed=" + isolationGuaranteed + ", "
			+ "isolationLevel=" + isolationLevel + ", "
			+ "jndiName=" + jndiName + ", "
			+ "initSql=" + initSql + ", "
			+ "lazyConnectionAssociation=" + lazyConnectionAssociation + ", "
			+ "lazyConnectionEnlistment=" + lazyConnectionEnlistment + ", "
			+ "leakReclaim=" + leakReclaim + ", "
			+ "leakTimeout=" + leakTimeout + ", "
			+ "matchConnections=" + matchConnections + ", "
			+ "maxConnectionUsageCount=" + maxConnectionUsageCount + ", "
			+ "maxPoolSize=" + maxPoolSize + ", "
			+ "maxWait=" + maxWait + ", "
			+ "nonTransactionalConnections=" + nonTransactionalConnections + ", "
			+ "password=" + password + ", "
			+ "ping=" + ping + ", pooling=" + pooling + ", "
			+ "poolDescription=" + poolDescription + ", "
			+ "poolProperties=" + poolProperties + ", "
			+ "poolResizeSize=" + poolResize + ", "
			+ "properties=" + properties + ", "
			+ "sqlTraceListeners=" + sqlTraceListeners + ", "
			+ "statementCacheSize=" + statementCacheSize + ", "
			+ "statementLeakReclaim=" + statementLeakReclaim + ", "
			+ "statementLeakTimeout=" + statementLeakTimeout + ", "
			+ "statementTimeout=" + statementTimeout + ", "
			+ "steadyPoolSize=" + steadyPoolSize + ", "
			+ "type=" + type + ", "
			+ "url=" + url + ", "
			+ "user=" + user + ", "
			+ "validateAtMostOncePeriod=" + validateAtMostOncePeriod + ", "
			+ "validationClassName=" + validationClassName + ", "
			+ "validationMethod=" + validationMethod + ", "
			+ "validationTable=" + validationTable + ", "
			+ "wrapJdbcObjects=" + wrapJdbcObjects;
	}
}
