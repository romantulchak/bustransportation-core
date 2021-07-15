package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
