package com.techprimers.micrometer.micrometerspringboot15.controller;

import com.techprimers.micrometer.micrometerspringboot15.service.MetricService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class HelloController {

    @Autowired
    private MetricService metricService;


    @Timed(
            value = "techprimers.hello.request",
            histogram = true,
            percentiles = {0.95, 0.99},
            extraTags = {"version", "1.0"}
    )
    @GetMapping("/hello")
    public String hello() {

       // metricService.meter("techprimers.hello.meter").record(3);
        metricService.counter("techprimers.hello.counter").increment(1);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello Youtube";
    }

   /* @Timed(
            value = "techprimers.hello2.request",
            histogram = true,
            percentiles = {0.95, 0.99},
            extraTags = {"version", "1.0"}
    )*/
    @GetMapping("/hello2")
    public String hello2() {
        Timer.Sample timer = Timer.start(metricService.registry());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.stop(metricService.timer("techprimers.hello3.request"));
        return "Hello Youtube2";
    }
   /* @Timed(
            value = "techprimers.hello2.request",
            histogram = true,
            percentiles = {0.95, 0.99},
            extraTags = {"version", "1.0"}
    )*/

    @GetMapping("/hello3")
    public String hello3() {
        return "Hello Youtube";
    }
    @Timed(
            value = "techprimers.hello2.request",
            histogram = true,
            percentiles = {0.95, 0.99},
            extraTags = {"version", "1.0"}
    )
    @GetMapping("/hello4")
    public String hello4() {
        return "Hello Youtube";
    }
}
