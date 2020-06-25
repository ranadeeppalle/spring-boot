package com.springcloud.practice.rest.service;

import com.springcloud.practice.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDAO {
    private static List<User> users = new ArrayList<>();
    private static int userCount = 3;
    static {
        users.add(new User(1,"Adam",new Date()));
        users.add(new User(2,"Eve",new Date()));
        users.add(new User(3,"Jack",new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId() == null)
            user.setId(++userCount);
        users.add(user);
        return user;
    }

    public User findOne(int id){
        final Optional<User> user = users.stream().filter(u -> u.getId().equals(id)).findFirst();
        return user.isPresent() ?  user.get() : null;
    }

    public User deleteById(int id){
        final Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            final User user = iterator.next();
            if(user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
