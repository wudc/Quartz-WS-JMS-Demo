package com.cjet.jms.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class QueueConsumer implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			String msgText;
			if (message instanceof TextMessage) {
				msgText = ((TextMessage)message).getText();
			}
			else {
				msgText = message.toString();
			}
			System.out.println("Message received: " + msgText);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
