package com.scarnezis.ticketing.repository;

import com.scarnezis.ticketing.entities.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByCodeAndEnabled(Long code, boolean enabled);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ticket SET enabled = false WHERE code = :code and enabled = true", nativeQuery = true)
    int disableTicket(Long code);

    @Modifying
    @Transactional
    @Query(value = "SELECT t FROM Ticket t INNER JOIN Purchase p ON t MEMBER OF p.options WHERE p.code = :purchaseId",
            nativeQuery = true)
    List<Ticket> findTicketsByPurchaseId(@Param("purchaseId") Long purchaseId);
}
