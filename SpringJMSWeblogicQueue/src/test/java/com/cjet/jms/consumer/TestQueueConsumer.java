package com.cjet.jms.consumer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.junit.Test;

public class TestQueueConsuer {
	@Test
	public void queueConsumerTest() throws JMSException {
		TextMessage message = mock(TextMessage.class);
		String msgText = "test";
		
		when(message.getText()).thenReturn(msgText);
		
		QueueConsumer consumer = new QueueConsumer();
		consumer.onMessage(message);
		
		verify(message).getText();
		assertEquals("test", consumer.getMsgText());
	}
	
	@Test
	public void queueConsumerFailTest() throws JMSException {
		TextMessage message = mock(TextMessage.class);
		Message jmsMessage = mock(Message.class);
		String msgText = "test";
		
		when(message.getText()).thenReturn(msgText);
		
		QueueConsumer consumer = new QueueConsumer();
		consumer.onMessage(jmsMessage);
		
		//verify the message.getText was never call because the JMS Message type is not TextMessage type
		verify(message, never()).getText();
	}
}
