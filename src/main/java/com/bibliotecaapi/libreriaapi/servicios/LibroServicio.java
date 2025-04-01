package com.bibliotecaapi.libreriaapi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bibliotecaapi.libreriaapi.entidades.Autor;
import com.bibliotecaapi.libreriaapi.entidades.Editorial;
import com.bibliotecaapi.libreriaapi.entidades.Libro;
import com.bibliotecaapi.libreriaapi.excepciones.MiException;
import com.bibliotecaapi.libreriaapi.repositorios.AutorRepositorio;
import com.bibliotecaapi.libreriaapi.repositorios.EditorialRepositorio;
import com.bibliotecaapi.libreriaapi.repositorios.LibroRepositorio;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, UUID idAutor, UUID idEditorial)
            throws MiException {

        validar(isbn, titulo, ejemplares, idAutor, idEditorial);

        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();

        if (autor == null) {
            throw new MiException("El autor especificado no existe.");
        }

        if (editorial == null) {
            throw new MiException("La editorial especificada no existe.");
        }

        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);
    }

    @Transactional(readOnly = true)
    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList<>();

        libros = libroRepositorio.findAll();
        return libros;
    }

    @Transactional
    public void modificarLibro(Long isbn, String titulo, Integer ejemplares, UUID idAutor, UUID idEditorial)
            throws MiException {

        validar(isbn, titulo, ejemplares, idAutor, idEditorial);

        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

        if (respuesta.isEmpty()) {
            throw new MiException("El libro especificado no existe.");
        }

        if (respuestaAutor.isEmpty()) {
            throw new MiException("El autor especificado no existe.");
        }

        if (respuestaEditorial.isEmpty()) {
            throw new MiException("La editorial especificada no existe.");
        }

        Libro libro = respuesta.get();
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAutor(respuestaAutor.get());
        libro.setEditorial(respuestaEditorial.get());

        libroRepositorio.save(libro);
    }

    @Transactional(readOnly = true)
    public Libro getOne(Long isbn) {
        return libroRepositorio.findById(isbn).orElse(null);
    }

    private void validar(Long isbn, String titulo, Integer ejemplares, UUID idAutor, UUID idEditorial)
            throws MiException {

        if (isbn == null) {
            throw new MiException("El ISBN no puede ser nulo.");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new MiException("El título no puede ser nulo o estar vacío.");
        }
        if (ejemplares == null) {
            throw new MiException("La cantidad de ejemplares no puede ser nula.");
        }
        if (idAutor == null) {
            throw new MiException("El ID del autor no puede ser nulo o estar vacío.");
        }
        if (idEditorial == null) {
            throw new MiException("El ID de la editorial no puede ser nulo o estar vacío.");
        }
    }

}
