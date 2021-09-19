package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    boolean existsBusByNameAndUserUsername(String busName, String username);

    List<Bus> findBusesByUserId(long userId);

    Optional<Bus> findBusByIdAndUserId(long busId, long userId);
}
