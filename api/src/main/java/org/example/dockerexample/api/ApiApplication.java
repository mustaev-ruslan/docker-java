package org.example.dockerexample.api;

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
public class ApiApplication {

    @Value("${api.server.host}")
    private String host;

    @Value("${api.server.port}")
    private String port;

    @GetMapping("/test")
    public String test() {
        return "Hello from api service on " + host + ":" + port;
    }

    @EventListener
    public void onStartup(ApplicationReadyEvent event) {
        log.info("Started api service on " + host + ":" + port);
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
