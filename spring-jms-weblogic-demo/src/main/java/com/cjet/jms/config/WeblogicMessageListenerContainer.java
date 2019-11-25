package com.cjet.jms.config;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jndi.JndiTemplate;

/**
 * The main problem with using a JNDI context for Weblogic Server is that it's associated with a Thread, 
 * and this doesn't fly in the multi-threaded implementation, which Spring does: WLS Documentation. The alternative is 
 * that you need a new InitialContext with the JndiTemplate which has the authentication credential. This
 * solution is a hack unless you upgrade WebLogic to 12.2 or higher. 
 * 
 * @author wudc
 *
 */
public class WeblogicMessageListenerContainer extends DefaultMessageListenerContainer {
	private JndiTemplate jndiTemplate;

	public void setJndiTemplate(JndiTemplate jndiTemplate) {
		this.jndiTemplate = jndiTemplate;
	}

	/**
	 * A hack for using WebLogic 12.1.3 called by the override wlFix method suggested by Spring
	 */
	protected void wlFix() {
		// Associate JNDI variables (user and password) with this thread for the benefit
		// of the WL drivers.
		try {
			InitialContext ic = new InitialContext(jndiTemplate.getEnvironment());
		} catch (NamingException e) {
			logger.warn(e.getMessage());
		}
	}

	@Override
	protected Connection createSharedConnection() throws JMSException {
		wlFix();
		return super.createSharedConnection();
	}
}
