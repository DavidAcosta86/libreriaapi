package com.bibliotecaapi.libreriaapi.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LibroCreatedDTO {

    private Long isbn;
    private String titulo;
    private String nombreEditorial;
    private String nombreAutor;

}
