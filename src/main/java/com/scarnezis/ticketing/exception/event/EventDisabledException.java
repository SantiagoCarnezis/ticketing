package com.scarnezis.ticketing.exception.event;

public class EventDisabledException extends RuntimeException {

    public EventDisabledException() {super("event no longer enabled");}
}
