package com.pruebatecnica.prueba;

import com.pruebatecnica.prueba.entities.Equipo;
import com.pruebatecnica.prueba.repository.EquipoRepository;
import com.pruebatecnica.prueba.services.EquipoService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EquipoServiceTest {

    @Mock
    private EquipoRepository equipoRepository;

    @InjectMocks
    private EquipoService equipoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        List<Equipo> equipos = new ArrayList<>();
        equipos.add(new Equipo(1L, "Real Madrid", "La Liga", "España"));
        equipos.add(new Equipo(2L, "FC Barcelona", "La Liga", "España"));

        when(equipoRepository.findAll()).thenReturn(equipos);

        List<Equipo> result = equipoService.getAll();

        assertEquals(2, result.size());
        assertEquals("Real Madrid", result.get(0).getNombre());
        assertEquals("La Liga", result.get(0).getLiga());
        assertEquals("España", result.get(0).getPais());

    }

    @Test
    public void testGetById() {
        Equipo equipo = new Equipo(1L, "Real Madrid", "La Liga", "España");
        when(equipoRepository.findById(1L)).thenReturn(Optional.of(equipo));

        Equipo result = equipoService.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Real Madrid", result.getNombre());
        assertEquals("La Liga", result.getLiga());
        assertEquals("España", result.getPais());
    }

    @Test
    public void testGetByIdNotFound() {
        when(equipoRepository.findById(1L)).thenReturn(Optional.empty());

        Equipo result = equipoService.getById(1L);

        assertNull(result);
    }

    @Test
    public void testSearchByName() {
        String nombre = "Real Madrid";
        List<Equipo> equipos = new ArrayList<>();
        equipos.add(new Equipo(1L, "Real Madrid", "La Liga", "España"));

        when(equipoRepository.findByNombreContaining(nombre)).thenReturn(equipos);

        List<Equipo> result = equipoService.searchByName(nombre);

        assertEquals(1, result.size());
        assertEquals("Real Madrid", result.get(0).getNombre());
        assertEquals("La Liga", result.get(0).getLiga());
        assertEquals("España", result.get(0).getPais());
    }

    @Test
    public void testSearchByNameNotFound() {
        String nombre = "Chelsea";

        when(equipoRepository.findByNombreContaining(nombre)).thenReturn(new ArrayList<>());

        List<Equipo> result = equipoService.searchByName(nombre);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSaveEquipo() {
        Equipo equipoToSave = new Equipo(null, "Nuevo Equipo", "Nueva Liga", "Nuevo Pais");

        when(equipoRepository.findByNombreContaining("Nuevo Equipo")).thenReturn(new ArrayList<>());
        when(equipoRepository.save(any(Equipo.class))).thenReturn(equipoToSave);

        Equipo result = equipoService.save(equipoToSave);

        assertNotNull(result);
        assertEquals("Nuevo Equipo", result.getNombre());
        assertEquals("Nueva Liga", result.getLiga());
        assertEquals("Nuevo Pais", result.getPais());
    }

    @Test
    public void testSaveEquipoAlreadyExists() {
        Equipo existingEquipo = new Equipo(1L, "Existing Equipo", "Liga 1", "Pais 1");
        when(equipoRepository.findByNombreContaining("Existing Equipo")).thenReturn(List.of(existingEquipo));

        Equipo equipoToSave = new Equipo(null, "Existing Equipo", "Liga 1", "Pais 1");

        assertThrows(IllegalArgumentException.class, () -> {
            equipoService.save(equipoToSave);
        });
    }

    @Test
    public void testRemoveEquipo() {
        Equipo existingEquipo = new Equipo(1L, "Existing Equipo", "Liga 1", "Pais 1");
        when(equipoRepository.findById(1L)).thenReturn(Optional.of(existingEquipo));

        equipoService.remove(1L);

        verify(equipoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testRemoveEquipoDoesNotExist() {
        when(equipoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            equipoService.remove(1L);
        });
    }

    @Test
    public void testUpdateEquipo() {
        Equipo existingEquipo = new Equipo(1L, "Existing Equipo", "Liga 1", "Pais 1");
        Equipo updatedEquipo = new Equipo(1L, "Updated Equipo", "Updated Liga", "Updated Pais");

        when(equipoRepository.findById(1L)).thenReturn(Optional.of(existingEquipo));
        when(equipoRepository.save(existingEquipo)).thenReturn(updatedEquipo);

        Equipo result = equipoService.update(1L, updatedEquipo);

        assertNotNull(result);
        assertEquals("Updated Equipo", result.getNombre());
        assertEquals("Updated Liga", result.getLiga());
        assertEquals("Updated Pais", result.getPais());
    }

    @Test
    public void testUpdateEquipoDoesNotExist() {
        when(equipoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            equipoService.update(1L, new Equipo());
        });
    }
}
