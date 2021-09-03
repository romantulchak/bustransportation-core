package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.TripTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripTemplateRepository extends JpaRepository<TripTemplate, Long> {

    List<TripTemplate> findTripTemplatesByUserId(long id);

    @Query(value = "SELECT t FROM TripTemplate t JOIN FETCH t.stops ts WHERE t.id = :id")
    Optional<TripTemplate> findTripTemplateStopsById(@Param("id") long id);

}
