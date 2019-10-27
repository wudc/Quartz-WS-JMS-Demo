package com.cjet.jms.config;

import java.util.Properties;

import javax.naming.Context;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application-jms.properties")
@EnableConfigurationProperties
@ConfigurationProperties(prefix="jms.service")
public class JmsServiceConfig {
	private String url;
	private String username;
	private String password;
	private String connectionFactoryName;
	private String queueName;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConnectionFactoryName() {
		return connectionFactoryName;
	}
	public void setConnectionFactoryName(String connectionFactoryName) {
		this.connectionFactoryName = connectionFactoryName;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	
	public Properties getJndiProperties() {
		final Properties jndiProps = new Properties();
		jndiProps.setProperty(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		jndiProps.setProperty(Context.PROVIDER_URL, url);
		
		return jndiProps;
	}
}
