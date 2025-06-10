package com.camilo.biblioteca_virtual.controller;

import com.camilo.biblioteca_virtual.model.Libro;
import com.camilo.biblioteca_virtual.repository.LibroRepository;
import com.camilo.biblioteca_virtual.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
@CrossOrigin(origins = "*")
public class LibroController {



    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private LibroService libroService; //

    @GetMapping
    public List<Libro> obtenerLibros() {
        return libroService.obtenerTodos();
    }

    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    @GetMapping("/{id}")
    public Libro obtenerLibroPorId(@PathVariable Long id) {
        return libroRepository.findById(id).orElse(null);
    }

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

    @DeleteMapping("/{id}")
    public void eliminarLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
    }

    @GetMapping("/buscar")
    public List<Libro> buscarPorTitulo(@RequestParam String titulo) {
        return libroService.buscarPorTitulo(titulo);
    }
}
