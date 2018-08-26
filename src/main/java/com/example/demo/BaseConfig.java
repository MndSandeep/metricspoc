package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@Configuration 
public class BaseConfig {
	
	@Bean
	public JvmThreadMetrics jvmThreadMetrics() {
		return new JvmThreadMetrics();
	}
	
	@Bean
	public SimpleMeterRegistry simpleMeterRegistry() {
		return new SimpleMeterRegistry();
	}

}
