package com.springcloud.practice.rest.service;

import com.springcloud.practice.rest.model.Post;
import com.springcloud.practice.rest.model.User;
import com.springcloud.practice.rest.repo.PostRepository;
import com.springcloud.practice.rest.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDAO {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User user){
        userRepository.save(user);
        return user;
    }

    public Post savePost(User user, Post post){
        post.setUser(user);
        postRepository.save(post);
        return post;
    }

    public User findOne(int id){
        final Optional<User> user = userRepository.findById(id);
        return user.isPresent() ?  user.get() : null;
    }

    public User deleteById(int id){
        final Optional<User> user = userRepository.findById(id);
            if(user.isPresent()) {
                userRepository.deleteById(id);
                return user.get();
            }
        return null;
    }
}
