package com.pruebatecnica.prueba.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@Entity
@Table(
        name = "Equipo"
)
@AllArgsConstructor
public class Equipo {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String liga;
    @NotBlank
    private String pais;

    public Equipo(){
    }


}

