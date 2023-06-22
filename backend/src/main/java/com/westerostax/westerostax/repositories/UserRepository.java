package com.westerostax.westerostax.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.westerostax.westerostax.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByUsername(String username);

    User getUserById(int id);

    List<User> getUsersByDistrict(int districtId);

    List<User> getUsersByRegion(int regionId);

    List<User> getUsersByLord(int lordId);

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(int id);

}
