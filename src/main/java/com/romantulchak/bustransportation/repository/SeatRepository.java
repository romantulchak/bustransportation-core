package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

//    @Query(value = "SELECT s FROM Seat s LEFT OUTER JOIN s.tickets st WHERE s.id = :seatId AND st.city.directionFrom <> :from")
//    Optional<Boolean> isSeatBooked(@Param("seatId") long seatId, @Param("from") String from);

      List<Seat> findSeatsByTripId(@Param("id") long id);
}
