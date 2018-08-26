package com.example.demo;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;

@Timed("sandeep.metrics.controller")
@RestController
public class FirstController {
	
	@Autowired
	CustomExecutorService cs;

	@RequestMapping("/sample")
	public String getSampleData() {
		cs.testCounter();
		return "Collected Metrics";
	}
	
	@RequestMapping("/exceptionMetrics")
	public ResponseEntity<Object> getSampleDataException() {
		return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping("/errorMetrics")
	public ResponseEntity<Object> getError() {
		throw new RuntimeException();
	}
	
}
