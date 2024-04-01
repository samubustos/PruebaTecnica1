package com.pruebatecnica.prueba.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "Equipo"
)
public class Equipo {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String nombre;
    private String liga;
    private String pais;

    public Equipo() {
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getLiga() {
        return this.liga;
    }

    public String getPais() {
        return this.pais;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public void setLiga(final String liga) {
        this.liga = liga;
    }

    public void setPais(final String pais) {
        this.pais = pais;
    }
}

