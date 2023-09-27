package ru.itmo.WesterosTax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.WesterosTax.models.Role;
import ru.itmo.WesterosTax.models.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<User> findByLandowner(User user);

    List<User> findByLord(User user);

    Iterable<User> findByRolesContains(Role role);
}
