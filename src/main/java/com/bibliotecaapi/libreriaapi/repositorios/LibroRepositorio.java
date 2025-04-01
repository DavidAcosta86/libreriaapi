
package com.bibliotecaapi.libreriaapi.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bibliotecaapi.libreriaapi.entidades.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    // método para buscar libro por título
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    public Libro buscarPorTitulo(@Param("titulo") String titulo);

    // método para buscar libro por nombre de autor
    @Query("SELECT l FROM Libro l WHERE l.autor.nombre = :nombre")
    public List<Libro> buscarPorAutor(@Param("nombre") String nombre);
}
