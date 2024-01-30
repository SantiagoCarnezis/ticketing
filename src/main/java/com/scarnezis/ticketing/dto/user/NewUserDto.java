package com.scarnezis.ticketing.dto.user;


import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class NewUserDto {

    @NonNull
    private String email;
    @NonNull
    private String name;
    @NonNull
    private String phone;
    @NonNull
    private String address;
    @NonNull
    private String password;
    @NonNull
    private boolean enableNotifications;
}
