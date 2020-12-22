package org.example.dockerexample.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AuthConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
