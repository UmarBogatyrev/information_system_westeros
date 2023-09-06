package ru.itmo.WesterosTax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.WesterosTax.models.Household;

public interface HouseholdRepository extends JpaRepository<Household, Long> {
}
