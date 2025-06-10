package com.camilo.biblioteca_virtual.repository;

import com.camilo.biblioteca_virtual.model.Carrito;
import com.camilo.biblioteca_virtual.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    Optional<Carrito> findByUsuario(Usuario usuario);
}
