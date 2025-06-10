package com.camilo.biblioteca_virtual.controller;

import com.camilo.biblioteca_virtual.model.Usuario;
import com.camilo.biblioteca_virtual.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // para permitir llamadas desde React
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario loginData) {
        String correo = loginData.getCorreo();
        String clave = loginData.getClave();

        Optional<Usuario> usuario = usuarioRepository.findByCorreoAndClave(correo, clave);
        return usuario.orElse(null); // Devuelve el usuario si lo encuentra o null si no
    }
}
