package com.scarnezis.ticketing.exception.event;

public class OptionNotFoundException extends RuntimeException {

    public OptionNotFoundException(String optionName) {
        super("option " + optionName + " was not found");
    }
}
