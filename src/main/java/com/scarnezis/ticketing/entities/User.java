package com.scarnezis.ticketing.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String phone;
    private String address;
    private String password;
    private LocalDate dateCreated;
    private boolean enableNotifications;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private List<Purchase> purchases;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "saved_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "saved_event_id")
    )
    private List<Event> savedEvents;

    public void saveEvent(Event event) {
        savedEvents.add(event);
    }
    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }
}
