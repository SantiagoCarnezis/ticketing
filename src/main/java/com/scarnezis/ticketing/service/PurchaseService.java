package com.scarnezis.ticketing.service;

import com.scarnezis.ticketing.dto.purchase.BuyTicketDto;
import com.scarnezis.ticketing.dto.purchase.BuyTicketOptionDto;
import com.scarnezis.ticketing.dto.purchase.get.GetPurchaseDto;
import com.scarnezis.ticketing.entities.*;
import com.scarnezis.ticketing.exception.event.EventDisabledException;
import com.scarnezis.ticketing.exception.event.EventNotFoundException;
import com.scarnezis.ticketing.exception.event.InsufficientTicketsException;
import com.scarnezis.ticketing.exception.event.OptionNotFoundException;
import com.scarnezis.ticketing.exception.purchase.PurchaseNotFoundException;
import com.scarnezis.ticketing.exception.user.UserNotFoundException;
import com.scarnezis.ticketing.repository.EventRepository;
import com.scarnezis.ticketing.repository.EventOptionRepository;
import com.scarnezis.ticketing.repository.PurchaseRepository;
import com.scarnezis.ticketing.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventOptionRepository eventOptionRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Transactional
    public void buy(Long buyerId, BuyTicketDto ticketDto) {

        Event event = eventRepository.findById(ticketDto.getEventId()).orElseThrow(
                () -> new EventNotFoundException(ticketDto.getEventId().toString()));

        if (!event.isEnabled() || LocalDate.now().isAfter(event.getExpiredDate()))
            throw new EventDisabledException();

        int newEventCurrentCapacity = event.getCurrentCapacity();
        int newOptionCurrentCapacity;
        Purchase purchase = new Purchase();
        purchase.setOptions(new ArrayList<>());
        purchase.setPaymentMethod(ticketDto.getPaymentMethod());
        purchase.setEvent(event);

        for (BuyTicketOptionDto optionDto: ticketDto.getOptions() ) {

            EventOption option = eventOptionRepository.findById(optionDto.getPricingOptionId()).orElseThrow(
                    () -> new OptionNotFoundException(optionDto.getPricingOptionId()));

            newOptionCurrentCapacity = option.getCurrentCapacity() + optionDto.getOwners().size();

            if (newOptionCurrentCapacity > option.getMaxPurchasesCapacity())
                throw new InsufficientTicketsException(option.getName());

            option.setCurrentCapacity(newOptionCurrentCapacity);
            newEventCurrentCapacity = newEventCurrentCapacity + optionDto.getOwners().size() * option.getAmountTickets();

            PurchaseOption purchaseOption = getPurchaseOption(optionDto, option, event);
            purchase.addOption(purchaseOption);

            eventOptionRepository.save(option);

        }

        if(newEventCurrentCapacity > event.getMaxCapacity())
            throw new InsufficientTicketsException(event.getName());

        User user = userRepository.findById(buyerId).orElseThrow(
                () -> new UserNotFoundException(buyerId.toString()));
        user.addPurchase(purchase);
        event.setCurrentCapacity(newEventCurrentCapacity);
        eventRepository.save(event);
        userRepository.save(user);
    }

    public List<GetPurchaseDto> getAll(Long userId) {

        List<Purchase> purchases = purchaseRepository.findAllByOwner(userId);
        List<GetPurchaseDto> purchasesData = new ArrayList<>();

        for (Purchase purchase:purchases) {

            GetPurchaseDto purchaseData = new GetPurchaseDto();
            purchaseData.setDto(purchase);
            purchasesData.add(purchaseData);
        }

        return purchasesData;
    }

    public Purchase get(Long purchaseCode) {

        return purchaseRepository.findById(purchaseCode).orElseThrow(() -> new PurchaseNotFoundException(""));
    }

    public void colaService() {} //se compra con cola virtual (se implementa con kafka)

    private static PurchaseOption getPurchaseOption(BuyTicketOptionDto optionDto, EventOption option, Event event) {

        PurchaseOption purchaseOption = new PurchaseOption();
        purchaseOption.setDescription(option.getDescription());
        purchaseOption.setName(option.getName());
        purchaseOption.setPrice(option.getPrice());
        purchaseOption.setAmountTickets(optionDto.getOwners().size());

        List<Ticket> tickets = getTickets(event, optionDto, option);
        purchaseOption.setTickets(new ArrayList<>());
        purchaseOption.addAllTickets(tickets);

        return purchaseOption;
    }

    private static List<Ticket> getTickets(Event event, BuyTicketOptionDto optionDto, EventOption option) {

        Buyer buyer;
        Ticket ticket;
        List<Ticket> tickets = new ArrayList<>();

        if (option.getAmountTickets() * optionDto.getAmountPurchase() != optionDto.getOwners().size())
            throw new InsufficientTicketsException("");

        for (Buyer owner:optionDto.getOwners()) {

            buyer = new Buyer();
            buyer.setName(owner.getName());
            buyer.setAddress(owner.getAddress());
            buyer.setPhone(owner.getPhone());
            buyer.setEmail(owner.getEmail());

            ticket = new Ticket();
            ticket.setBuyer(buyer);
            ticket.setEvent(event);
            ticket.setEventOptionName(option.getName());
            ticket.setEventOptionDescription(option.getDescription());
            tickets.add(ticket);
        }

        return tickets;
    }
}
