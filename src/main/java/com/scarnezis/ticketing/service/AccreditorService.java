package com.scarnezis.ticketing.service;

import com.scarnezis.ticketing.dto.acreditador.NewAcreditadorDto;
import com.scarnezis.ticketing.entities.Accreditor;
import com.scarnezis.ticketing.entities.Event;
import com.scarnezis.ticketing.entities.Ticket;
import com.scarnezis.ticketing.exception.acreditador.AccreditorNotFoundException;
import com.scarnezis.ticketing.exception.event.EventNotFoundException;
import com.scarnezis.ticketing.exception.ticket.TicketNotFoundException;
import com.scarnezis.ticketing.exception.user.UserAlreadyExistsException;
import com.scarnezis.ticketing.repository.AccreditorRepository;
import com.scarnezis.ticketing.repository.EventRepository;
import com.scarnezis.ticketing.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccreditorService {

    @Autowired
    private AccreditorRepository accreditorRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public void create(NewAcreditadorDto acreditadorDto) {

        Accreditor accreditor = accreditorRepository.findByDni(acreditadorDto.getDni());
        Event event = eventRepository.findById(acreditadorDto.getEventId()).orElseThrow(
                () -> new EventNotFoundException(acreditadorDto.getEventId().toString()));


        if (accreditor != null)
            throw new UserAlreadyExistsException(acreditadorDto.getDni());
        else {

            accreditor = new Accreditor();
            accreditor.setDni(accreditor.getDni());
            accreditor.setName(accreditor.getName());
            accreditor.setEvent(event);
            accreditor.setValidatedTickets(new ArrayList<>());

            accreditorRepository.save(accreditor);
        }
    }

    public void validateTicket(String dni, Long ticketCode) {

        Ticket ticket = ticketRepository.findByCodeAndEnabled(ticketCode, true).orElseThrow(() ->
                new TicketNotFoundException(ticketCode));
        Accreditor accreditor = accreditorRepository.findById(dni).orElseThrow(() -> new AccreditorNotFoundException(dni));

        ticket.setEnabled(false);
        accreditor.addTicket(ticket);

        accreditorRepository.save(accreditor);
        ticketRepository.save(ticket);

        //int updated = ticketRepository.disableTicket(ticketCode);

//        if (updated <= 0)
//            throw new InvalidTicketException(ticketCode);
    }


}
