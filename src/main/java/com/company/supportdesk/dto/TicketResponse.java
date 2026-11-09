package com.company.supportdesk.dto;

import com.company.supportdesk.model.TicketPriority;
import com.company.supportdesk.model.TicketStatus;

import java.time.Instant;
import java.util.UUID;


public record TicketResponse(

    UUID id,
    String title,
    String description,
    String customerEmail,
    TicketPriority priority,
    TicketStatus status,
    Instant createdAt

) {}