package org.example.dockerexample.auth.controller;

import lombok.RequiredArgsConstructor;
import org.example.dockerexample.auth.dto.ApiDataDto;
import org.example.dockerexample.auth.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final RestTemplate restTemplate;

    @Value("${auth.server.host}")
    private String host;

    @Value("${auth.server.port}")
    private String port;

    @Value("${auth.api-url}")
    private String apiUrl;

    @GetMapping("/test")
    public String test() {
        return "Hello from auth service on " + host + ":" + port;
    }

    @GetMapping("/test-with-api-data")
    public ApiDataDto testWithApiData() {
        return restTemplate.getForObject(apiUrl + "/test-api-data", ApiDataDto.class);
    }

    @GetMapping("/current-user")
    public UserDto currentUser() {
        return new UserDto(1234L, "foo@gmail.com");
    }


}
