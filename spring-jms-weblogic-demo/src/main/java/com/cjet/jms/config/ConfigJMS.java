package com.cjet.jms.config;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

import com.cjet.jms.listener.QueueConsumer;

/**
 * To install Weblogic maven dependenices to local .m2 repository. See this example mvn command
 * 
 * -DgroupId is the repository location top level folders
 * -Dversion is the current weblogic server version
 * 
 * mvn install:install-file -Dfile=wlthint3client.jar -DgeneratePom=true -DgroupId=com.oracle.weblogic -DartifactId=wlthint3client -Dversion=12.1.3 -Dpackaging=jar
 *
 * @author wudc
 *
 */
@Configuration
@EnableJms
public class ConfigJMS {
	
	private final JmsServiceConfig config;
	private JndiTemplate jndiTemplate;
	private JndiObjectFactoryBean queueConnectionFactory;
	private JndiObjectFactoryBean jmsQueue;
	
	@Autowired
	public ConfigJMS(JmsServiceConfig config) {
		this.config = config;
	}
	
	@Bean (name="jndiTemplate")
    public JndiTemplate jndiTemplate() {
		System.out.println("Create jndiTemplate() .......... ");

		jndiTemplate = new JndiTemplate();
		System.out.println("Context: " + config.getJndiProperties().getProperty(Context.INITIAL_CONTEXT_FACTORY));
		System.out.println("Url: " + config.getJndiProperties().getProperty(Context.PROVIDER_URL));
		System.out.println("User: " + config.getJndiProperties().getProperty(Context.SECURITY_PRINCIPAL));
		System.out.println("Password: " + config.getJndiProperties().getProperty(Context.SECURITY_CREDENTIALS));

		jndiTemplate.setEnvironment(config.getJndiProperties());
        return new JndiTemplate();
    }
     
    @Bean (name="queueConnectionFactory")
    public JndiObjectFactoryBean queueConnectionFactory() {
		System.out.println("Create queueConnectionFactory() .......... ");
        queueConnectionFactory = new JndiObjectFactoryBean();
        queueConnectionFactory.setJndiTemplate(jndiTemplate);
        queueConnectionFactory.setJndiName(config.getConnectionFactoryName());
        return queueConnectionFactory;
    }
     
    @Bean (name="jmsQueue")
    public JndiObjectFactoryBean jmsQueue() {
		System.out.println("Create jmsQueue() .......... ");
        jmsQueue = new JndiObjectFactoryBean();
        jmsQueue.setJndiTemplate(jndiTemplate);
        jmsQueue.setJndiName(config.getQueueName());
         
        return jmsQueue;
    }

    @Bean (name="listener")
	public QueueConsumer queueListener() {
		System.out.println("Create queueListener() .......... ");
		return new QueueConsumer();
	}
	
	@Bean (name="messageListener")
	public WeblogicMessageListenerContainer messageListener() {
		System.out.println("Create messageListener() .......... ");
		WeblogicMessageListenerContainer listener = new WeblogicMessageListenerContainer();
		listener.setJndiTemplate(jndiTemplate);
		listener.setConcurrentConsumers(1);
		listener.setConnectionFactory((ConnectionFactory)queueConnectionFactory.getObject());
		listener.setDestination((Destination)jmsQueue.getObject());
		listener.setMessageListener(queueListener());
		return listener;
	}
	
}
