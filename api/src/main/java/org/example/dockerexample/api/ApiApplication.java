package org.example.dockerexample.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@RestController
@Slf4j
@RequiredArgsConstructor
public class ApiApplication {

    private final PostRepository postRepository;

    private final RestTemplate restTemplate;

    @Value("${api.server.host}")
    private String host;
    @Value("${api.server.port}")
    private String port;
    @Value("${api.db-url}")
    private String dbUrl;
    @Value("${api.auth-api-url}")
    private String authApiUrl;

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @GetMapping("/test")
    public String test() {
        return "Hello from api service on " + host + ":" + port;
    }

    @GetMapping("/add-post")
    public String addPost() {
        Post savedPost = postRepository.save(new Post("hello"));
        return savedPost.toString();
    }

    @GetMapping("/show-posts")
    public String showPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(Post::toString)
                .reduce("", (a, b) -> a + "<br>" + b);
    }

    @GetMapping("/test-with-current-user")
    public CurrentUserDto testWithCurrentUser() {
        UserDto userDto = restTemplate.getForObject(authApiUrl + "/current-user", UserDto.class);
        return new CurrentUserDto(true, userDto);
    }

    @GetMapping("/api/test-api-data")
    public ApiDataDto testApiData() {
        return new ApiDataDto(true);
    }

    @EventListener
    public void onStartup(ApplicationReadyEvent event) {
        log.info("Started api service on " + host + ":" + port);
        log.info("Database url " + dbUrl);
    }

}
