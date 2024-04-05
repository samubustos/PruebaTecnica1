<<<<<<< HEAD
package com.pruebatecnica.prueba.controllers;

import com.pruebatecnica.prueba.entities.Equipo;
import com.pruebatecnica.prueba.services.IEquipoService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipos")
@SecurityRequirement(name = "bearerAuth")
public class EquipoController {
    @Autowired
    private IEquipoService service;

    public EquipoController() {
    }
    @GetMapping
    public List<Equipo> getAll() {
        return (List<Equipo>) service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEquipo(@PathVariable Long id) {
        Equipo equipo = service.getById(id);
        if (equipo == null) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("mensaje", "Equipo no encontrado");
            body.put("codigo", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        } else {
            return ResponseEntity.ok(equipo);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<Object> searchByName(@RequestParam String nombre) {
        List<Equipo> equipos = service.searchByName(nombre);
        if (equipos.isEmpty()) {
            String mensaje = "Equipo no encontrado";
            int codigo = HttpStatus.NOT_FOUND.value();
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("mensaje", mensaje);
            body.put("codigo", codigo);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        } else {
            return ResponseEntity.ok().body(equipos);
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody Equipo equipo) {
        try {
            Equipo equipoCreado = service.save(equipo);
            return ResponseEntity.status(HttpStatus.CREATED).body(equipoCreado);
        } catch (IllegalArgumentException e) {
            String mensaje = "La solicitud es inválida";
            int codigo = HttpStatus.BAD_REQUEST.value();
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("mensaje", mensaje);
            body.put("codigo", codigo);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(@PathVariable Long id){
        try {
            service.remove(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            String mensaje = "Equipo no encontrado";
            int codigo = HttpStatus.NOT_FOUND.value();
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("mensaje", mensaje);
            body.put("codigo", codigo);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Equipo equipo) {
        try {
            Equipo equipoActualizado = service.update(id, equipo);
            return ResponseEntity.ok(equipoActualizado);
        } catch (EntityNotFoundException ex) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("mensaje", "Equipo no encontrado");
            body.put("codigo", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }
}
=======
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

>>>>>>> origin/main
