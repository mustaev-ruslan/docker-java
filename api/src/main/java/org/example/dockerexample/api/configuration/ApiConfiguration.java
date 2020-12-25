package org.example.dockerexample.api.configuration;

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
