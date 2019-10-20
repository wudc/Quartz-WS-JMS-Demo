package com.cjet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cjet.jms.producer.Sender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringJmsWeblogicQueueApplicationTests {

	@Autowired
	private Sender sender;

//	@Test
//	public void contextLoads() {
//	}

	@Test
	public void testReceive() throws Exception {
		sender.send("Hello Spring JMS ActiveMQ!");

	}
}
