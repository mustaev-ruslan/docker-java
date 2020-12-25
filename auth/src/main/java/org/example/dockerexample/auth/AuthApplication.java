package org.example.dockerexample.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
public class AuthApplication {

    @Value("${auth.server.host}")
    private String host;
    @Value("${auth.server.port}")
    private String port;
    @Value("${auth.db-url}")
    private String dbUrl;

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @EventListener
    public void onStartup(ApplicationReadyEvent event) {
        log.info("Started auth service on " + host + ":" + port);
        log.info("Database url " + dbUrl);
    }

}
