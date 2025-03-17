package com.egg.electro_catalog.model.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "fabrica", schema = "electro_catalog")
public class Fabrica extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    
    @Setter
    @NotNull(message = "El nombre de la fábrica no puede ser nulo")
    @NotBlank(message = "El nombre de la fábrica no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre de la fábrica debe tener entre 3 y 50 caracteres")
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;
}
