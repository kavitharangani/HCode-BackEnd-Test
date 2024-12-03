package com.example.content_management.config;

import com.example.content_management.service.impl.UserServiceIMPL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection (if necessary)
                .authorizeRequests(authorize ->
                        authorize
                                .requestMatchers("/api/v1/user/**").permitAll() // Allow public access to login and register endpoints
                                .requestMatchers("/api/v1/articles/**").permitAll()

                )
                .logout(logout -> logout
                        .logoutUrl("/api/v1/user/logout") // Define the logout URL
                        .logoutSuccessUrl("/api/v1/user/login?logout") // Redirect after logout
                        .permitAll()
                )
                .oidcLogout().disable(); // Disable HTTP Basic Authentication since we're using session-based login

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}