package com.scarnezis.ticketing.exception.user;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String email) {
        super("user " + email + " already exists");
    }
}
