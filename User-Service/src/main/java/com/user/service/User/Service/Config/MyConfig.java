package com.user.service.User.Service.Config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    @Bean
    @LoadBalanced //otherwise getting error
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
