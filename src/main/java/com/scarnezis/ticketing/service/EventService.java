package com.scarnezis.ticketing.service;

import com.scarnezis.ticketing.dto.event.get.GetEventDataDto;
import com.scarnezis.ticketing.dto.event.get.GetOwnerEventDataDto;
import com.scarnezis.ticketing.dto.event.neww.NewEventDto;
import com.scarnezis.ticketing.dto.event.neww.NewEventOptionDto;
import com.scarnezis.ticketing.dto.event.update.UpdateEventDto;
import com.scarnezis.ticketing.dto.event.update.UpdateEventOptionDto;
import com.scarnezis.ticketing.entities.Event;
import com.scarnezis.ticketing.entities.EventOption;
import com.scarnezis.ticketing.entities.EventType;
import com.scarnezis.ticketing.entities.User;
import com.scarnezis.ticketing.exception.event.CapacityExceededException;
import com.scarnezis.ticketing.exception.event.EventDisabledException;
import com.scarnezis.ticketing.exception.event.EventNotFoundException;
import com.scarnezis.ticketing.exception.user.UserNotFoundException;
import com.scarnezis.ticketing.repository.EventRepository;
import com.scarnezis.ticketing.repository.UserRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    public void create(NewEventDto eventDto) {

        User owner = userRepository.findById(eventDto.getUserId()).orElseThrow(
                () -> new UserNotFoundException(eventDto.getUserId().toString()));

        validNewEvent(eventDto);

        Event event = new Event();
        event.setName(eventDto.getName());
        event.setDate(eventDto.getDate());
        event.setDescription(eventDto.getDescription());
        event.setLocation(eventDto.getLocation());
        event.setOwner(owner);
        event.setMaxCapacity(eventDto.getMaxCapacity());
        event.setCurrentCapacity(0);
        event.setExpiredDate(eventDto.getExpiredDate());
        event.setEnabled(true);

        List<EventOption> eventOptions = new ArrayList<>();
        EventOption eventOption;

        for (NewEventOptionDto optionDto:eventDto.getEventOptions()) {

            eventOption = new EventOption();
            eventOption.setName(optionDto.getName());
            eventOption.setDescription(optionDto.getDescription());
            eventOption.setCurrentCapacity(0);
            eventOption.setEnable(true);
            eventOption.setPrice(optionDto.getPrice());
            eventOption.setAmountTickets(optionDto.getAmountTickets());
            eventOption.setExpiredTime(optionDto.getExpiredTime());
            eventOption.setMaxPurchasesCapacity(optionDto.getMaxPurchasesCapacity());

            eventOptions.add(eventOption);
        }

        event.setEventOptions(eventOptions);
        event.setType(eventDto.getType());
        event.setSubmittedDate(LocalDate.now());

        eventRepository.save(event);
    }

    public void update(Long id, UpdateEventDto eventDto) {

        Event event = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id.toString()));

        validUpdateEvent(eventDto, event);

        event.setName(eventDto.getName());
        event.setDate(eventDto.getDate());
        event.setDescription(eventDto.getDescription());
        event.setLocation(eventDto.getLocation());
        event.setMaxCapacity(eventDto.getMaxCapacity());
        event.setExpiredDate(eventDto.getExpiredDate());
        event.setType(eventDto.getType());

        List<EventOption> options = event.getEventOptions();
        EventOption newOption;

        for (UpdateEventOptionDto newOptionDto: eventDto.getEventOptions()) {

            Optional<EventOption> oldOption = options.stream()
                    .filter(option -> option.getCode().equals(newOptionDto.getCode()))
                    .findFirst();

            if(oldOption.isPresent()) {
                options.remove(oldOption.get());
                newOption = updateEventOption(oldOption.get(), newOptionDto);
            }
            else {
                newOption = getNewEventOption(newOptionDto);
            }

            options.add(newOption);
        }

        event.setEventOptions(options);

        eventRepository.save(event);
    }

    public GetEventDataDto getEventData(Long id){

        Event event = eventRepository.findByIdAndEnabledTrue(id).orElseThrow(() -> new EventNotFoundException(id.toString()));

        GetEventDataDto eventData = new GetEventDataDto();
        eventData.setDto(event);

        return eventData;
    }

    public GetOwnerEventDataDto getOwnerEventData(Long id){

        //chequear que el dueño del evento sea sea el usuario logueado

        Event event = eventRepository.findByIdAndEnabledTrue(id).orElseThrow(() -> new EventNotFoundException(id.toString()));

        GetOwnerEventDataDto eventData = new GetOwnerEventDataDto();
        eventData.setDto(event);

        return eventData;
    }

    public List<Event> searchBy(String userId, String name, List<EventType> types, String location,
                         LocalDate minDate, LocalDate maxDate, Float minPrice, Float maxPrice){

        Specification<Event> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (userId != null) {
                predicates.add(criteriaBuilder.equal(root.get("owner").get("userId"), userId));
            }

            if (name != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), name));
            }

            if (types != null && !types.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("type"), types));
            }

            if (location != null) {
                predicates.add(criteriaBuilder.equal(root.get("location"), location));
            }

            if (minDate != null) {
                predicates.add(criteriaBuilder.greaterThan(root.get("minDate"), minDate));
            }

            if (maxDate != null) {
                predicates.add(criteriaBuilder.lessThan(root.get("maxDate"), maxDate));
            }

            if (minPrice != null) {
                predicates.add(criteriaBuilder.greaterThan(root.get("minPrice"), minPrice));
            }

            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThan(root.get("maxPrice"), maxPrice));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return eventRepository.findAll(spec);
        //return new ArrayList<>();

    }

    public void saveEvent(Long eventId, Long userId) {

        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId.toString()));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(eventId.toString()));;
        user.saveEvent(event);
        userRepository.save(user);
    }

    //public void getTicketsSold(){}

    private static void validNewEvent(NewEventDto eventDto) {
        int maxOptionsCapacity = eventDto.getEventOptions().stream()
                .mapToInt(options -> options.getMaxPurchasesCapacity() * options.getAmountTickets())
                .sum();

        if (maxOptionsCapacity >= eventDto.getMaxCapacity())
            throw new CapacityExceededException();
        else if ( eventDto.getExpiredDate().isBefore(eventDto.getDate()))
            throw new EventDisabledException();
    }

    private static void validUpdateEvent(UpdateEventDto eventDto, Event event) {

        int newMaxOptionsCapacity = eventDto.getEventOptions().stream()
                .mapToInt(options -> options.getMaxPurchasesCapacity() * options.getAmountTickets())
                .sum();

        if (newMaxOptionsCapacity >= eventDto.getMaxCapacity() || event.getMaxCapacity() >= eventDto.getMaxCapacity())
            throw new CapacityExceededException();
        else if ( eventDto.getExpiredDate().isBefore(eventDto.getDate()))
            throw new EventDisabledException();
    }

    private EventOption getNewEventOption(UpdateEventOptionDto updateEventOptionDto) {

        EventOption eventOption = new EventOption();
        eventOption.setName(updateEventOptionDto.getName());
        eventOption.setDescription(updateEventOptionDto.getDescription());
        eventOption.setPrice(updateEventOptionDto.getPrice());
        eventOption.setMaxPurchasesCapacity(updateEventOptionDto.getMaxPurchasesCapacity());
        eventOption.setAmountTickets(updateEventOptionDto.getAmountTickets());
        eventOption.setExpiredTime(updateEventOptionDto.getExpiredTime());
        eventOption.setEnable(updateEventOptionDto.isEnable());

        return eventOption;
    }

    private EventOption updateEventOption(EventOption updatedOption, UpdateEventOptionDto updateEventOptionDto) {

        if(updatedOption.getCurrentCapacity() < updateEventOptionDto.getMaxPurchasesCapacity())
            throw new CapacityExceededException();

        updatedOption.setName(updateEventOptionDto.getName());
        updatedOption.setDescription(updateEventOptionDto.getDescription());
        updatedOption.setPrice(updateEventOptionDto.getPrice());
        updatedOption.setMaxPurchasesCapacity(updateEventOptionDto.getMaxPurchasesCapacity());
        updatedOption.setAmountTickets(updateEventOptionDto.getAmountTickets());
        updatedOption.setExpiredTime(updateEventOptionDto.getExpiredTime());
        updatedOption.setEnable(updateEventOptionDto.isEnable());

        return updatedOption;
    }
}
