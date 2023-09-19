package ru.itmo.WesterosTax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.WesterosTax.models.TaxType;
import ru.itmo.WesterosTax.models.User;

public interface TaxTypeRepository extends JpaRepository<TaxType, Long> {

    Iterable<TaxType> findAllByLord(User lord);
}