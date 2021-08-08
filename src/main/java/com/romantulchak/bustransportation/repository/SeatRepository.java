package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query(value = "SELECT s FROM Seat s LEFT OUTER JOIN s.bookings sb WHERE s.id = :seatId AND sb.city.direction.directionFrom <> :from")
    Optional<Boolean> isSeatBooked(@Param("seatId") long seatId, @Param("from") String from);
}
