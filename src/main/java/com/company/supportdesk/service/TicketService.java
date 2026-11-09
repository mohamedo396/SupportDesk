package com.company.supportdesk.service;

import com.company.supportdesk.dto.CreateTicketRequest;
import com.company.supportdesk.dto.TicketResponse;
import com.company.supportdesk.exception.TicketNotFoundException;
import com.company.supportdesk.model.Ticket;
import com.company.supportdesk.model.TicketStatus;
import com.company.supportdesk.repository.TicketRepository;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public TicketResponse createTicket(CreateTicketRequest request) {

        // 1. Generate a unique ticket ID
        UUID id = UUID.randomUUID();

        // 2. Create the ticket using validated client data
        Ticket ticket = new Ticket(
                id,
                request.title(),
                request.description(),
                request.customerEmail(),
                request.priority(),
                TicketStatus.OPEN,
                Instant.now()
        );

        // 3. Save the ticket
        Ticket savedTicket = ticketRepository.save(ticket);

        // 4. Convert the model into a response DTO
        return toResponse(savedTicket);
    }


    private TicketResponse toResponse(Ticket ticket){
        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getCustomerEmail(),
                ticket.getPriority(),
                ticket.getStatus(),
                ticket.getCreatedAt()
        );
    }

    public List<TicketResponse> getAllTickets(){
        return ticketRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public TicketResponse getTicketById(UUID id){
        Ticket ticket=ticketRepository.findById(id)
                .orElseThrow(()->new TicketNotFoundException(
                    "Ticket not fount with ID: "+id
                ));
        return toResponse(ticket);
    }

   
}