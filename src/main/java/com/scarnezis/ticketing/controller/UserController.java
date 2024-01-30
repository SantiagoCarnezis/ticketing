package com.scarnezis.ticketing.controller;

import com.scarnezis.ticketing.dto.purchase.get.GetPurchaseDto;
import com.scarnezis.ticketing.dto.user.NewUserDto;
import com.scarnezis.ticketing.dto.user.UpdateUserDto;
import com.scarnezis.ticketing.dto.user.get.GetUserDataDto;
import com.scarnezis.ticketing.entities.Purchase;
import com.scarnezis.ticketing.exception.user.UserAlreadyExistsException;
import com.scarnezis.ticketing.exception.user.UserNotFoundException;
import com.scarnezis.ticketing.service.PurchaseService;
import com.scarnezis.ticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody NewUserDto userDto) {

        ResponseEntity response;

        try {
            userService.register(userDto);
            response = new ResponseEntity<>("registered", HttpStatus.CREATED);
        }
        catch (UserAlreadyExistsException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("error during registration, user already exists", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PutMapping("/{user_id}")
    public ResponseEntity update(@PathVariable("user_id") Long id, @RequestBody UpdateUserDto userDto) {

        ResponseEntity response;

        try {
            userService.update(id, userDto);
            response = new ResponseEntity<>("updated", HttpStatus.OK);
        }
        catch (UserNotFoundException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("error during updating, user not found", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/{user_id}")
    public ResponseEntity get(@PathVariable("user_id") Long id) {

        ResponseEntity response;

        try {
            GetUserDataDto user = userService.getUserData(id);
            response = new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (UserNotFoundException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("error during updating, user not found", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/{user_id}/purchase")
    public ResponseEntity getPurchases(@PathVariable("user_id") Long userId) {

        ResponseEntity response;

        try {
            List<GetPurchaseDto> purchases = purchaseService.getAll(userId);
            response = new ResponseEntity<>(purchases, HttpStatus.OK);
        }
        catch (UserNotFoundException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("error during updating, user not found", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/{user_id}/purchase/{purchase_id}")
    public ResponseEntity getPurchase(@PathVariable("purchase_id") Long purchaseId) {

        ResponseEntity response;

        try {
            Purchase purchase = purchaseService.get(purchaseId);
            response = new ResponseEntity<>(purchase, HttpStatus.OK);
        }
        catch (UserNotFoundException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("error during updating, user not found", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/{user_id}/events/saved")
    public ResponseEntity getSavedEvents(@PathVariable("user_id") Long id) {

        ResponseEntity response;

        try {
            response = new ResponseEntity<>(userService.getSavedEvents(id), HttpStatus.OK);
        }
        catch (UserNotFoundException ex) {

            ex.printStackTrace();
            response = new ResponseEntity<>("error during updating, user not found", HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
