package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    boolean existsBusByBusName(String busName);

   // @Query("SELECT b FROM Bus b left join b.directions as bd where bd.directionFrom = :directionFrom and bd.directionTo = :directionTo order by bd.price")
  //  List<Bus> busesByDirection(@Param("directionFrom") String directionFrom, @Param("directionTo") String directionTo);
}
