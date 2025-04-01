package com.bibliotecaapi.libreriaapi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bibliotecaapi.libreriaapi.entidades.Editorial;
import com.bibliotecaapi.libreriaapi.excepciones.MiException;
import com.bibliotecaapi.libreriaapi.repositorios.EditorialRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre) throws MiException {

        validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setActiva(true);

        editorialRepositorio.save(editorial);
    }

    @Transactional(readOnly = true)
    public List<Editorial> listarEditoriales() {

        List<Editorial> editoriales = new ArrayList<>();

        editoriales = editorialRepositorio.findAll();
        return editoriales;
    }

    @Transactional(readOnly = true)
    public List<Editorial> listarEditorialesOrd() {

        List<Editorial> editoriales = new ArrayList<>();

        editoriales = editorialRepositorio.findAllOrderedByName();
        return editoriales;
    }

    @Transactional
    public void modificarEditorial(UUID id, String nombre) throws MiException {
        validar(nombre);

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            editorialRepositorio.save(editorial);
        } else {
            throw new MiException("No se encontró una editorial con el ID especificado");
        }
    }

    @Transactional
    public void eliminar(UUID id) throws MiException {
        Optional<Editorial> editorialOpt = editorialRepositorio.findById(id);
        if (editorialOpt.isPresent()) {
            Editorial editorial = editorialOpt.get();
            editorial.setActiva(false);
            editorialRepositorio.save(editorial);
        } else {
            throw new MiException("No se encontró una editorial con el ID especificado");
        }
    }

    @Transactional(readOnly = true)
    public Editorial getOne(UUID id) {
        return editorialRepositorio.findById(id).orElse(null);
    }

    private void validar(String nombre) throws MiException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new MiException("El nombre de la editorial no puede ser nulo o estar vacío");
        }
    }
}
