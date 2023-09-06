package ru.itmo.WesterosTax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.WesterosTax.models.District;

public interface DistrictRepository extends JpaRepository<District, Long> {
}