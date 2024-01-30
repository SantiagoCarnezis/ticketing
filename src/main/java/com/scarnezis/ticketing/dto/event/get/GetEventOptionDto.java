package com.scarnezis.ticketing.dto.event.get;

import com.scarnezis.ticketing.entities.EventOption;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetEventOptionDto {

    private Long code;
    private String name;
    private String description;
    private float price;
    private int amountTickets;
    private boolean enable;

    public void setDto(EventOption option){

        this.setDescription(option.getDescription());
        this.setName(option.getName());
        this.setCode(option.getCode());
        this.setPrice(option.getPrice());
        this.setEnable(option.isEnable());
        this.setAmountTickets(option.getAmountTickets());
    }
}
