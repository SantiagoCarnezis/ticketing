package com.scarnezis.ticketing.dto.event.update;

import com.scarnezis.ticketing.entities.EventType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UpdateEventDto {

    private String name;
    private String description;
    private EventType type;
    private String location;
    private LocalDate date;
    private Integer maxCapacity;
    private LocalDate expiredDate;
    private List<UpdateEventOptionDto> eventOptions;
}
