package com.scarnezis.ticketing.dto.purchase.get;

import com.scarnezis.ticketing.entities.Purchase;
import com.scarnezis.ticketing.entities.PurchaseOption;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetPurchaseDto {

    private Long code;
    private String paymentMethod;
    private GetEventPurchaseDto event;
    private List<GetPurchaseOptionDto> options;

    public void setDto(Purchase purchase){


        GetEventPurchaseDto eventData = new GetEventPurchaseDto();
        eventData.setDto(purchase.getEvent());

        List<GetPurchaseOptionDto> optionsData = new ArrayList<>();

        for (PurchaseOption ticket:purchase.getOptions()){

            GetPurchaseOptionDto optionData = new GetPurchaseOptionDto();
            optionData.setDto(ticket);
            optionsData.add(optionData);
        }

        this.setCode(purchase.getCode());
        this.setEvent(eventData);
        this.setPaymentMethod(purchase.getPaymentMethod());
        this.setOptions(optionsData);
    }
}
