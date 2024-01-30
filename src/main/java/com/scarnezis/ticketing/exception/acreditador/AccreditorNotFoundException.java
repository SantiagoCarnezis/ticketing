package com.scarnezis.ticketing.exception.acreditador;

public class AccreditorNotFoundException extends RuntimeException {

    public AccreditorNotFoundException(String dni) {
        super("accreditor " + dni + " was not found");
    }
}
