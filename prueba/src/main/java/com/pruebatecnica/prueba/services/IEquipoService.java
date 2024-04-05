<<<<<<< HEAD
package com.pruebatecnica.prueba.services;

import com.pruebatecnica.prueba.entities.Equipo;
import java.util.List;

public interface IEquipoService {
    List<Equipo> getAll();
    Equipo getById(Long id);
    List<Equipo> searchByName(String nombre);
    Equipo save(Equipo equipo);
    void remove(Long id);
    Equipo update(Long id, Equipo equipo);
}
=======
package com.pruebatecnica.prueba.services;

import com.pruebatecnica.prueba.entities.Equipo;
import java.util.List;

public interface IEquipoService {
    List<Equipo> getAll();
    Equipo getById(Long id);
    List<Equipo> searchByName(String nombre);
    void save(Equipo equipo);
    void remove(Long id);
    Equipo update(Long id, Equipo equipo);
}
>>>>>>> origin/main
