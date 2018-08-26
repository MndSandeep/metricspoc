package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.LongTaskTimer;
import io.micrometer.core.instrument.LongTaskTimer.Sample;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@Service
public class CustomExecutorService {

	@Autowired
	SimpleMeterRegistry meterRegistry;
	
	public CustomExecutorService(SimpleMeterRegistry meterRegistry) {
		Counter counter= meterRegistry.counter("object.instances", "customExecuorSrvcie","demo");
		counter.increment();
	}
	
	public void testCounter() {
		Counter counter= Metrics.counter("object.instances", "customExecuorSrvcie","demo");
		counter.increment();
		
		LongTaskTimer longTaskTimer = LongTaskTimer
			.builder("3rdPartyService")
			.register(Metrics.globalRegistry);
		Sample currentTaskId = longTaskTimer.start();
				try {
			Thread.sleep(5000);
				} catch (InterruptedException ignored) { }
				long timeElapsed = currentTaskId.stop();
		longTaskTimer.record(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}); 
		
		SimpleMeterRegistry registry = new SimpleMeterRegistry();
		List<String> list = new ArrayList<>(4);
		list.add("Sandeep");
		Gauge gauge = Gauge
		.builder("cache.size", list, List::size)
		.register(Metrics.globalRegistry);
	}
}
