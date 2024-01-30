package com.scarnezis.ticketing.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Buyer {

    private String email;
    private String name;
    private String phone;
    private String address;
}
