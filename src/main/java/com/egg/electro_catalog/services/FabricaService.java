package com.egg.electro_catalog.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.electro_catalog.exception.ElectroCatalogException;
import com.egg.electro_catalog.model.entities.Fabrica;
import com.egg.electro_catalog.repositories.FabricaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FabricaService {


    private final FabricaRepository fabricaRepository;


    @Transactional
    public Fabrica registrar(Fabrica nuevaFabrica) {
        return fabricaRepository.save(nuevaFabrica);
    }

    @Transactional
    public Fabrica modificar(UUID id, Fabrica fabrica) {
        // Validamos que tanto el ID enviado por sea igual al del objeto
        if(!id.equals(fabrica.getId()))
        {
            throw new ElectroCatalogException("¡El ID de la fábrica que intentas modificar no coincide!");
        }

        // Validamos que Exista la fábrica en la BD
        if(!fabricaRepository.existsById(fabrica.getId())) {
            throw new ElectroCatalogException("¡La fábrica que intentas modificar no existe!");
        }

        // Actualizamos la instancia
       return fabricaRepository.save(fabrica);
    }

    @Transactional(readOnly = true)
    public List<Fabrica> listarTodos() {
        return fabricaRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Fabrica getOne(UUID id) {
        return fabricaRepository.findById(id).orElseThrow(() -> new ElectroCatalogException("La fábrica no está registrada."));
    }
}
