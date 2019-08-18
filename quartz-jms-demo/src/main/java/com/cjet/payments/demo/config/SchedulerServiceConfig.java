package com.cjet.payments.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cjet.quartz.demo.service.SchedulerService;

@Configuration
public class SchedulerServiceConfig {
	
	@Bean
    public SchedulerService createSchedulerService() {
        return new SchedulerService();
    }
}
