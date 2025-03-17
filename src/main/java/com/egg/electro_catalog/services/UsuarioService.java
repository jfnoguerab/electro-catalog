package com.egg.electro_catalog.services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.electro_catalog.exception.ElectroCatalogException;
import com.egg.electro_catalog.mapper.UsuarioMapper;
import com.egg.electro_catalog.model.dtos.UsuarioCreateDTO;
import com.egg.electro_catalog.model.dtos.UsuarioUpdateDTO;
import com.egg.electro_catalog.model.entities.Usuario;
import com.egg.electro_catalog.model.enums.Rol;
import com.egg.electro_catalog.repositories.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Transactional
    public Usuario registrar(UsuarioCreateDTO nuevoUsuarioDTO) {
        // Mapeamos y recibimos la instancia
        Usuario usuario = usuarioMapper.toUsuario(nuevoUsuarioDTO);
        // Encriptamos la contraseña
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        // Agignamos el Rol por defecto
        usuario.setRol(Rol.USER);

        // Registramos la instancia
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario modificar(UUID id, UsuarioUpdateDTO usuarioDTO) {

        // Validamos que tanto el ID enviado por sea igual al del objeto
        if(!id.equals(usuarioDTO.getId()))
        {
            throw new ElectroCatalogException("¡El ID del usuario que intentas modificar no coincide!");
        }

        // Validamos que Exista el usuario en la BD
        Usuario usuarioOrig = usuarioRepository.findById(id).orElseThrow(() -> new ElectroCatalogException("¡El usuario que intentas modificar no existe!"));

        // Mapeamos y recibimos la instancia
        Usuario usuarioUp = usuarioMapper.toUsuario(usuarioDTO);

         if(!usuarioOrig.getNombre().equalsIgnoreCase(usuarioUp.getNombre())) {
            usuarioOrig.setNombre(usuarioUp.getNombre());
         }
         
         if(!usuarioOrig.getNombre().equalsIgnoreCase(usuarioUp.getNombre())) {
            usuarioOrig.setNombre(usuarioUp.getNombre());
         }
         
         if(!usuarioOrig.getEmail().equalsIgnoreCase(usuarioUp.getEmail())) {
            usuarioOrig.setEmail(usuarioUp.getEmail());
         }


        // Actualizamos la instancia
       return usuarioRepository.save(usuarioOrig);
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario getOne(UUID id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ElectroCatalogException("El usuario no está registrado."));
    }
}
