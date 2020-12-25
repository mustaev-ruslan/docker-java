package org.example.dockerexample.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@SpringBootApplication
public class ApiApplication {

    @Value("${api.server.host}")
    private String host;
    @Value("${api.server.port}")
    private String port;
    @Value("${api.db-url}")
    private String dbUrl;

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @EventListener
    public void onStartup(ApplicationReadyEvent event) {
        log.info("Started api service on " + host + ":" + port);
        log.info("Database url " + dbUrl);
    }

}
