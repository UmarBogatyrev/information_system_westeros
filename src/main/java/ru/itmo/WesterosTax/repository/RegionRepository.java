package ru.itmo.WesterosTax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.itmo.WesterosTax.model.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {  
} 