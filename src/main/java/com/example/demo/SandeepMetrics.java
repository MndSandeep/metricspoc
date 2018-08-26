package com.example.demo;

import static java.util.Collections.emptyList;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.binder.MeterBinder;
@Component
public class SandeepMetrics implements MeterBinder {
	
	private final Iterable<Tag> tags;

    public SandeepMetrics() {
        this(emptyList());
    }

    public SandeepMetrics(Iterable<Tag> tags) {
        this.tags = tags;
    }

    
	@Override
	public void bindTo(MeterRegistry registry) {
		Timer.builder("mymetric.gc.pause")
        .tags("demo","m1")
        .description("First custom metrics")
        .register(registry)
        .record(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}
}
