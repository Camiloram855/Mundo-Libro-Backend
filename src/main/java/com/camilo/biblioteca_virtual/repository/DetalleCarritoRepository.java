package com.camilo.biblioteca_virtual.repository;

import com.camilo.biblioteca_virtual.model.Carrito;
import com.camilo.biblioteca_virtual.model.DetalleCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito, Long> {
    List<DetalleCarrito> findByCarrito(Carrito carrito);
}
