package com.scarnezis.ticketing.dto.event.get;

import com.scarnezis.ticketing.entities.Event;
import com.scarnezis.ticketing.entities.EventOption;
import com.scarnezis.ticketing.entities.EventType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetEventDataDto {

    private Long id;
    private GetOwnerDto owner;
    private String name;
    private String description;
    private EventType type;
    private String location;
    private LocalDate date;
    private LocalDate expiredDate;
    private List<GetEventOptionDto> eventOptions;

    public void addOption(GetEventOptionDto option) {
        eventOptions.add(option);
    }

    public void setDto(Event event){

        GetOwnerDto ownerData = new GetOwnerDto();
        ownerData.setDto(event.getOwner());

        this.setId(event.getId());
        this.setName(event.getName());
        this.setDescription(event.getDescription());
        this.setDate(event.getDate());
        this.setLocation(event.getLocation());
        this.setType(event.getType());
        this.setExpiredDate(event.getExpiredDate());
        this.setOwner(ownerData);

        GetEventOptionDto optionDataDto;
        this.setEventOptions(new ArrayList<>());

        for (EventOption option:event.getEventOptions()) {

            optionDataDto = new GetEventOptionDto();
            optionDataDto.setDto(option);

            this.addOption(optionDataDto);
        }
    }
}
