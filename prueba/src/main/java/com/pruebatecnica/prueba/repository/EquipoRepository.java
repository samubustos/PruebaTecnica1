package com.pruebatecnica.prueba.repository;

import com.pruebatecnica.prueba.entities.Equipo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipoRepository extends CrudRepository<Equipo, Long> {
    List<Equipo> findByNombreContaining(String nombre);
    Optional<Equipo> findByNombre(String nombre);
}
