package ru.itmo.WesterosTax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ru.itmo.WesterosTax.models.TaxType;
import ru.itmo.WesterosTax.models.User;

public interface TaxTypeRepository extends JpaRepository<TaxType, Long> {

    Iterable<TaxType> findAllByLord(User lord);

    @Query("SELECT formula FROM TaxType WHERE id = :taxTypeId")
    String findFormulaById(@Param("taxTypeId") Long taxTypeId);
}