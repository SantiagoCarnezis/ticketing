package com.scarnezis.ticketing.exception.event;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(String eventId) {
        super("event " + eventId + " was not found");
    }
}
