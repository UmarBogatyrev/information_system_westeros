package ru.itmo.WesterosTax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.WesterosTax.models.Region;
import ru.itmo.WesterosTax.models.Tax;
import ru.itmo.WesterosTax.models.TaxRegion;

public interface TaxRegionRepository extends JpaRepository<TaxRegion, Long> {

    Iterable<TaxRegion> findAllByTaxOrderById(Tax tax);

    TaxRegion findByTaxAndRegion(Tax tax, Region region);

    TaxRegion findFirstByRegionOrderByIdDesc(Region region);
}