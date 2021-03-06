package com.cjet.payments.demo.controller;

import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cjet.quartz.demo.model.ScheduleRequest;
import com.cjet.quartz.demo.model.ScheduleResponse;
import com.cjet.quartz.demo.service.SchedulerService;

@RestController
public class SchedulerController {

	@Autowired
	SchedulerService schedulerService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/schedule", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ScheduleResponse schedule(@RequestBody ScheduleRequest details) throws Exception {

		Class<?> jobClass = Class.forName(details.getJobClass());
		schedulerService.scheduleJob((Class<? extends Job>) jobClass, details.getJobName(), details.getJobGroup(),
				details.getCronExpression());
		return new ScheduleResponse("Successfully scheduled " + details.getJobName());
	}

}
