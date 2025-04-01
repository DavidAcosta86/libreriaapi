
package com.bibliotecaapi.libreriaapi.repositorios;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bibliotecaapi.libreriaapi.entidades.Editorial;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, UUID> {

    @Query("SELECT e FROM Editorial e ORDER BY e.nombre ASC")
    List<Editorial> findAllOrderedByName();

}
