package com.pruebatecnica.prueba.services;

import com.pruebatecnica.prueba.entities.Equipo;
import com.pruebatecnica.prueba.repository.EquipoRepository;
import java.util.List;
import java.util.Optional;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.stereotype.Service;


@Service
public class EquipoService implements IEquipoService {

    @Autowired
    private EquipoRepository equipoRepository;


    public List<Equipo> getAll() {
        return (List<Equipo>) equipoRepository.findAll();
    }


    public Equipo getById(Long id) {
        return equipoRepository.findById(id).orElse(null);
    }


    public List<Equipo> searchByName(String nombre) {
        return equipoRepository.findByNombreContaining(nombre);
    }


    public Equipo save(Equipo equipo) {
        if (equipo.getNombre() == null || equipo.getLiga() == null || equipo.getPais() == null) {
            throw new IllegalArgumentException("Los datos del equipo son inválidos");
        }
        List<Equipo> equipoEncontrado = searchByName(equipo.getNombre());
        if (!equipoEncontrado.isEmpty()) {
            throw new IllegalArgumentException("Los datos del equipo son inválidos");
        }
        return equipoRepository.save(equipo);
    }


    public void remove(Long id) {
        Optional<Equipo> equipoOptional = equipoRepository.findById(id);
        if (equipoOptional.isPresent()) {
            equipoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Equipo no encontrado");
        }
    }



    public Equipo update(Long id, Equipo equipo) {
        Equipo equipoExistente = equipoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipo no encontrado"));

        equipoExistente.setNombre(equipo.getNombre());
        equipoExistente.setLiga(equipo.getLiga());

        return equipoRepository.save(equipoExistente);
    }
}



