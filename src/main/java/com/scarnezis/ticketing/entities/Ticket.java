package com.scarnezis.ticketing.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String qr;
    @ManyToOne
    private Event event;
    private String eventOptionName;
    private String eventOptionDescription;
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "buyer_name")),
            @AttributeOverride(name = "email", column = @Column(name = "buyer_email")),
            @AttributeOverride(name = "phone", column = @Column(name = "buyer_phone")),
            @AttributeOverride(name = "address", column = @Column(name = "buyer_address"))
    })
    @Embedded
    private Buyer buyer;
    private boolean enabled;
}
