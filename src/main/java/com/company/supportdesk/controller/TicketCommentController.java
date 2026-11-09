package com.company.supportdesk.controller;

import com.company.supportdesk.dto.CommentResponse;
import com.company.supportdesk.dto.CreateCommentRequest;
import com.company.supportdesk.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tickets/{ticketId}/comments")
public class TicketCommentController {

    private final TicketService ticketService;

    public TicketCommentController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // CREATE COMMENT
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable UUID ticketId,
            @Valid @RequestBody CreateCommentRequest request
    ) {

        CommentResponse response =
                ticketService.createComment(ticketId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET COMMENTS
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getComments(
            @PathVariable UUID ticketId
    ) {

        List<CommentResponse> comments =
                ticketService.getComments(ticketId);

        return ResponseEntity.ok(comments);
    }
}