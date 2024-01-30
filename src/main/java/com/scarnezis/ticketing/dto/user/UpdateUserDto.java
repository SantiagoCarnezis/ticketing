package com.scarnezis.ticketing.dto.user;


import lombok.Data;

@Data
public class UpdateUserDto {

    private String name;
    private String phone;
    private String address;
    private boolean enableNotifications;
}
