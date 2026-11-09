package com.company.supportdesk.model;

import java.time.Instant;
import java.util.UUID;

public class TicketComment {

    private final UUID id;
    private final UUID ticketId;
    private final UUID authorUserId;
    private final String content;
    private final Instant createdAt;

    public TicketComment(
            UUID id,
            UUID ticketId,
            UUID authorUserId,
            String content,
            Instant createdAt
    ) {
        this.id = id;
        this.ticketId = ticketId;
        this.authorUserId = authorUserId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public UUID getAuthorUserId() {
        return authorUserId;
    }

    public String getContent() {
        return content;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}