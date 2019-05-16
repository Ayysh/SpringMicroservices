package com.microservice.currencyconversionservice;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//-- Manual Configuration  for Ribbon - Load Balancer
@Configuration
public class LocalRibbonClientConfiguration {

    // -- I am creating a configuration bean . Please use this one and manage this. @Component is defined at class level. An it cannot be used for manual configuration.
    // @Bean is used for manual configuration along with @Configuration
    @Bean
    public ServerList<Server> ribbonServerList() {
        return new StaticServerList<>((new Server("localhost", 8001)), new Server("localhost", 8000));
    }
}