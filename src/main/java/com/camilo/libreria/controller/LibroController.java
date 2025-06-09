package com.camilo.libreria.controller;

import com.camilo.libreria.model.Libro;
import com.camilo.libreria.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
@CrossOrigin(origins = "*") // Permite acceder desde cualquier frontend (como React o JS)
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    // Obtener todos los libros
    @GetMapping
    public List<Libro> obtenerLibros() {
        return libroRepository.findAll();
    }

    // Crear un nuevo libro
    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    // Obtener un libro por ID
    @GetMapping("/{id}")
    public Libro obtenerLibroPorId(@PathVariable Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    // Actualizar un libro existente
    @PutMapping("/{id}")
    public Libro actualizarLibro(@PathVariable Long id, @RequestBody Libro libroActualizado) {
        Libro libro = libroRepository.findById(id).orElse(null);
        if (libro != null) {
            libro.setTitulo(libroActualizado.getTitulo());
            libro.setAutor(libroActualizado.getAutor());
            libro.setDescripcion(libroActualizado.getDescripcion());
            libro.setImagen(libroActualizado.getImagen());
            return libroRepository.save(libro);
        }
        return null;
    }

    // Eliminar un libro por ID
    @DeleteMapping("/{id}")
    public void eliminarLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
    }
}
