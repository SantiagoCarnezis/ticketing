package com.scarnezis.ticketing.dto.purchase;

import com.scarnezis.ticketing.entities.Event;
import com.scarnezis.ticketing.entities.EventOption;

import java.util.List;

public class PurchaseDto {

    private Event event;
    private List<EventOption> selectedOption;
    private String paymentMethod;
}
