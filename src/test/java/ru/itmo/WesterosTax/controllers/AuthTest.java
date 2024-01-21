package ru.itmo.WesterosTax.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.itmo.WesterosTax.models.Role;
import ru.itmo.WesterosTax.models.User;
import ru.itmo.WesterosTax.repositories.UserRepository;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class AuthTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthController authController;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        openMocks(this);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
    }

    private void mockUserWithRole(Role role) {
        User user = new User();
        user.setRoles(Collections.singleton(role));
        when(authentication.getName()).thenReturn("lord");
        when(userRepository.findByUsername("lord")).thenReturn(user);
    }

    @Test
    void testGetMainPageForLandowner() {
        mockUserWithRole(Role.ROLE_LANDOWNER);
        assertEquals("redirect:/courier/index", authController.getMainPage());
    }

    @Test
    void testGetMainPageForCourier() {
        mockUserWithRole(Role.ROLE_COURIER);
        assertEquals("redirect:/household/index", authController.getMainPage());
    }

    @Test
    void testGetMainPageForAdmin() {
        mockUserWithRole(Role.ROLE_ADMIN);
        assertEquals("redirect:/admin/lord", authController.getMainPage());
    }

    @Test
    void testPostMainPageForLord() {
        mockUserWithRole(Role.ROLE_LORD);
        assertEquals("redirect:/landowner/index", authController.postMainPage());
    }

    @Test
    void testPostMainPageForLandowner() {
        mockUserWithRole(Role.ROLE_LANDOWNER);
        assertEquals("redirect:/courier/index", authController.postMainPage());
    }

    @Test
    void testPostMainPageForCourier() {
        mockUserWithRole(Role.ROLE_COURIER);
        assertEquals("redirect:/household/index", authController.postMainPage());
    }

    @Test
    void testPostMainPageForAdmin() {
        mockUserWithRole(Role.ROLE_ADMIN);
        assertEquals("redirect:/admin/lord", authController.postMainPage());
    }
}
