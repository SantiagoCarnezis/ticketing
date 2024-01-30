package com.scarnezis.ticketing.exception.event;

public class InsufficientTicketsException extends RuntimeException {

    public InsufficientTicketsException(String name) {
        super("Insufficient tickets for " + name);
    }
}
