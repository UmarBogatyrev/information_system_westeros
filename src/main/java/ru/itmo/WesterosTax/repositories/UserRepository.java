package ru.itmo.WesterosTax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.WesterosTax.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Iterable<User> findByLandowner(User user);

    Iterable<User> findByLord(User user);
}
