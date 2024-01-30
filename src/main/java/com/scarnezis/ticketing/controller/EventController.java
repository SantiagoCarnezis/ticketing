package com.scarnezis.ticketing.controller;

import com.scarnezis.ticketing.dto.event.get.GetEventDataDto;
import com.scarnezis.ticketing.dto.event.get.GetOwnerEventDataDto;
import com.scarnezis.ticketing.dto.event.neww.NewEventDto;
import com.scarnezis.ticketing.dto.event.update.UpdateEventDto;
import com.scarnezis.ticketing.dto.purchase.BuyTicketDto;
import com.scarnezis.ticketing.exception.event.*;
import com.scarnezis.ticketing.exception.user.UserNotFoundException;
import com.scarnezis.ticketing.service.EventService;
import com.scarnezis.ticketing.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity create(@RequestBody NewEventDto eventDto) {

        ResponseEntity response;

        try {
            eventService.create(eventDto);
            response = new ResponseEntity<>("created", HttpStatus.CREATED);
        }
        catch (UserNotFoundException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
        }
        catch (CapacityExceededException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("capacity exceeded", HttpStatus.BAD_REQUEST);
        }
        catch (EventDisabledException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("event disabled", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PutMapping("/{event_id}")
    public ResponseEntity update(@PathVariable("event_id") Long id, @RequestBody UpdateEventDto eventDto) {

        ResponseEntity response;

        try {
            eventService.update(id, eventDto);
            response = new ResponseEntity<>("updated", HttpStatus.OK);
        }
        catch (EventNotFoundException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("error during updating, event not found", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/{event_id}")
    public ResponseEntity<?> get(@PathVariable("event_id") Long id) {

        ResponseEntity<?> response;

        try {
            GetEventDataDto event = eventService.getEventData(id);
            response = new ResponseEntity<>(event, HttpStatus.OK);
        }
        catch (EventNotFoundException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("event not found", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/{event_id}/owner")
    public ResponseEntity<?> getOwnerEventData(@PathVariable("event_id") Long id) {

        ResponseEntity<?> response;

        try {
            GetOwnerEventDataDto event = eventService.getOwnerEventData(id);
            response = new ResponseEntity<>(event, HttpStatus.OK);
        }
        catch (EventNotFoundException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("event not found", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PutMapping("/{event_id}/user/{user_id}")
    public ResponseEntity saveEvent(@PathVariable("event_id") Long eventId, @PathVariable("user_id") Long userId) {

        ResponseEntity response;

        try {
            eventService.saveEvent(eventId, userId);
            response = new ResponseEntity<>("saved", HttpStatus.OK);
        }
        catch (UserNotFoundException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
        }
        catch (EventNotFoundException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("event not found", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PostMapping("/buy/{user_id}")
    public ResponseEntity buy(@PathVariable("user_id") Long buyerId, @RequestBody BuyTicketDto ticketDto) {

        ResponseEntity response;

        try {
            purchaseService.buy(buyerId, ticketDto);
            response = new ResponseEntity<>("created", HttpStatus.CREATED);
        }
        catch (UserNotFoundException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
        }
        catch (EventNotFoundException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("event not found", HttpStatus.BAD_REQUEST);
        }
        catch (OptionNotFoundException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("event option not found", HttpStatus.BAD_REQUEST);
        }
        catch (InsufficientTicketsException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("insufficient tickets", HttpStatus.BAD_REQUEST);
        }
        catch (EventDisabledException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("event disabled", HttpStatus.BAD_REQUEST);
        }

        return response;

    }
}
