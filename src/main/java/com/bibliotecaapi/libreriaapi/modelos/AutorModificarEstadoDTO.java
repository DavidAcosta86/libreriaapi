package com.bibliotecaapi.libreriaapi.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorModificarEstadoDTO {
    private UUID id;
    private String nombre;
    private Boolean activo;
}