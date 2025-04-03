package com.bibliotecaapi.libreriaapi.controladores;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotecaapi.libreriaapi.entidades.Autor;
import com.bibliotecaapi.libreriaapi.entidades.Editorial;
import com.bibliotecaapi.libreriaapi.modelos.AutorModificarEstadoDTO;
import com.bibliotecaapi.libreriaapi.servicios.AutorServicio;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/autor")
public class AutorControlador {
    // Instancio al servicio, para poder acceder a sus métodos.

    private final AutorServicio autorServicio;

    @PostMapping("crear")
    public ResponseEntity<Object> crearAutor(@RequestParam String nombre) {
        try {
            autorServicio.crearAutor(nombre);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("listar")
    public ResponseEntity<Object> listarEditorial() {
        List<Autor> response = autorServicio.listarAutorOrd();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("modificar")
    public ResponseEntity<Void> modificarAutor(@RequestParam String nombre, @RequestParam String id) {
        try {
            UUID uuidId = UUID.fromString(id);
            autorServicio.modificarAutor(nombre, uuidId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/modificarDTO")
    public ResponseEntity<AutorModificarEstadoDTO> modificarAutor(@RequestBody AutorModificarEstadoDTO autorDTO) {
        AutorModificarEstadoDTO autorActualizado = autorServicio.modificarAutor(autorDTO);
        return new ResponseEntity<>(autorActualizado, HttpStatus.OK);
    }

}