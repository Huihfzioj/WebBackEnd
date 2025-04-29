package com.example.gestionAlumni.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Désactive CSRF pour faciliter les tests via Postman
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/alumni/**").permitAll() // Permet un accès public à /api/alumni/**
                .requestMatchers("/api/students/**").permitAll() // Permet un accès public à /api/students/**
                .requestMatchers("/api/offers/**").permitAll() 
                .requestMatchers("/api/messages/**").permitAll()// Permet un accès public à /api/offers/**
                .anyRequest().authenticated() // Authentifie toutes les autres requêtes
            );
        return http.build();
    }
}
