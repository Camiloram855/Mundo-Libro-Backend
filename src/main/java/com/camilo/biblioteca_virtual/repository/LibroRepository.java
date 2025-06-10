package com.camilo.biblioteca_virtual.repository;

import com.camilo.biblioteca_virtual.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;




@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Puedes agregar métodos personalizados si los necesitas
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

}
