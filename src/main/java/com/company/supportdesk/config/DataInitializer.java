package com.company.supportdesk.config;

import com.company.supportdesk.model.User;
import com.company.supportdesk.model.UserRole;
import com.company.supportdesk.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {

        User engineer1 = new User(
                UUID.randomUUID(),
                "Sarah Johnson",
                "sarah@company.com",
                UserRole.BACKEND_ENGINEER
        );

        User engineer2 = new User(
                UUID.randomUUID(),
                "Michael Smith",
                "michael@company.com",
                UserRole.BACKEND_ENGINEER
        );

        User supportAgent = new User(
                UUID.randomUUID(),
                "John Brown",
                "john@company.com",
                UserRole.SUPPORT_AGENT
        );

        userRepository.save(engineer1);
        userRepository.save(engineer2);
        userRepository.save(supportAgent);

        System.out.println("Sample users initialized successfully.");
    }
}