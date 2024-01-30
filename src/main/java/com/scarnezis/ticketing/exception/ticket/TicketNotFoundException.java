package com.scarnezis.ticketing.exception.ticket;

public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException(Long code) {
        super("ticket " + code + " was not found");
    }
}
