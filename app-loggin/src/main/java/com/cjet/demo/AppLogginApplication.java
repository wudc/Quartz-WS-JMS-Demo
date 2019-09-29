package com.cjet.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cjet.demo.app.DemoApp;

@SpringBootApplication
public class AppLogginApplication {
	private static final Logger LOGGER = LogManager.getLogger(AppLogginApplication.class);

	public static void main(String[] args) {
		
		SpringApplication.run(AppLogginApplication.class, args);
		
		LOGGER.info("Info level log message");
        LOGGER.debug("Debug level log message");
        LOGGER.fatal("A FATAL ERROR Error Error ............");
        LOGGER.error("Error level log message");
        LOGGER.trace("A TRACE Message");
        LOGGER.warn("A WARN Message");
        
        DemoApp app = new DemoApp();
	}

}
