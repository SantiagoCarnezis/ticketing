package com.scarnezis.ticketing.dto.purchase.get;

import com.scarnezis.ticketing.dto.event.get.GetOwnerDto;
import com.scarnezis.ticketing.entities.Event;
import com.scarnezis.ticketing.entities.EventType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GetEventPurchaseDto {

    private Long id;
    private String name;
    private String description;
    private EventType type;
    private String location;
    private LocalDate date;
    private GetOwnerDto owner;

    public void setDto(Event event){

        GetOwnerDto ownerData = new GetOwnerDto();
        ownerData.setDto(event.getOwner());

        this.setId(event.getId());
        this.setName(event.getName());
        this.setDescription(event.getDescription());
        this.setDate(event.getDate());
        this.setLocation(event.getLocation());
        this.setType(event.getType());
        this.setOwner(ownerData);
    }
}
