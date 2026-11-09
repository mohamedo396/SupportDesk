package com.company.supportdesk.dto;

import java.time.Instant;
import java.util.UUID;

public record CommentResponse(

        UUID id,
        UUID ticketId,
        UUID authorUserId,
        String authorName,
        String content,
        Instant createdAt

) {}