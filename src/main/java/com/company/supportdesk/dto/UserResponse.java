package com.company.supportdesk.dto;

import com.company.supportdesk.model.UserRole;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        UserRole role
) {}