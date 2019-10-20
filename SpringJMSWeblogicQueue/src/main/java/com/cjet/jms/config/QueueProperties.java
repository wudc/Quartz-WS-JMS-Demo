package com.cjet.jms.config;

public interface QueueProperties {
	public final static String SERVER="t3://localhost:7001";
	public final static String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";
	public final static String JMS_QUEUE_FACTORY="javax.jms.QueueConnectionFactory";
	public final static String QUEUE="jms.MyJMSQueue";
	
}
