package com.bibliotecaapi.libreriaapi.modelos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListarLibroActivoDTO {
    private String titulo;
    private Integer ejemplares;

}
