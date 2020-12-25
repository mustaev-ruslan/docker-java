package org.example.dockerexample.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.dockerexample.api.dto.ApiDataDto;
import org.example.dockerexample.api.dto.CurrentUserDto;
import org.example.dockerexample.api.dto.Post;
import org.example.dockerexample.api.dto.UserDto;
import org.example.dockerexample.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final PostRepository postRepository;

    private final RestTemplate restTemplate;

    @Value("${api.server.host}")
    private String host;
    @Value("${api.server.port}")
    private String port;
    @Value("${api.auth-api-url}")
    private String authApiUrl;

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

    @GetMapping("/test-api-data")
    public ApiDataDto testApiData() {
        return new ApiDataDto(true);
    }

}
