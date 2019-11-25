package com.cjet.jms.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan ({"com.cjet.jms.config"})
public class SpringJmsWeblogicDemoApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJmsWeblogicDemoApplication.class, args);
	}

	@Override
    public void run(String... args) {
		//sender.send("Greeting David Wu. From Spring Boot to Weblogic JMS Queue.");		
	}
}
