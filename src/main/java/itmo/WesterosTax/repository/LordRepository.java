package itmo.WesterosTax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import itmo.WesterosTax.model.Lord;

public interface LordRepository extends JpaRepository<Lord, Long> {  
} 