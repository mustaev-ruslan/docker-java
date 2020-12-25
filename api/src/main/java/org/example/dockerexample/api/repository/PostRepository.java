package org.example.dockerexample.api.repository;

import org.example.dockerexample.api.dto.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}