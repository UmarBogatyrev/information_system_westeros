package ru.itmo.WesterosTax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.WesterosTax.models.Census;
import ru.itmo.WesterosTax.models.Tax;
import ru.itmo.WesterosTax.models.TaxType;
import ru.itmo.WesterosTax.models.User;

import java.util.List;

public interface TaxRepository extends JpaRepository<Tax, Long> {

    Iterable<Tax> findAllByTaxType(TaxType taxType);

    Tax findByTaxTypeLordAndFinished(User lord, boolean finished);

    Iterable<Tax> findAllByTaxTypeLordAndFinished(User lord, boolean finished);

    Tax findFirstByTaxTypeLordOrderByIdDesc(User lord);
}