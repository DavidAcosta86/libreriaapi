
package com.bibliotecaapi.libreriaapi.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bibliotecaapi.libreriaapi.entidades.Autor;

public interface AutorRepositorio extends JpaRepository<Autor, UUID> {

}
