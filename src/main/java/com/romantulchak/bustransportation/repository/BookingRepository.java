package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT b FROM Booking b WHERE b.user.id = :id")
    Page<Booking> findBookingByUserId(@Param("id") long id, Pageable pageable);

    @Query(value = "SELECT sum(b.totalNumberOfSeats) FROM Booking b LEFT JOIN b.city c WHERE c.id = :cityId")
    Optional<Integer> findTotalNumberOfSeats(@Param("cityId") long id);
}
