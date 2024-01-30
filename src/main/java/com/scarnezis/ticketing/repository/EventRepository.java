package com.scarnezis.ticketing.repository;

import com.scarnezis.ticketing.entities.Event;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAll(Specification<Event> spec);

    Optional<Event> findByIdAndEnabledTrue(Long id);
}
