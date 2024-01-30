package com.scarnezis.ticketing.dto.event.get;

import com.scarnezis.ticketing.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOwnerDto {

    private Long id;
    private String email;
    private String name;
    private String phone;

    public void setDto(User user) {

        this.setId(user.getId());
        this.setName(user.getName());
        this.setEmail(user.getEmail());
        this.setPhone(user.getPhone());
    }
}
