package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query("SELECT t FROM Trip t left join t.direction as td where CAST(t.date as LocalDate) = CAST(:date as LocalDate) and t.numberOfSeats > :numberOfSeats and td.directionFrom = :directionFrom and td.directionTo = :directionTo")
    List<Trip> findTripsByDate(@Param("date") LocalDateTime date, @Param("numberOfSeats") int numberOfSeats, @Param("directionFrom") String directionFrom, @Param("directionTo") String directionTo);

}
