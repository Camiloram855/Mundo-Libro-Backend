package com.camilo.biblioteca_virtual.service;

import com.camilo.biblioteca_virtual.model.Usuario;
import com.camilo.biblioteca_virtual.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> autenticar(String correo, String clave) {
        return usuarioRepository.findByCorreoAndClave(correo, clave);
    }
}
