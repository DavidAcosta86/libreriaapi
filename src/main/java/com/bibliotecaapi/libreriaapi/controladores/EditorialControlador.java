package com.bibliotecaapi.libreriaapi.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotecaapi.libreriaapi.entidades.Editorial;
import com.bibliotecaapi.libreriaapi.modelos.EditorialCreateDTO;
import com.bibliotecaapi.libreriaapi.servicios.EditorialServicio;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/editorial")

public class EditorialControlador {

    private final EditorialServicio editorialServicio;

    @PostMapping("crear")
    public ResponseEntity<Object> crearEditorial(@RequestParam String nombre) {
        try {
            editorialServicio.crearEditorial(nombre);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/crearDTO")
    public ResponseEntity<EditorialCreateDTO> crearEditorial(@RequestBody EditorialCreateDTO editorialDTO) {
        EditorialCreateDTO nuevaEditorial = editorialServicio.crearEditorial(editorialDTO);
        return new ResponseEntity<>(nuevaEditorial, HttpStatus.CREATED);
    }

    @GetMapping("listar")
    public ResponseEntity<Object> listarEditorial() {
        List<Editorial> response = editorialServicio.listarEditorialesOrd();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("listar_activas")
    public ResponseEntity<Object> listarEditorialActivas() {
        List<Editorial> response = editorialServicio.listarEditorialesActivas();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("listar_inactivas")
    public ResponseEntity<Object> listarEditorialInActivas() {
        List<Editorial> response = editorialServicio.listarEditorialesInActivas();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
