package com.egg.electro_catalog.model.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "articulo", schema = "electro_catalog")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro", nullable = false)
    private Long nro;

    @Setter
    @NotNull(message = "El nombre del artículo no puede ser nulo")
    @NotBlank(message = "El nombre del artículo no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del artículo debe tener entre 3 y 50 caracteres")
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;
    
    @Setter
    @Max(value = 250, message = "La descripción artículo debe tener máximo 250 caracteres")
    @Column(name = "descripcion", length = 250, nullable = false)
    private String descripcion;

    @Setter
    @ManyToOne
    @JoinColumn(name = "fabrica_id", nullable = false)
    @NotNull(message = "Debe seleccionar una fábrica")
    private Fabrica fabrica;

}
