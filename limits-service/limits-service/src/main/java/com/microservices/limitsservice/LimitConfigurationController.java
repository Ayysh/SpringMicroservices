package com.microservices.limitsservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LimitConfigurationController {

    @Autowired
    private LimitConfiguration configuration;

    @GetMapping("/limits")
    public @ResponseBody
    LimitConfiguration retrieveLimitsFromConfig() {
        return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }

    @GetMapping("/fault-tolerance-ex")
    @HystrixCommand(fallbackMethod = "fallbackRetrieveConfiguration")
    public @ResponseBody LimitConfiguration retrieveConfiguration() {
        throw new RuntimeException("Not available");
    }

    public LimitConfiguration fallbackRetrieveConfiguration() {
        return new LimitConfiguration(9, 999);
    }
}
