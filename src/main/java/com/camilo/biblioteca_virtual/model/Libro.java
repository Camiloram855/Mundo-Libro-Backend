package com.camilo.biblioteca_virtual.model;

import jakarta.persistence.*;


@Entity
@Table(name = "Libro") // Asegura que se use la tabla correcta
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String titulo;

    @Column(nullable = true)
    private String autor;

    @Column(nullable = true)
    private String imagen;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String descripcion;
    private double precio;

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Constructor vacío
    public Libro() {
    }

    // Constructor con parámetros (sin precio, porque no existe en tu tabla)
    public Libro(String titulo, String autor, String imagen, String descripcion) {
        this.titulo = titulo;
        this.autor = autor;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    // ====== Getters y Setters ======
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
