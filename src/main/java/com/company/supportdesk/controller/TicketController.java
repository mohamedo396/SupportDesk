package com.company.supportdesk.controller;

import com.company.supportdesk.dto.AssignTicketRequest;
import com.company.supportdesk.dto.CreateTicketRequest;
import com.company.supportdesk.dto.TicketResponse;
import com.company.supportdesk.service.TicketService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(
            @Valid @RequestBody CreateTicketRequest request) {

        TicketResponse response =
                ticketService.createTicket(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping 
    public ResponseEntity<List<TicketResponse>> getAllTickets(){
        List<TicketResponse> tickets= ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable UUID id){
        TicketResponse ticket=ticketService.getTicketById(id);
        return ResponseEntity.ok(ticket);

    }

    //assign ticket to engineer

    @PatchMapping("/{id}/assignee")
    public ResponseEntity<TicketResponse> assignTicket(
            @PathVariable UUID id,
            @Valid @RequestBody AssignTicketRequest request
    ) {

        TicketResponse response = ticketService.assignTicket(
                id,
                request.assigneeId()
        );

        return ResponseEntity.ok(response);
    }


    
    
}