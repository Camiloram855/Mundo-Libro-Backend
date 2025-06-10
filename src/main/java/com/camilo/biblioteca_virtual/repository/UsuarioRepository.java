package com.camilo.biblioteca_virtual.repository;

import com.camilo.biblioteca_virtual.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar por correo únicamente
    Optional<Usuario> findByCorreo(String correo);

    // Buscar por correo Y clave (para login)
    Optional<Usuario> findByCorreoAndClave(String correo, String clave);
}
