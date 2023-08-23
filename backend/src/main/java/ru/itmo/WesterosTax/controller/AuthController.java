package ru.itmo.WesterosTax.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import ru.itmo.WesterosTax.model.ERole;
import ru.itmo.WesterosTax.model.Role;
import ru.itmo.WesterosTax.model.User;
import ru.itmo.WesterosTax.payload.request.LoginRequest;
import ru.itmo.WesterosTax.payload.request.SignupRequest;
import ru.itmo.WesterosTax.payload.response.JwtResponse;
import ru.itmo.WesterosTax.payload.response.MessageResponse;
import ru.itmo.WesterosTax.repository.RoleRepository;
import ru.itmo.WesterosTax.repository.UserRepository;
import ru.itmo.WesterosTax.security.jwt.JwtUtils;
import ru.itmo.WesterosTax.security.services.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity
        .ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role courierRole = roleRepository.findByName(ERole.ROLE_COURIER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(courierRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "lord":
          Role lordRole = roleRepository.findByName(ERole.ROLE_LORD)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(lordRole);

          break;
           case "landowner":
          Role landownerRole = roleRepository.findByName(ERole.ROLE_LANDOWNER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(landownerRole);

          break;
        default:
          Role courierRole = roleRepository.findByName(ERole.ROLE_COURIER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(courierRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}