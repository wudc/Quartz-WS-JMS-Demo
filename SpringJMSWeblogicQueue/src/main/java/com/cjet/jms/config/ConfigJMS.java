package com.cjet.jms.config;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.JndiDestinationResolver;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

import com.cjet.jms.producer.Sender;

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
public class ConfigJMS {
	
	@Bean
    public JndiTemplate jndiTemplate() {
        return new JndiTemplate();
    }
     
    @Bean
    public JndiObjectFactoryBean queueConnectionFactory() {
        JndiObjectFactoryBean queueConnectionFactory = new JndiObjectFactoryBean();
        queueConnectionFactory.setJndiTemplate(jndiTemplate());
        queueConnectionFactory.setJndiName("jms/MyJMSConnectionFactory");
        return queueConnectionFactory;
    }
     
    @Bean
    public JndiDestinationResolver jmsDestinationResolver() {
        JndiDestinationResolver destResolver = new JndiDestinationResolver();
        destResolver.setJndiTemplate(jndiTemplate());
        destResolver.setCache(true);
         
        return destResolver;
    }
     
    @Bean
    public JmsTemplate queueSenderTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory((ConnectionFactory) queueConnectionFactory().getObject());
        template.setDestinationResolver(jmsDestinationResolver());
        return template;
    }
     
    @Bean
    public JndiObjectFactoryBean jmsQueue() {
        JndiObjectFactoryBean jmsQueue = new JndiObjectFactoryBean();
        jmsQueue.setJndiTemplate(jndiTemplate());
        jmsQueue.setJndiName("jms/MyJMSQueue");
         
        return jmsQueue;
    }
	@Bean
	public Sender sender() {
		return new Sender();
	}
}
