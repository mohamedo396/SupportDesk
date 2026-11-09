package com.company.supportdesk.model;

import java.util.UUID;

public class User {
    private final UUID id;
    private final String name;
    private final String email;
    private final UserRole role;

    public User(UUID id, String name, String email, UserRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public UUID getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public UserRole getRole(){
        return role;
    }
}
