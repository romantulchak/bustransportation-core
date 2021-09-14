package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query(value = "SELECT r FROM Route r LEFT OUTER JOIN r.trip rt WHERE r.departureFrom = :from AND r.arrivalTo = :to AND DATE(r.departureTime) = DATE(:date)")
    List<Route> findRoutesByDateAndDirection(@Param("from") String from, @Param("to") String to, @Param("date") LocalDate date);

}
