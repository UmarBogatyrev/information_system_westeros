package ru.itmo.WesterosTax.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/", "/public/**").permitAll()
                    .requestMatchers("/lord/**").hasRole("LORD")
                    .requestMatchers("/landowner/**").hasRole("LANDOWNER")
                    .requestMatchers("/courier/**").hasRole("COURIER")
                    .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin.loginPage("/login").permitAll()) // Настройка формы входа
            .logout(logout -> logout.logoutSuccessUrl("/").permitAll()); // Настройка выхода и перенаправления после выхода

        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserBuilder userBuilder = User.builder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        manager.createUser(userBuilder.username("lord").password(passwordEncoder.encode("lordpass")).roles("LORD").build());
        manager.createUser(userBuilder.username("landowner").password(passwordEncoder.encode("landownerpass")).roles("LANDOWNER").build());
        manager.createUser(userBuilder.username("courier").password(passwordEncoder.encode("courierpass")).roles("COURIER").build());
        return manager;
    }
}
