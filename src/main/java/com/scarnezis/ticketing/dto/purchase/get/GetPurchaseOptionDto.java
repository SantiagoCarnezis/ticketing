package com.scarnezis.ticketing.dto.purchase.get;

import com.scarnezis.ticketing.entities.PurchaseOption;
import com.scarnezis.ticketing.entities.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetPurchaseOptionDto {

    private Long code;
    private String name;
    private String description;
    private float price;
    private int amountTickets;
    private List<GetTicketPurchaseDto> tickets;

    public void setDto(PurchaseOption option){

        this.setCode(option.getCode());
        this.setName(option.getName());
        this.setDescription(option.getDescription());
        this.setPrice(option.getPrice());
        this.setAmountTickets(option.getAmountTickets());

        List<GetTicketPurchaseDto> ticketsData = new ArrayList<>();

        for (Ticket ticket:option.getTickets()){

            GetTicketPurchaseDto ticketData = new GetTicketPurchaseDto();
            ticketData.setDto(ticket);
            ticketsData.add(ticketData);
        }

        this.setTickets(ticketsData);
    }
}
