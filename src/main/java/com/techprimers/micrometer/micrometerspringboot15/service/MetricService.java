package com.techprimers.micrometer.micrometerspringboot15.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricService {

    private final MeterRegistry meterRegistry;

    @Autowired
    public MetricService(MeterRegistry meterRegistry){
        this.meterRegistry = meterRegistry;
    }

    public DistributionSummary meter(String name) {
        DistributionSummary timer = DistributionSummary.builder(name)
                .baseUnit("req/min")
                .register(meterRegistry);
        return timer;
    }

    public Counter counter(String name) {
        Counter counter = Counter.builder(name)
                .register(meterRegistry);
        return counter;
    }

    public Timer timer(String name) {
        Timer timer = Timer.builder(name).publishPercentileHistogram().publishPercentiles(0.4,0.6).register(meterRegistry);
        return timer;
    }

    public MeterRegistry registry() {
        return meterRegistry;
    }
}
