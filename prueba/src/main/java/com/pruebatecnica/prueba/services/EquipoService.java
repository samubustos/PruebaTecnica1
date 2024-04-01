package com.pruebatecnica.prueba.services;

import com.pruebatecnica.prueba.entities.Equipo;
import com.pruebatecnica.prueba.repository.EquipoRepository;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipoService implements IEquipoService {
    @Autowired
    private EquipoRepository repository;

    public EquipoService(EquipoRepository equipoRepository) {
        this.repository = repository;
    }
    @Override
    public List<Equipo> getAll() {

        return (List<Equipo>) repository.findAll();
    }

    @Override
    public Equipo getById(Long id){
        return repository.findById(id).get();
    }

    @Override
    public List<Equipo> searchByName(String nombre) {
        return repository.findByNombreContaining(nombre);
    }

    @Override
    public void save(Equipo equipo){
        repository.save(equipo);
    }

    @Override
    public void remove(Long id){
        repository.deleteById(id);
    }

    @Override
    public Equipo update(Long id, Equipo equipo) {
        Equipo equipoExistente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipo no encontrado con ID: " + id));

        equipoExistente.setNombre(equipo.getNombre());
        equipoExistente.setLiga(equipo.getLiga());
        equipoExistente.setPais(equipo.getPais());

        return repository.save(equipoExistente);
    }

}

