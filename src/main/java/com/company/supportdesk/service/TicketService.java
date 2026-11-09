package com.company.supportdesk.service;

import com.company.supportdesk.dto.CreateTicketRequest;
import com.company.supportdesk.dto.TicketResponse;
import com.company.supportdesk.exception.InvalidAssignmentException;
import com.company.supportdesk.exception.TicketNotFoundException;
import com.company.supportdesk.exception.UserNotFoundException;
import com.company.supportdesk.model.Ticket;
import com.company.supportdesk.model.TicketStatus;
import com.company.supportdesk.model.User;
import com.company.supportdesk.model.UserRole;
import com.company.supportdesk.repository.TicketRepository;
import com.company.supportdesk.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(
            TicketRepository ticketRepository,
            UserRepository userRepository
    ) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    // CREATE TICKET
    public TicketResponse createTicket(CreateTicketRequest request) {

        UUID id = UUID.randomUUID();

        Ticket ticket = new Ticket(
                id,
                request.title(),
                request.description(),
                request.customerEmail(),
                request.priority(),
                TicketStatus.OPEN,
                Instant.now(),
                null
        );

        Ticket savedTicket = ticketRepository.save(ticket);

        return toResponse(savedTicket);
    }

    // GET ALL TICKETS
    public List<TicketResponse> getAllTickets() {

        return ticketRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // GET TICKET BY ID
    public TicketResponse getTicketById(UUID id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(
                        "Ticket not found with ID: " + id
                ));

        return toResponse(ticket);
    }

    // ASSIGN TICKET TO ENGINEER
    public TicketResponse assignTicket(
            UUID ticketId,
            UUID assigneeId
    ) {

        // STEP 1: Check that the ticket exists

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException(
                        "Ticket not found with ID: " + ticketId
                ));

        // STEP 2: Check that the user exists

        User engineer = userRepository.findById(assigneeId)
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found with ID: " + assigneeId
                ));

        // STEP 3: Check that the user is a backend engineer

        if (engineer.getRole() != UserRole.BACKEND_ENGINEER) {

            throw new InvalidAssignmentException(
                    "Only backend engineers can be assigned tickets."
            );
        }

        // STEP 4: Create a new ticket with the engineer assigned

        Ticket updatedTicket = ticket.withAssignee(engineer.getId());

        // STEP 5: Save the updated ticket

        Ticket savedTicket = ticketRepository.save(updatedTicket);

        // STEP 6: Convert the ticket into a response DTO

        return toResponse(savedTicket);
    }

    // CONVERT TICKET INTO RESPONSE DTO
    private TicketResponse toResponse(Ticket ticket) {

        String assignedToName = null;

        if (ticket.getAssignedToUserId() != null) {

            User assignedUser = userRepository
                    .findById(ticket.getAssignedToUserId())
                    .orElse(null);

            if (assignedUser != null) {
                assignedToName = assignedUser.getName();
            }
        }

        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getCustomerEmail(),
                ticket.getPriority(),
                ticket.getStatus(),
                ticket.getCreatedAt(),
                ticket.getAssignedToUserId(),
                assignedToName
        );
    }
}