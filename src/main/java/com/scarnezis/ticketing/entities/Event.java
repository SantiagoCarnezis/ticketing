package com.scarnezis.ticketing.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    //@JoinColumn(name="owner_id", nullable=false)
    private User owner;
    private Integer maxCapacity;
    private Integer currentCapacity;
    private String name;
    private String description;
    private EventType type;
    private String location;
    private boolean enabled;
    private LocalDate date;
    private LocalDate submittedDate;
    private LocalDate expiredDate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private List<EventOption> eventOptions; //valor de la entrada normal, promos, etc
}
