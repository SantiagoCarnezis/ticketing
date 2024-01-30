package com.scarnezis.ticketing.service;

import com.scarnezis.ticketing.dto.event.get.GetEventDataDto;
import com.scarnezis.ticketing.dto.user.NewUserDto;
import com.scarnezis.ticketing.dto.user.UpdateUserDto;
import com.scarnezis.ticketing.dto.user.get.GetUserDataDto;
import com.scarnezis.ticketing.entities.Event;
import com.scarnezis.ticketing.entities.User;
import com.scarnezis.ticketing.exception.user.UserAlreadyExistsException;
import com.scarnezis.ticketing.exception.user.UserNotFoundException;
import com.scarnezis.ticketing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void register(NewUserDto userDto){

        User user = userRepository.findByEmail(userDto.getEmail());

        if (user != null)
            throw new UserAlreadyExistsException(userDto.getEmail());
        else {

            user = new User();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setAddress(userDto.getAddress());
            user.setPassword(userDto.getPassword());
            user.setPhone(userDto.getPhone());
            user.setEnableNotifications(userDto.isEnableNotifications());
            user.setDateCreated(LocalDate.now());
            user.setSavedEvents(new ArrayList<>());
            user.setPurchases(new ArrayList<>());

            userRepository.save(user);
        }

    }

    public void update(Long id, UpdateUserDto userDto){

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        user.setName(userDto.getName());
        user.setAddress(userDto.getAddress());
        user.setPhone(userDto.getPhone());
        user.setEnableNotifications(userDto.isEnableNotifications());

        userRepository.save(user);
    }

    public GetUserDataDto getUserData(Long id){

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));

        GetUserDataDto userDataDto = new GetUserDataDto();
        userDataDto.setDto(user);

        return userDataDto;
    }

//    public List<Ticket> getUserTickets(String userId, String purchaseCode){
//
//        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId)).getTickets();
//    }

    public List<GetEventDataDto> getSavedEvents(Long id){

        List<Event> events = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id.toString())).getSavedEvents();

        List<GetEventDataDto> eventsData = new ArrayList<>();
        GetEventDataDto eventData;

        for (Event event:events) {

            eventData = new GetEventDataDto();
            eventData.setDto(event);
            eventsData.add(eventData);
        }

        return eventsData;
    }

}
