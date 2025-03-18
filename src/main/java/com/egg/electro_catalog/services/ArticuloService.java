package com.egg.electro_catalog.services;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.electro_catalog.exception.ElectroCatalogException;
import com.egg.electro_catalog.model.entities.Articulo;
import com.egg.electro_catalog.repositories.ArticuloRepository;
import com.egg.electro_catalog.repositories.FabricaRepository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticuloService {

    private final FabricaRepository fabricaRepository;
    private final ArticuloRepository articuloRepository;
    private final AtomicInteger atomicInteger = new AtomicInteger(0); // Inicia en 0

    @PostConstruct
    public void inicializarContador() {
        Articulo articulo = articuloRepository.findTopByOrderByNroDesc();
        if (articulo != null) {
            atomicInteger.set(articulo.getNro() + 1);
        }
    }

    @Transactional
    public Articulo registrar(Articulo nuevoArticulo){
        // Validamos que la fábrica no sea null y que exista en la BD
        if(nuevoArticulo.getFabrica().getId() == null || 
            !fabricaRepository.existsById(nuevoArticulo.getFabrica().getId())
        ) {
            throw new ElectroCatalogException("La fábrica del artículo no está registrada."); 
        }

        nuevoArticulo.setNro(atomicInteger.getAndIncrement()); // Asigna y aumenta

        return articuloRepository.save(nuevoArticulo);
    }

    @Transactional
    public Articulo modificar(UUID id, Articulo articulo) {

        // Validamos que tanto el ID enviado por sea igual al del objeto
        if(!id.equals(articulo.getId()))
        {
            throw new ElectroCatalogException("¡El ID del artículo que intentas modificar no coincide!");
        }

        // Validamos que Exista el artículo en la BD
        if(!articuloRepository.existsById(articulo.getId())) {
            throw new ElectroCatalogException("¡El artículo que intentas modificar no existe!");
        }

        // Validamos que Exista la fábrica en la BD
        if(articulo.getFabrica().getId() == null || !fabricaRepository.existsById(articulo.getFabrica().getId())){
            throw new ElectroCatalogException("La fábrica del artículo no está registrada.");
        }

        // Actualizamos la instancia
       return articuloRepository.save(articulo);
    }

    @Transactional(readOnly = true)
    public List<Articulo> listarTodos() {
        return articuloRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Articulo getOne(UUID id) {
        return articuloRepository.findById(id).orElseThrow(() -> new ElectroCatalogException("El artículo no está registrado."));
    }
}
