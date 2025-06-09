package com.camilo.libreria.repository;

import com.camilo.libreria.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Puedes agregar métodos personalizados si los necesitas
}
