package com.bibliotecaapi.libreriaapi.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Libro {
    @Id
    private Long isbn;
    @Column(name = "activo")
    private boolean activo;
    private String titulo;
    private Integer ejemplares;

    @ManyToOne
    private Autor autor;

    @ManyToOne
    private Editorial editorial;

}
