// package ru.itmo.WesterosTax.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// @Configuration
// @EnableWebSecurity
// @EnableMethodSecurity(prePostEnabled = true)
// public class SecurityConfig extends WebSecurityConfiguration{

//     @Bean
//     public UserDetailsService userDetailsService() {
//         PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

//         UserDetails lord = User.builder()
//             .username("lord")
//             .password(passwordEncoder.encode("lordpass"))
//             .roles("LORD")
//             .build();

//         UserDetails landowner = User.builder()
//             .username("landowner")
//             .password(passwordEncoder.encode("landownerpass"))
//             .roles("LANDOWNER")
//             .build();

//         UserDetails courier = User.builder()
//             .username("courier")
//             .password(passwordEncoder.encode("courierpass"))
//             .roles("COURIER")
//             .build();

//         return new InMemoryUserDetailsManager(lord, landowner, courier);
//     }

//     @Configuration
//     public static class WebSecurityConfig {

//         @Bean
//         public HttpSecurity httpSecurity() throws Exception {
//             return new HttpSecurity(null, null, null)
//                 .authorizeHttpRequests(authorizeRequests ->
//                     authorizeRequests
//                         .requestMatchers("/", "/public/**").permitAll()
//                         .requestMatchers("/lord/**").hasRole("LORD")
//                         .requestMatchers("/landowner/**").hasRole("LANDOWNER")
//                         .requestMatchers("/courier/**").hasRole("COURIER")
//                         .anyRequest().authenticated()
//                 )
//                 .formLogin(formLogin -> formLogin.loginPage("/login").permitAll())
//                 .logout(logout -> logout.logoutSuccessUrl("/").permitAll());
//         }
//     }
// }