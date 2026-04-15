package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Esto cifrará las contraseñas
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitamos esto para facilitar las pruebas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/**").permitAll() // Por ahora permitimos todo en users para poder registrar
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Swagger libre
                        .anyRequest().authenticated() // Lo demás (libros) requiere login
                )
                .httpBasic(basic -> {}); // Permite autenticación básica para pruebas

        return http.build();
    }
}