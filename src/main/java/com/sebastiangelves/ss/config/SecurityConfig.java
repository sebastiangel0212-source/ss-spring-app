package com.sebastiangelves.ss.config;

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
        return new BCryptPasswordEncoder();
    }

    @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        // --> 1. ASEGÚRATE DE QUE CSRF ESTÉ DESHABILITADO
        .csrf(csrf -> csrf.disable())

        .authorizeHttpRequests(auth -> auth
            // --> 2. ASEGÚRATE DE QUE LAS RUTAS SEAN PÚBLICAS (permitAll)
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers("/api/productos/**").permitAll() 
            .anyRequest().authenticated()
        );

    return http.build();
}
}