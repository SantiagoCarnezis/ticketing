package com.scarnezis.ticketing.repository;

import com.scarnezis.ticketing.entities.Accreditor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccreditorRepository extends JpaRepository<Accreditor, String> {

    Accreditor findByDni(String dni);
}
