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
    private final UUID assignedToUserId;


    public  Ticket(
        UUID id,
        String title,
        String description,
        String customerEmail,
        TicketPriority priority,
        TicketStatus status,
        Instant createdAt,
        UUID assignedToUserId

    ){
        this.id=id;
        this.title=title;
        this.description=description;
        this.customerEmail=customerEmail;
        this.priority=priority;
        this.status=status;
        this.createdAt=createdAt;
        this.assignedToUserId=assignedToUserId;
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

    public UUID getAssignedToUserId(){
        return assignedToUserId;
    }

    public Ticket withAssignee(UUID userId){
        return new Ticket(
            this.id,
            this.title,
            this.description,
            this.customerEmail,
            this.priority,
            this.status,
            this.createdAt,
            userId
        );
    }


}