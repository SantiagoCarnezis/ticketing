package com.scarnezis.ticketing.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "purchase_option")
public class PurchaseOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String name;
    private String description;
    private float price;
    private int amountTickets;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_id")
    private List<Ticket> tickets;

    public void addAllTickets(List<Ticket> newTickets) {
        tickets.addAll(newTickets);
    }
}
