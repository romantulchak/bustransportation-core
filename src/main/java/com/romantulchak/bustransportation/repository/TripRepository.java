package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.Trip;
import org.springframework.data.jpa.repository.EntityGraph;
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

//        @Query("SELECT t FROM Trip t LEFT JOIN t.direction td LEFT JOIN t.intermediatePlaces tip WHERE CAST(t.date AS LocalDate) = CAST(:date AS LocalDate) AND t.numberOfSeats >= :numberOfSeats AND td.directionFrom = :directionFrom AND (td.directionTo = :directionTo OR tip.city = :directionTo) ORDER BY t.price DESC")
//    List<Trip> findTripsByDate(@Param("date") LocalDateTime date, @Param("numberOfSeats") int numberOfSeats, @Param("directionFrom") String directionFrom, @Param("directionTo") String directionTo);

    @Query(value = "SELECT t FROM Trip t WHERE t.creator.id = :userId")
    List<Trip> findTripsForUser(@Param("userId") long userId);

    @Query(value = "SELECT t FROM Trip t LEFT JOIN t.cities tc JOIN FETCH t.seats ts WHERE tc.id = :id")
    Optional<Trip> findTripByCityId(@Param("id") long id);
}
