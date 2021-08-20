package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "SELECT c FROM City c LEFT JOIN c.trip ct WHERE ct.id = :tripId")
    List<City> findCitiesForTrip(@Param("tripId") long tripId);

    @Query(value = "SELECT c FROM City c LEFT OUTER JOIN c.trip ct WHERE CAST(c.dateOfDeparture AS LocalDate) = CAST(:dateOfDeparture AS LocalDate) AND c.direction.directionFrom = :directionFrom AND c.direction.directionTo = :directionTo")
    List<City> findCitiesTrip(@Param("dateOfDeparture")LocalDateTime dateOfDeparture, @Param("directionFrom") String directionFrom, @Param("directionTo") String directionTo);
}
