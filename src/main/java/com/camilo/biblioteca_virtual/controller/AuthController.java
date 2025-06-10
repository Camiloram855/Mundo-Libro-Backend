package com.camilo.biblioteca_virtual.controller;

import com.camilo.biblioteca_virtual.model.Usuario;
import com.camilo.biblioteca_virtual.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // permite acceso desde cualquier frontend (React, etc.)
public class AuthController {

    private final UsuarioService usuarioService;

    @Autowired
    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioEncontrado = usuarioService.autenticar(usuario.getCorreo(), usuario.getClave());

        if (usuarioEncontrado.isPresent()) {
            return "Login exitoso para el usuario: " + usuarioEncontrado.get().getNombre();
        } else {
            return "Credenciales incorrectas";
        }
    }
}
