package com.cjet.jms.producer;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.jms.core.JmsTemplate;

public class TestSender {

	@Test
	public void SenderTest() {
		JmsTemplate jmsTemplate = mock(JmsTemplate.class);
		String message = "test";
		doNothing().when(jmsTemplate).convertAndSend("jms/MyJMSQueue", message);
		Sender sender = new Sender();
		sender.setJmsTemplate(jmsTemplate);
		
		sender.send(message);
		verify(jmsTemplate).convertAndSend("jms/MyJMSQueue", message);
	}
	
	@Test
	public void SenderMessageCaptureTest() {
		ArgumentCaptor<String> argumentResource = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> argumentMessage = ArgumentCaptor.forClass(String.class);
		JmsTemplate jmsTemplate = mock(JmsTemplate.class);
		String message = "test";
		doNothing().when(jmsTemplate).convertAndSend("jms/MyJMSQueue", message);
		Sender sender = new Sender();
		sender.setJmsTemplate(jmsTemplate);
		
		sender.send(message);
		verify(jmsTemplate).convertAndSend(argumentResource.capture(), argumentMessage.capture());
		
		assertEquals("jms/MyJMSQueue", argumentResource.getValue());
		assertEquals("test", argumentMessage.getValue());
	}
	
	@Test
	public void SenderBDDTest() {
		JmsTemplate jmsTemplate = mock(JmsTemplate.class);
		String message = "test";
		
		//given
		willDoNothing().given(jmsTemplate).convertAndSend("jms/MyJMSQueue", message);
		
		//when
		Sender sender = new Sender();
		sender.setJmsTemplate(jmsTemplate);
		sender.send(message);
		
		//then
		verify(jmsTemplate).convertAndSend("jms/MyJMSQueue", message);		
	}
}
