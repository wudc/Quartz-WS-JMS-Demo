package com.cjet.demo.app;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class DemoApp {
	public DemoApp(){
		Logger logger = LogManager.getLogger(DemoApp.class);
		logger.trace("COM :: cjet :: demo :: LEVEL :: DemoApp TRACE Message ::");
		logger.info("COM :: cjet :: demo :: LEVEL :: DemoApp INFO Message ::");
		logger.debug("COM :: cjet :: demo :: LEVEL :: DemoApp DEBUG Message ::");
		logger.error("COM :: cjet :: demo :: LEVEL :: DemoApp ERROR Message ::");
		logger.warn("COM :: cjet :: demo :: LEVEL :: DemoApp WARN Message ::");

	}
}