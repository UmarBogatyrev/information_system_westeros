package ru.itmo.WesterosTax.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.springframework.context.annotation.Scope;

import ru.itmo.WesterosTax.security.jwt.AuthEntryPointJwt;
import ru.itmo.WesterosTax.security.jwt.AuthTokenFilter;
import ru.itmo.WesterosTax.security.services.UserDetailsServiceImpl;

@Configuration
@EnableMethodSecurity
// (securedEnabled = true,
// jsr250Enabled = true,
// prePostEnabled = true) // by default
public class WebSecurityConfig {
  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests((authorize) -> authorize 
              .requestMatchers(mvc.pattern("/api/auth/**"), mvc.pattern("/api/test/**")).permitAll()
              .anyRequest().authenticated()
        );
      
    http.authenticationProvider(authenticationProvider());

    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    
    return http.build();
  }

  @Scope("prototype")
  @Bean
  MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
    return new MvcRequestMatcher.Builder(introspector);
  }
  // @Bean
  // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  // http.csrf(csrf -> csrf.disable())
  // .exceptionHandling(exception ->
  // exception.authenticationEntryPoint(unauthorizedHandler))
  // .sessionManagement(session ->
  // session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
  // .authorizeHttpRequests(auth ->
  // auth.requestMatchers("/api/auth/**").permitAll()
  // .requestMatchers("/api/test/**").permitAll()
  // .anyRequest().authenticated()
  // );

  // http.authenticationProvider(authenticationProvider());

  // http.addFilterBefore(authenticationJwtTokenFilter(),
  // UsernamePasswordAuthenticationFilter.class);

  // return http.build();
  // }
}