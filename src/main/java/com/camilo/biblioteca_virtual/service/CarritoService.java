package com.camilo.biblioteca_virtual.service;

import com.camilo.biblioteca_virtual.model.*;
import com.camilo.biblioteca_virtual.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;

    public void agregarLibroAlCarrito(String correoUsuario, Long idLibro, int cantidad) {
        // Buscar al usuario por su correo
        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar o crear el carrito
        Carrito carrito = carritoRepository.findByUsuario(usuario)
                .orElseGet(() -> {
                    Carrito nuevoCarrito = new Carrito();
                    nuevoCarrito.setUsuario(usuario);
                    return carritoRepository.save(nuevoCarrito);
                });

        // Buscar el libro
        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        // Crear detalle carrito
        DetalleCarrito detalle = new DetalleCarrito();
        detalle.setCarrito(carrito);
        detalle.setLibro(libro);
        detalle.setCantidad(cantidad);
        detalle.setSubtotal(libro.getPrecio() * cantidad);

        // Guardar en la base de datos
        detalleCarritoRepository.save(detalle);
    }
}
