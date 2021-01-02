package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {
    boolean existsDirectionByDirection(String direction);
}
