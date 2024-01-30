package com.scarnezis.ticketing.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name ="event_option")
public class EventOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String name;
    private String description;
    private float price;
    private Integer maxPurchasesCapacity; // la cantidad de compras disponibles
    private int currentCapacity; //cant de tickets disponibles
    private int amountTickets; //por ejemplo un 4x3
    private LocalDate expiredTime;
    //private Integer maxAmountsTickets;
    private boolean enable;
}
