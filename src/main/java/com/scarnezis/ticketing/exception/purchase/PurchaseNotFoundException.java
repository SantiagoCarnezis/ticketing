package com.scarnezis.ticketing.exception.purchase;

public class PurchaseNotFoundException extends RuntimeException {

    public PurchaseNotFoundException(String code) {
        super("purchase was not found");
    }
}
