package com.springcloud.practice.rest.repo;

import com.springcloud.practice.rest.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
