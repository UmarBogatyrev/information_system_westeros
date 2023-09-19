package ru.itmo.WesterosTax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.WesterosTax.models.Census;
import ru.itmo.WesterosTax.models.CensusRegion;
import ru.itmo.WesterosTax.models.Region;

public interface CensusRegionRepository extends JpaRepository<CensusRegion, Long> {

    Iterable<CensusRegion> findAllByCensusOrderById(Census census);

    CensusRegion findByCensusAndRegion(Census census, Region region);

    CensusRegion findFirstByRegionOrderByIdDesc(Region region);
}
