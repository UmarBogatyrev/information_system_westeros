package itmo.WesterosTax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import itmo.WesterosTax.model.User;

public interface UserRepository extends JpaRepository<User, Long> {  
} 