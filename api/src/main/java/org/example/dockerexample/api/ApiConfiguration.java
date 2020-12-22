package org.example.dockerexample.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
