package com.scarnezis.ticketing.dto.purchase.get;

import com.scarnezis.ticketing.entities.Buyer;
import com.scarnezis.ticketing.entities.Ticket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetTicketPurchaseDto {

    private Long code;
    private String qr;
    private GetEventPurchaseDto event;
    private Buyer buyer;

    public void setDto(Ticket ticket) {

        GetEventPurchaseDto eventData = new GetEventPurchaseDto();
        eventData.setDto(ticket.getEvent());

        this.setEvent(eventData);
        this.setCode(ticket.getCode());
        this.setBuyer(ticket.getBuyer());
        this.setQr(ticket.getQr());
    }
}
