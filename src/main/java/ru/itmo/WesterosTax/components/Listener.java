package ru.itmo.WesterosTax.components;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.itmo.WesterosTax.repositories.UserRepository;
import ru.itmo.WesterosTax.seeders.UserSeeder;

@Component
public class Listener {

    private final UserRepository userRepository;

    public Listener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
//        UserSeeder.seed(userRepository);
    }
}
