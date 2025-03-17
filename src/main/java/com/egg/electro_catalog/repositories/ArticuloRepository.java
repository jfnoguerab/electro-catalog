package com.egg.electro_catalog.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egg.electro_catalog.model.entities.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, UUID> {

}
