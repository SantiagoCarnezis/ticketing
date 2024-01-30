package com.scarnezis.ticketing.dto.purchase;

import com.scarnezis.ticketing.entities.Buyer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuyTicketOptionDto {

    private String pricingOptionId;
    private List<Buyer> owners;
    private int amountPurchase;
}
