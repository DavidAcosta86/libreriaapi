package com.bibliotecaapi.libreriaapi.servicios;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bibliotecaapi.libreriaapi.entidades.Autor;
import com.bibliotecaapi.libreriaapi.excepciones.MiException;
import com.bibliotecaapi.libreriaapi.repositorios.AutorRepositorio;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorServicio {

    private final AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MiException {

        validar(nombre);

        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setActivo(true);

        autorRepositorio.save(autor);
    }

    @Transactional(readOnly = true)
    public List<Autor> listarAutores() {

        List<Autor> autores = new ArrayList<>();

        autores = autorRepositorio.findAll();
        return autores;
    }

    @Transactional(readOnly = true)
    public List<Autor> listarAutorOrd() {

        List<Autor> autores = new ArrayList<>();
        Sort sort = Sort.by(Sort.Direction.ASC, "nombre");
        autores = autorRepositorio.findAll(sort);
        return autores;
    }

    @Transactional
    public void modificarAutor(String nombre, UUID id) throws MiException {

        validar(nombre);
        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            autorRepositorio.save(autor);

        } else {
            throw new MiException("No se encontró un autor con el ID especificado");
        }
    }

    @Transactional
    public void eliminar(UUID id) throws MiException {
        Optional<Autor> autorOpt = autorRepositorio.findById(id);
        if (autorOpt.isPresent()) {
            Autor autor = autorOpt.get();
            autor.setActivo(false);
            autorRepositorio.save(autor);

        } else {
            throw new MiException("No se encontró un autor con el ID especificado para eliminar");
        }
    }

    @Transactional(readOnly = true)
    public Autor getOne(UUID id) {
        return autorRepositorio.findById(id).orElse(null);
    }

    private void validar(String nombre) throws MiException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new MiException("El nombre no puede ser nulo o estar vacío");
        }
    }

    // @Transactional
    // public void modificarAutor(String nombre, UUID id) {
    // UUID idUuid = id.
    // Optional<Autor> respuesta = autorRepositorio.findById(id);
    // if (respuesta.isPresent()) {
    // Autor autor = respuesta.get();
    // System.out.println(autor);
    // autor.setNombre(nombre);
    // autorRepositorio.save(autor);
    // }else {
    // throw new NoSuchElementException("No se encontró el autor con ID: " + id);
    // }}

}
