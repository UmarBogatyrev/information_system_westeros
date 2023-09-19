package ru.itmo.WesterosTax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.WesterosTax.models.Region;
import ru.itmo.WesterosTax.models.User;

public interface RegionRepository extends JpaRepository<Region, Long> {

    Iterable<Region> findAllByLord(User lord);
}