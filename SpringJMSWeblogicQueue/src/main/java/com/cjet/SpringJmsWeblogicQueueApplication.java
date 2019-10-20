package com.cjet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cjet.jms.producer.Sender;

@SpringBootApplication
public class SpringJmsWeblogicQueueApplication implements CommandLineRunner {

	@Autowired
	private Sender sender;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJmsWeblogicQueueApplication.class, args);
		
	}

	@Override
    public void run(String... args) {
		sender.send("Greeting David Wu. From Spring Boot to Weblogic JMS Queue.");
    }	
}
