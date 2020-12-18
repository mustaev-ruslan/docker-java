package org.example.dockerexample.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthApplication {

    @Value("${auth.server.host}")
    private String host;

    @Value("${auth.server.port}")
    private String port;

    @Value("${auth.server.db-url}")
    private String dbUrl;

    @GetMapping("/test")
    public String test() {
        return "Hello from auth service on " + host + ":" + port;
    }

    @EventListener
    public void onStartup(ApplicationReadyEvent event) {
        log.info("Started auth service on " + host + ":" + port);
        log.info("Database url " + dbUrl);
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
