package com.scarnezis.ticketing.exception.acreditador;

public class AccreditorAlreadyExistsException extends RuntimeException {

    public AccreditorAlreadyExistsException(String dni) {
        super("accreditor " + dni + " already exists");
    }
}
