package com.egg.electro_catalog.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egg.electro_catalog.model.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
     // Usando Convención de Nombres de Spring Data JPA
    public Optional<Usuario> findByEmail(String email);
}
