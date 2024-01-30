package com.scarnezis.ticketing.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String paymentMethod;
    @ManyToOne
    private Event event;
    //@ElementCollection
    //@CollectionTable(name = "purchase_options")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_id")
    private List<PurchaseOption> options;

    public void addOption(PurchaseOption purchaseOption) {
        options.add(purchaseOption);
    }
}
