package com.scarnezis.ticketing.dto.event.neww;

import com.scarnezis.ticketing.entities.EventType;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class NewEventDto {

    @NonNull
    private Long userId;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private EventType type;
    @NonNull
    private String location;
    @NonNull
    private LocalDate date;
    @NonNull
    private Integer maxCapacity;
    //@Builder.Default
    //private LocalDate expiredDate = LocalDate.now().plusMonths(1);
    private LocalDate expiredDate;
    @NonNull
    private List<NewEventOptionDto> eventOptions;
}
