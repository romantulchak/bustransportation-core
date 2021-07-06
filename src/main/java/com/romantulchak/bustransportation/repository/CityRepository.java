package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "SELECT c FROM City c WHERE c.trip.id = :tripId")
    List<City> findCitiesForTrip(@Param("tripId") long tripId);
}
