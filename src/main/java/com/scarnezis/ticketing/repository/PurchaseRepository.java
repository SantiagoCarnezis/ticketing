package com.scarnezis.ticketing.repository;

import com.scarnezis.ticketing.entities.Purchase;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Modifying
    @Transactional
    @Query(value = "Select * FROM purchase WHERE owner_id = :ownerId", nativeQuery = true)
    List<Purchase> findAllByOwner(Long ownerId);
}
