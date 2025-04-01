package com.bibliotecaapi.libreriaapi.entidades;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nombre;

    @Column(name = "activa")
    private boolean activa;

}
