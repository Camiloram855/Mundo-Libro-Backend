package com.camilo.biblioteca_virtual.service;

import com.camilo.biblioteca_virtual.model.Libro;
import com.camilo.biblioteca_virtual.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }
}
