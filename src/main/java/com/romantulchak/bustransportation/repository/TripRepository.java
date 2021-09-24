package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.enums.RemoveType;
import com.romantulchak.bustransportation.model.enums.TripType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query(value = "SELECT t FROM Trip t WHERE t.creator.id = :userId AND t.removeType = :removeType")
    Page<Trip> findTripsForUser(@Param("userId") long userId, @Param("removeType") RemoveType removeType, Pageable pageable);

    @Query(value = "SELECT t FROM Trip t JOIN FETCH t.seats ts WHERE t.id = :id AND t.removeType = :removeType")
    Optional<Trip> findTripByCityId(@Param("id") long id, @Param("removeType") String removeType);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Trip t SET t.removeType = :removeType WHERE t.id = :id")
    void updateRemoveType(@Param("id") long id, @Param("removeType") RemoveType removeType);

    Optional<Integer> countAllByRemoveTypeAndCreatorId(RemoveType removeType, long id);
}
