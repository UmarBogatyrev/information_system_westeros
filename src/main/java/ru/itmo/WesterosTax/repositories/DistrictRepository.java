package ru.itmo.WesterosTax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.WesterosTax.models.District;
import ru.itmo.WesterosTax.models.User;

public interface DistrictRepository extends JpaRepository<District, Long> {
}