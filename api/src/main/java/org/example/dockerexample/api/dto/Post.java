package org.example.dockerexample.api.dto;

import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString
public class Post {

    @Id
    String id;

    String name;

    public Post(String name) {
        this.name = name;
    }
}
