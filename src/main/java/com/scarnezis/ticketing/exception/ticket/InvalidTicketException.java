package com.scarnezis.ticketing.exception.ticket;

public class InvalidTicketException extends RuntimeException {

    public InvalidTicketException(Long ticketCode) {
        super("ticket " + ticketCode + " is invalid");
    }
}
