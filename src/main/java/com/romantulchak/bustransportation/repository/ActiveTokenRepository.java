package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.ActivateToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActiveTokenRepository extends JpaRepository<ActivateToken, Long> {

    Optional<ActivateToken> findByToken(String token);

    Optional<Long> getTokenIdByToken(String token);
}
