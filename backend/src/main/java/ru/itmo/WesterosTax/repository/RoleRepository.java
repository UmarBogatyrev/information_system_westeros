package ru.itmo.WesterosTax.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.WesterosTax.model.ERole;
import ru.itmo.WesterosTax.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}