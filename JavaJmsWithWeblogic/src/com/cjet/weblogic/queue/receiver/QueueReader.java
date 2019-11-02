package com.cjet.weblogic.queue.receiver;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.cjet.weblogic.queue.QueueProperties;

public class QueueReader implements MessageListener {
	private QueueConnectionFactory queueConnectionFactory;
	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private QueueReceiver queueReceiver;
	private Queue queue;
	private boolean quit;
	
	/**
	 * Session.CLIENT_ACKNOWLEDGE allows the JMS queue server not to dequeue message and try redeliver
	 * at a configured interval.
	 * 
	 * @param context
	 * @param queueName
	 * @throws NamingException
	 * @throws JMSException
	 */
	public void init(Context context, String queueName) throws NamingException, JMSException {
		queueConnectionFactory =(QueueConnectionFactory)context.lookup(QueueProperties.JMS_QUEUE_FACTORY);
		queueConnection = queueConnectionFactory.createQueueConnection();
		//queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queueSession = queueConnection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);
		queue = (Queue)context.lookup(queueName);
		queueReceiver = queueSession.createReceiver(queue);
		queueReceiver.setMessageListener(this);
		queueConnection.start();
	}
	
	public void close() throws JMSException {
		queueReceiver.close();
		queueSession.close();
		queueConnection.close();
	}

	
	private static InitialContext getInitialContext() throws NamingException {
		Hashtable<String,String> env = new Hashtable<String,String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, QueueProperties.JNDI_FACTORY);
		env.put(Context.PROVIDER_URL, QueueProperties.SERVER);
		return new InitialContext(env);
	}
	
	public static void main(String[] args) throws Exception{
		InitialContext initialContext = getInitialContext();
		QueueReader queueReader = new QueueReader();
		queueReader.init(initialContext, QueueProperties.QUEUE);
		System.out.println("Waiting to receive messages");
		synchronized(queueReader) {
			while(!queueReader.quit) {
				try {
					queueReader.wait();
				}
				catch(InterruptedException ie) {}
			}
		}
	}

	@Override
	public void onMessage(Message msg) {
		try {
			String msgText;
			if (msg instanceof TextMessage) {
				msgText = ((TextMessage)msg).getText();
				//raise error for jms server error handling scenario
				//raise exception skip the message acknowledge to jms server
				//throw new JMSException("TextMesasge Error......");
			}
			else {
				msgText = msg.toString();
			}
			
			System.out.println("Message received: " + msgText);
			
			if (msgText.equalsIgnoreCase("quit")) {
				synchronized(this) {
					quit = true;
					this.notifyAll(); //notify main thread to quit
				}
			}
			
			//delay acknowledge for testing
			Thread.sleep(25000);
			System.out.println("Message msg.acknowledge() sent.");
			msg.acknowledge();
		}
		catch (JMSException | InterruptedException jmsException) {
			System.err.println("Exception: " + jmsException.getMessage());
		}
	}
}
