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
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Las rutas de autenticación son públicas.
                .requestMatchers("/api/auth/**").permitAll()
                // Las rutas de productos requieren autenticación.
                .requestMatchers("/api/productos/**").authenticated()
                // Cualquier otra petición también requiere autenticación.
                .anyRequest().authenticated()
            );
        
        // Esta es la parte que faltaba para construir y retornar la configuración.
        return http.build();
    }
}