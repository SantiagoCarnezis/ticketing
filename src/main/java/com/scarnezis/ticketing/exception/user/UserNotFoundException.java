package com.scarnezis.ticketing.exception.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String email) {
        super("user " + email + " was not found");
    }
}
