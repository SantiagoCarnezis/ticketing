package com.scarnezis.ticketing.controller;

import com.scarnezis.ticketing.dto.acreditador.NewAcreditadorDto;
import com.scarnezis.ticketing.exception.acreditador.AccreditorNotFoundException;
import com.scarnezis.ticketing.exception.event.EventNotFoundException;
import com.scarnezis.ticketing.exception.ticket.TicketNotFoundException;
import com.scarnezis.ticketing.exception.user.UserAlreadyExistsException;
import com.scarnezis.ticketing.service.AccreditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accreditor")
public class AcreditadorController {

    @Autowired
    private AccreditorService accreditorService;

    @PostMapping
    public ResponseEntity create(@RequestBody NewAcreditadorDto acreditadorDto) {

        ResponseEntity response;

        try {
            accreditorService.create(acreditadorDto);
            response = new ResponseEntity<>("created", HttpStatus.OK);
        }
        catch (EventNotFoundException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("event not found", HttpStatus.BAD_REQUEST);
        }
        catch (UserAlreadyExistsException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("error during registration", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PostMapping("/{accreditorDni}/validate/{ticketCode}")
    public ResponseEntity validateTicket(@PathVariable Long ticketCode, @PathVariable String accreditorDni) {

        ResponseEntity response;

        try {
            accreditorService.validateTicket(accreditorDni, ticketCode);
            response = new ResponseEntity<>("validated", HttpStatus.OK);
        }
        catch (TicketNotFoundException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("invalid ticket", HttpStatus.BAD_REQUEST);
        }
        catch (AccreditorNotFoundException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("acreditador not found", HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
