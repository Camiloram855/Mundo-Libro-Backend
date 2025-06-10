package com.camilo.biblioteca_virtual.controller;

import com.camilo.biblioteca_virtual.model.Carrito;
import com.camilo.biblioteca_virtual.model.DetalleCarrito;
import com.camilo.biblioteca_virtual.model.Libro;
import com.camilo.biblioteca_virtual.model.Usuario;
import com.camilo.biblioteca_virtual.repository.CarritoRepository;
import com.camilo.biblioteca_virtual.repository.DetalleCarritoRepository;
import com.camilo.biblioteca_virtual.repository.LibroRepository;
import com.camilo.biblioteca_virtual.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*") // Esto permite que React pueda conectarse
public class CarritoController {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/agregar")
    public DetalleCarrito agregarLibroAlCarrito(@RequestBody DetalleCarritoRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(request.getIdUsuario());
        Optional<Libro> libroOpt = libroRepository.findById(request.getIdLibro());

        if (usuarioOpt.isPresent() && libroOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            Libro libro = libroOpt.get();

            Carrito carrito = carritoRepository.findByUsuario(usuario).orElseGet(() -> {
                Carrito nuevoCarrito = new Carrito();
                nuevoCarrito.setUsuario(usuario);
                return carritoRepository.save(nuevoCarrito);
            });

            DetalleCarrito detalle = new DetalleCarrito();
            detalle.setCarrito(carrito);
            detalle.setLibro(libro);
            detalle.setCantidad(request.getCantidad());
            detalle.setSubtotal(libro.getPrecio() * request.getCantidad());

            return detalleCarritoRepository.save(detalle);
        } else {
            throw new RuntimeException("Usuario o libro no encontrado.");
        }
    }

    // ✅ NUEVO MÉTODO PARA CONSULTAR LOS LIBROS DEL CARRITO DE UN USUARIO
    @GetMapping("/usuario/{id}")
    public List<DetalleCarrito> obtenerCarritoPorUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            Optional<Carrito> carritoOpt = carritoRepository.findByUsuario(usuario);

            if (carritoOpt.isPresent()) {
                Carrito carrito = carritoOpt.get();
                return detalleCarritoRepository.findByCarrito(carrito);
            } else {
                return new ArrayList<>(); // Usuario no tiene carrito aún
            }
        } else {
            throw new RuntimeException("Usuario no encontrado.");
        }
    }

    // Clase estática para mapear el JSON recibido desde React
    public static class DetalleCarritoRequest {
        private Long idUsuario;
        private Long idLibro;
        private int cantidad;

        public Long getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(Long idUsuario) {
            this.idUsuario = idUsuario;
        }

        public Long getIdLibro() {
            return idLibro;
        }

        public void setIdLibro(Long idLibro) {
            this.idLibro = idLibro;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
    }
}
