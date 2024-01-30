package com.scarnezis.ticketing.dto.event.get;

import com.scarnezis.ticketing.entities.Event;
import com.scarnezis.ticketing.entities.EventOption;
import com.scarnezis.ticketing.entities.EventType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class GetOwnerEventDataDto {

    private Long id;
    private Integer maxCapacity;
    private Integer currentCapacity;
    private String name;
    private String description;
    private EventType type;
    private String location;
    private boolean enabled;
    private LocalDate date;
    private LocalDate submittedDate;
    private LocalDate expiredDate;
    private List<EventOption> eventOptions;

    public void setDto(Event event){

        this.setId(event.getId());
        this.setMaxCapacity(event.getMaxCapacity());
        this.setCurrentCapacity(event.getCurrentCapacity());
        this.setName(event.getName());
        this.setDescription(event.getDescription());
        this.setDate(event.getDate());
        this.setSubmittedDate(event.getSubmittedDate());
        this.setExpiredDate(event.getExpiredDate());
        this.setLocation(event.getLocation());
        this.setType(event.getType());
        this.setExpiredDate(event.getExpiredDate());
        this.setEventOptions(event.getEventOptions());
    }
}
