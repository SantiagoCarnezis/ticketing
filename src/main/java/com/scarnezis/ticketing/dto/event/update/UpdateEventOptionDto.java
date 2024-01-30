package com.scarnezis.ticketing.dto.event.update;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateEventOptionDto {

    private Long code;
    private String name;
    private String description;
    private float price;
    private Integer maxPurchasesCapacity;
    private int amountTickets;
    private LocalDate expiredTime;
    private boolean enable;
}
