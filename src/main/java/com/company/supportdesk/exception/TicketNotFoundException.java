package com.company.supportdesk.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String message){
        super(message);
    }
    
}
