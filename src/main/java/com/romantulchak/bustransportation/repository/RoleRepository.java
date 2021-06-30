package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.Role;
import com.romantulchak.bustransportation.model.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByName(ERole name);

}
