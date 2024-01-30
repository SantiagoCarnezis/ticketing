package com.scarnezis.ticketing.dto.event.neww;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NewEventOptionDto {

    private String name;
    private String description;
    private float price;
    private Integer maxPurchasesCapacity;
    private int amountTickets;
    private LocalDate expiredTime;
}
