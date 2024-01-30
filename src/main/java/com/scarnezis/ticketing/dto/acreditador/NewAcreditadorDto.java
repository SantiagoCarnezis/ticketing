package com.scarnezis.ticketing.dto.acreditador;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewAcreditadorDto {

    private String dni;
    private String name;
    private Long eventId;
}
