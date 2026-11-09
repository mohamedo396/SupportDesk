package com.company.supportdesk.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.company.supportdesk.model.User;

@Repository 
public class UserRepository {
    private final ConcurrentHashMap<UUID,User> storage=new ConcurrentHashMap<>();

    //save a user
    public User save(User user){
        storage.put(user.getId(),user);
        return user;
    }

    //find a user by id
    public Optional<User> findById(UUID id){
        return Optional.ofNullable(storage.get(id));
    }

    //retrieve all users

    public List<User> findAll(){
        return new ArrayList<>(storage.values());
    }
}
