package com.company.supportdesk.repository;

import com.company.supportdesk.model.TicketComment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Comparator;

@Repository
public class TicketCommentRepository {

    private final ConcurrentHashMap<UUID, TicketComment> storage =
            new ConcurrentHashMap<>();

    // Save a comment
    public TicketComment save(TicketComment comment) {

        storage.put(comment.getId(), comment);

        return comment;
    }

    // Find all comments belonging to a ticket
    public List<TicketComment> findByTicketId(UUID ticketId) {

        return storage.values()
                .stream()
                .filter(comment ->
                        comment.getTicketId().equals(ticketId)
                )
                 .sorted(Comparator.comparing(TicketComment::getCreatedAt))
                .toList();
    }
}