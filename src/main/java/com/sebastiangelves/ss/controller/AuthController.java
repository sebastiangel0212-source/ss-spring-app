package com.sebastiangelves.ss.controller;

import com.sebastiangelves.ss.model.Usuario;
import com.sebastiangelves.ss.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth") // Todas las rutas de este controlador empezarán con /api/auth
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Endpoint para registrar un nuevo usuario
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Usuario usuario) {
        // Codificamos la contraseña antes de guardarla
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }

    // Endpoint para el inicio de sesión
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Usuario loginRequest) {
        // Buscamos al usuario por su nombre de usuario
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(loginRequest.getUsername());

        // Verificamos si el usuario existe y si la contraseña coincide
        if (usuarioOpt.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), usuarioOpt.get().getPassword())) {
            // Si la autenticación es correcta, devolvemos un mensaje de éxito
            return ResponseEntity.ok("Autenticación satisfactoria");
        } else {
            // Si no, devolvemos un error de autenticación
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error en la autenticación: usuario o contraseña incorrectos");
        }
    }
}