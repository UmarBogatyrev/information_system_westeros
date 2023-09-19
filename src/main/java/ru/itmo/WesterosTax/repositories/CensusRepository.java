package ru.itmo.WesterosTax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.WesterosTax.models.Census;
import ru.itmo.WesterosTax.models.CensusRegion;
import ru.itmo.WesterosTax.models.Region;
import ru.itmo.WesterosTax.models.User;

import java.util.List;

public interface CensusRepository extends JpaRepository<Census, Long> {

    Census findByLordAndFinished(User lord, boolean finished);

    Iterable<Census> findAllByLordAndFinished(User lord, boolean finished);

    Census findFirstByLordOrderByIdDesc(User lord);
}