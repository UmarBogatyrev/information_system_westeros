package ru.itmo.WesterosTax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.WesterosTax.models.Census;
import ru.itmo.WesterosTax.models.CensusDistrict;
import ru.itmo.WesterosTax.models.CensusRegion;
import ru.itmo.WesterosTax.models.District;

public interface CensusDistrictRepository extends JpaRepository<CensusDistrict, Long> {

    Iterable<CensusDistrict> findAllByCensusRegionOrderById(CensusRegion censusRegion);

    CensusDistrict findByCensusRegionCensusAndDistrict(Census census, District district);
}
