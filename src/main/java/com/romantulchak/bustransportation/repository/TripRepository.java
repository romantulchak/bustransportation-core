package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query(value = "SELECT t FROM Trip t WHERE t.creator.id = :userId")
    List<Trip> findTripsForUser(@Param("userId") long userId);

    @Query(value = "SELECT t FROM Trip t JOIN FETCH t.seats ts")
    Optional<Trip> findTripByCityId(@Param("id") long id);

}
