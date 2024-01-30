package com.scarnezis.ticketing.dto.purchase;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuyTicketDto {

    private Long eventId;
    private String paymentMethod;
    private List<BuyTicketOptionDto> options;
}
