package com.bibliotecaapi.libreriaapi.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotecaapi.libreriaapi.entidades.Editorial;
import com.bibliotecaapi.libreriaapi.modelos.LibroCreateDTO;
import com.bibliotecaapi.libreriaapi.modelos.LibroCreatedDTO;
import com.bibliotecaapi.libreriaapi.modelos.ListarLibroActivoDTO;
import com.bibliotecaapi.libreriaapi.servicios.LibroServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/libro")
public class LibroControlador {
    @Autowired
    private LibroServicio libroServicio;

    @PostMapping("/crear")
    public ResponseEntity<Object> crearLibro(@RequestBody LibroCreateDTO libroDTO) {
        try {
            LibroCreatedDTO show = libroServicio.crearLibro(libroDTO);
            return new ResponseEntity<>(show, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/listar-activos")
    public ResponseEntity listarActivos() {
        try {
            List<ListarLibroActivoDTO> librosActivos = libroServicio.listarLibroActivo();
            return new ResponseEntity(librosActivos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
