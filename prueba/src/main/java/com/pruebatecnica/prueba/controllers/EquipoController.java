package com.pruebatecnica.prueba.controllers;

import com.pruebatecnica.prueba.entities.Equipo;
import com.pruebatecnica.prueba.services.IEquipoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/equipos"})
public class EquipoController {
    @Autowired
    private IEquipoService service;

    public EquipoController() {
    }
    @GetMapping
    public List<Equipo> getAll() {
        return (List<Equipo>) service.getAll();
    }

    @GetMapping({"/{id}"})
    public Equipo getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/buscar")
    public List<Equipo> searchByName(@RequestParam String nombre) {
        return service.searchByName(nombre);
    }

    @PostMapping
    public void save(@RequestBody Equipo equipo){
        service.save(equipo);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id){
        service.remove(id);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Equipo equipo) {
        service.update(id, equipo);
    }
}

