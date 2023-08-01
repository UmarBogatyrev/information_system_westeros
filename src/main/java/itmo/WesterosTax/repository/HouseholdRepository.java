package itmo.WesterosTax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import itmo.WesterosTax.model.Household;

public interface HouseholdRepository extends JpaRepository<Household, Long> {  
}  