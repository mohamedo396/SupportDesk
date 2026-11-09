package com.company.supportdesk.model;

import java.time.Instant;
import java.util.UUID;

public class Ticket{
    private final UUID id;
    private final String title;
    private final String description;
    private final String customerEmail;
    private final TicketPriority priority;
    private final TicketStatus status;
    private final Instant createdAt;


    public  Ticket(
        UUID id,
        String title,
        String description,
        String customerEmail,
        TicketPriority priority,
        TicketStatus status,
        Instant createdAt

    ){
        this.id=id;
        this.title=title;
        this.description=description;
        this.customerEmail=customerEmail;
        this.priority=priority;
        this.status=status;
        this.createdAt=createdAt;
    }


    public UUID getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getCustomerEmail(){
        return customerEmail;
    }

    public TicketPriority getPriority(){
        return priority;
    }

    public TicketStatus getStatus(){
        return status;
    }

    public Instant getCreatedAt(){
        return createdAt;
    }


}