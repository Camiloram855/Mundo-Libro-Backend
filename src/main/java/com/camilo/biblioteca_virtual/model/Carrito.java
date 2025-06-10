package com.camilo.biblioteca_virtual.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private boolean activo = true;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    private List<DetalleCarrito> detalles;

    // Getters y Setters
    // ...
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
