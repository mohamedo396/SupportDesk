package com.company.supportdesk.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AssignTicketRequest(

        @NotNull(message = "Assignee ID is required")
        UUID assigneeId

) {}