package com.scarnezis.ticketing.exception.event;

public class CapacityExceededException extends RuntimeException {

    public CapacityExceededException() {
        super("There are no more entries left for the option ");
    }
}
