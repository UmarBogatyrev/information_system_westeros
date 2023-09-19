package ru.itmo.WesterosTax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.WesterosTax.models.District;
import ru.itmo.WesterosTax.models.Tax;
import ru.itmo.WesterosTax.models.TaxDistrict;
import ru.itmo.WesterosTax.models.TaxRegion;

public interface TaxDistrictRepository extends JpaRepository<TaxDistrict, Long> {

    Iterable<TaxDistrict> findAllByTaxRegionOrderById(TaxRegion taxRegion);

    TaxDistrict findByTaxRegionTaxAndDistrict(Tax tax, District district);
}