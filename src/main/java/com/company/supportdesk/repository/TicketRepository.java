package com.company.supportdesk.repository;

import org.springframework.stereotype.Repository;

import com.company.supportdesk.model.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository 
public class TicketRepository {
    private final ConcurrentHashMap<UUID, Ticket> storage = new ConcurrentHashMap<>();

    public Ticket save(Ticket ticket){
        storage.put(ticket.getId(),ticket);
        return ticket;
    }

    public Optional<Ticket> findById(UUID id){
        return Optional.ofNullable(storage.get(id));
    }

    public List<Ticket> findAll(){
        return new ArrayList<>(storage.values());
    }
}
