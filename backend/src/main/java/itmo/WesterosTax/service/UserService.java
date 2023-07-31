package itmo.WesterosTax.service;

import itmo.WesterosTax.model.District;
import itmo.WesterosTax.model.Region;
import itmo.WesterosTax.model.User;
import itmo.WesterosTax.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name, String username, String password, String role) {
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        return userRepository.save(user);
    }

    public User createLord(String name, String username, String password, Region region) {
        return createUser(name, username, password, "LORD");
    }

    public User createLandowner(String name, String username, String password, District district, User lord) {
        User landowner = createUser(name, username, password, "LANDOWNER");
        landowner.setDistrict(district);
        landowner.setLord(lord);
        return userRepository.save(landowner);
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

}
