package com.scarnezis.ticketing.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Accreditor {

    @Id
    private String dni;
    private String name;
    @ManyToOne
    private Event event;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id")
    private List<Ticket> validatedTickets;

    public void addTicket(Ticket ticket) {
        validatedTickets.add(ticket);
    }
}
