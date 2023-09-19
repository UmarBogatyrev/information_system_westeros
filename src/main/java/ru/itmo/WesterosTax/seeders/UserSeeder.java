package ru.itmo.WesterosTax.seeders;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itmo.WesterosTax.models.Role;
import ru.itmo.WesterosTax.models.User;
import ru.itmo.WesterosTax.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserSeeder {

    private static final List<User> userList = new ArrayList<>();

    private static void init() {
        userList.add(new User("ADMIN", new BCryptPasswordEncoder().encode("ADMIN"), true, Collections.singleton(Role.ROLE_ADMIN)));
    }

    public static void seed(UserRepository userRepository) {
        init();
        for (User user : userList) {
            if (userRepository.findByUsername(user.getUsername()) == null)
                userRepository.save(user);
        }
    }
}
