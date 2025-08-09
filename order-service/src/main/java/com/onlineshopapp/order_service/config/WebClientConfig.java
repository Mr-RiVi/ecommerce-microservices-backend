package com.onlineshopapp.order_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    @LoadBalanced // Enables client-side load balancing for WebClient.
    // When the order service tries to connect to another service (e.g., inventory or payment)
    // and there are multiple instances registered with Eureka or another discovery server,
    // this annotation helps balance the load and direct the request to one of the available instances.
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }
}
