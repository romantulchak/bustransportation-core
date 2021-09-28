package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.TripStatisticDeleted;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TripStatisticDeletedRepository extends JpaRepository<TripStatisticDeleted, Long> {

    @Query(value = "SELECT t FROM TripStatisticDeleted t WHERE t.user.id = :id")
    Page<TripStatisticDeleted> findAllByUserId(long id, Pageable pageable);
}
