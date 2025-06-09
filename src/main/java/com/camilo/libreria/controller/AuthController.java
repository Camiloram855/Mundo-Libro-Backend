package com.camilo.libreria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*") // Permite peticiones desde el frontend (JS)
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Lógica de autenticación básica. Puedes mejorarla con DB más adelante.
        if ("admin".equals(username) && "admin123".equals(password)) {
            return ResponseEntity.ok("LOGIN_OK");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        } // hola como estan
    }
}
