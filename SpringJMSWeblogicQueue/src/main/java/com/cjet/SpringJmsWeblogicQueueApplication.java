package com.cjet;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.cjet.jms.producer.Sender;

@SpringBootApplication
public class SpringJmsWeblogicQueueApplication implements CommandLineRunner {

	@Autowired
	private Sender sender;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJmsWeblogicQueueApplication.class, args);
		
	}

	@Override
    public void run(String... args) {
		sender.send("Greeting David Wu. From Spring Boot to Weblogic JMS Queue.");
		
		Resource resource = resourceLoader.getResource("classpath:create_text.xml");
		InputStream stream = resource.getInputStream();
		
		String text = null;
		try (
			Scanner scanner = new Scanner(stream, StandardCharsets.UTF_8.name())) {
			text = scanner.useDelimiter("\\A").next();
		}
		
		System.out.println("run: " + text);
		sender.sendXMLFile(text);
    }	
}
