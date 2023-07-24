package itmo.WesterosTax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import itmo.WesterosTax.model.District;

public interface DistrictRepository extends JpaRepository<District, Long> {  
}  