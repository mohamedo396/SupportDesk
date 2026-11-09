package com.company.supportdesk.dto;

import com.company.supportdesk.model.TicketPriority;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record CreateTicketRequest(

    @NotBlank(message = "Title is required")
    @Size(max = 120, message = "Title cannot exceed 120 characters")
    String title,

    @NotBlank(message = "Description is required")
    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    String description,

    @NotBlank(message = "Customer email is required")
    @Email(message = "Please provide a valid email address")
    String customerEmail,

    @NotNull(message = "Priority is required")
    TicketPriority priority

) {}