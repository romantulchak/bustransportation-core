package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.TripStatisticDeleted;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripStatisticDeletedRepository extends JpaRepository<TripStatisticDeleted, Long> {
}
