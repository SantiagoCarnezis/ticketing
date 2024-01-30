package com.scarnezis.ticketing.service;

import com.scarnezis.ticketing.entities.Ticket;
import com.scarnezis.ticketing.exception.ticket.TicketNotFoundException;
import com.scarnezis.ticketing.repository.PurchaseRepository;
import com.scarnezis.ticketing.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public void sendTickets(Long purchaseCode) {


        List<Ticket> tickets = ticketRepository.findTicketsByPurchaseId(purchaseCode);

        tickets.stream().forEach(ticket -> sendEmail(ticket.getBuyer().getEmail()));

//        for (Ticket ticket:tickets) {
//
//            sendEmail(ticket.getBuyer().getEmail());
//            ticket.setEnabled(false);
//            ticketRepository.save(ticket);
//        }

//        purchase.getOptions().stream().forEach(
//                option -> option.getTickets().stream().forEach(
//                        ticket -> sendEmail(ticket.getBuyer().getEmail())));
    }

    public void resendTicket(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException(ticketId));
        sendEmail(ticket.getBuyer().getEmail());
        ticket.setEnabled(false);
        ticketRepository.save(ticket);
    }

    private void sendEmail(String email){

        System.out.println("the ticket was sent to " + email);
    }
}
