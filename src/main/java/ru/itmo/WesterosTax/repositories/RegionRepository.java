package ru.itmo.WesterosTax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.WesterosTax.models.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}