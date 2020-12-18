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

import java.util.List;

@SpringBootApplication
@RestController
@Slf4j
@RequiredArgsConstructor
public class ApiApplication {

    @Value("${api.server.host}")
    private String host;

    @Value("${api.server.port}")
    private String port;

    @Value("${api.server.db-url}")
    private String dbUrl;

    private final PostRepository postRepository;

    @GetMapping("/test")
    public String test() {
        return "Hello from api service on " + host + ":" + port;
    }

    @GetMapping("/addPost")
    public String addPost() {
        Post savedPost = postRepository.save(new Post("hello"));
        return savedPost.toString();
    }

    @GetMapping("/showPosts")
    public String showPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(Post::toString)
                .reduce("", (a, b) -> a + "<br>" + b);
    }

    @EventListener
    public void onStartup(ApplicationReadyEvent event) {
        log.info("Started api service on " + host + ":" + port);
        log.info("Database url " + dbUrl);
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
