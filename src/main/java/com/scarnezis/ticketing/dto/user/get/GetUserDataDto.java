package com.scarnezis.ticketing.dto.user.get;

import com.scarnezis.ticketing.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GetUserDataDto {

    private Long id;
    private String email;
    private String name;
    private String phone;
    private String address;
    private LocalDate dateCreated;
    private boolean enableNotifications;

    public void setDto(User user) {

        this.setId(user.getId());
        this.setName(user.getName());
        this.setEmail(user.getEmail());
        this.setAddress(user.getAddress());
        this.setPhone(user.getPhone());
        this.setEnableNotifications(user.isEnableNotifications());
        this.setDateCreated(user.getDateCreated());
    }
}
