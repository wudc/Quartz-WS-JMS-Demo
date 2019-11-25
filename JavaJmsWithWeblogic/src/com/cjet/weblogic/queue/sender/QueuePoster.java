package com.cjet.weblogic.queue.sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.cjet.weblogic.queue.QueueProperties;

public class QueuePoster {
	private QueueConnectionFactory queueConnectionFactory;
	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private QueueSender queueSender;
	private Queue queue;
	private TextMessage message;
	
	public void init(Context context, String queueName) throws NamingException, JMSException {
		queueConnectionFactory =(QueueConnectionFactory)context.lookup(QueueProperties.JMS_QUEUE_FACTORY);
		queueConnection = queueConnectionFactory.createQueueConnection();
		queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queue = (Queue)context.lookup(queueName);
		queueSender = queueSession.createSender(queue);
		message = queueSession.createTextMessage();
		queueConnection.start();
	}
	
	public void post(String msg) throws JMSException {
		message.setText(msg);
		queueSender.send(message);
	}
	
	public void close() throws JMSException {
		queueSender.close();
		queueSession.close();
		queueConnection.close();
	}
	
	private static void sendToServer(QueuePoster queuePoster) throws IOException, JMSException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		boolean readFlag = true;
		System.out.println("Enter message to send to weblogic server (Enter quit to end):n");
		while(readFlag) {
			System.out.print("Enter Mesage: ");
			String msg = bufferedReader.readLine();
			if (msg.equals("quit")) {
				queuePoster.post(msg);
				System.exit(0);
			}
			queuePoster.post(msg);
			System.out.println();
		}
		bufferedReader.close();
	}
	
	private static void autoSend(QueuePoster queuePoster) throws IOException, JMSException, InterruptedException {
		while(true) {
			queuePoster.post("Greeting David Wu. From Spring Boot to Weblogic JMS Queue. The time is: " + new java.util.Date());
			Thread.sleep(20000);
		}
	}	
	
	private static InitialContext getInitialContext() throws NamingException {
		Hashtable<String,String> env = new Hashtable<String,String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, QueueProperties.JNDI_FACTORY);
		env.put(Context.PROVIDER_URL, QueueProperties.SERVER);
		env.put(Context.SECURITY_PRINCIPAL, "myqueueuser");
		env.put(Context.SECURITY_CREDENTIALS, "myqueueuser1");		
		return new InitialContext(env);
	}
	
	public static void main(String[] args) throws Exception{
		InitialContext initialContext = getInitialContext();
		QueuePoster queuePoster = new QueuePoster();
		queuePoster.init(initialContext, QueueProperties.QUEUE);
		//sendToServer(queuePoster);
		autoSend(queuePoster);
		queuePoster.close();
	}
}
