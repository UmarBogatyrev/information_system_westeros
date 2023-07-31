package itmo.WesterosTax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import itmo.WesterosTax.model.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {  
} 