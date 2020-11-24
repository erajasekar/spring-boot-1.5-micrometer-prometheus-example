package com.techprimers.micrometer.micrometerspringboot15.service;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
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
                .baseUnit("req/sec")
                .register(meterRegistry);
        return timer;
    }
}
