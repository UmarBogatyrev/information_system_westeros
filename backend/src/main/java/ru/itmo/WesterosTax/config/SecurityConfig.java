// package ru.itmo.WesterosTax.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig extends WebSecurityConfiguration {

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.authorizeHttpRequests()
//                 .requestMatchers("/public/**").permitAll() // Îòêðûòûå ðåñóðñû
//                 .requestMatchers("/landowner/**").hasRole("LANDOWNER") // Ðåñóðñû äëÿ LANDOWNER
//                 .requestMatchers("/courier/**").hasRole("COURIER") // Ðåñóðñû äëÿ COURIER
//                 .requestMatchers("/lord/**").hasRole("LORD") // Ðåñóðñû äëÿ LORD
//                 .anyRequest().authenticated()
//                 .and()
//                 .formLogin(login -> login
//                         .loginPage("/login")
//                         .defaultSuccessUrl("/dashboard"))
//                 .logout(logout -> logout
//                         .logoutUrl("/logout")
//                         .logoutSuccessUrl("/login"))
//                 .exceptionHandling(handling -> handling.accessDeniedPage("/access-denied"));
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder(); // Используйте BCryptPasswordEncoder для хеширования паролей
//     }
// }