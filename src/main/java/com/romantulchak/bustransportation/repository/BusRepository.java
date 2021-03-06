package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    boolean existsBusByName(String busName);

    List<Bus> findBusesByUserId(long userId);
}
