package com.scarnezis.ticketing.repository;

import com.scarnezis.ticketing.entities.EventOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventOptionRepository extends JpaRepository<EventOption, String> {

//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE event_option SET user_id = null WHERE user_id = :userId",
//            nativeQuery = true)
//    void buyTicket(String id, int newAvailablePurchases);
}
