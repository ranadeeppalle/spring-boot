package com.springcloud.practice.rest.controller;

import com.springcloud.practice.rest.model.Post;
import com.springcloud.practice.rest.model.User;
import com.springcloud.practice.rest.service.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userDAO.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        final User user = userDAO.findOne(id);
        if(user == null)
            throw new UserNotFoundException(id +"not found");
        EntityModel<User> resource = new EntityModel<User>(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        // ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable int id){
        final User user = userDAO.deleteById(id);
        if(user == null)
            throw new UserNotFoundException(id +" not found");
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user){
        userDAO.save(user);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable int id){
        final User user = userDAO.findOne(id);
        if(user == null) throw  new UserNotFoundException("id-"+id);
        return user.getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){
        final User user = userDAO.findOne(id);
        if(user == null) throw  new UserNotFoundException("id-"+id);
        userDAO.savePost(user, post);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    /*@GetMapping("/hello")
    public String hello(@RequestHeader(name="Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("good.morning.message", null,locale);
    }*/
    @GetMapping("/hello")
    public String hello(){
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }
}
