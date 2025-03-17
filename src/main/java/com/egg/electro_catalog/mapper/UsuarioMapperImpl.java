package com.egg.electro_catalog.mapper;
import org.springframework.stereotype.Component;

import com.egg.electro_catalog.model.dtos.UsuarioCreateDTO;
import com.egg.electro_catalog.model.entities.Usuario;

@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        if(usuarioCreateDTO == null) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNombre(usuarioCreateDTO.getNombre());
        usuario.setApellido(usuarioCreateDTO.getApellido());
        usuario.setEmail(usuarioCreateDTO.getEmail());
        usuario.setPassword(usuarioCreateDTO.getPassword());

        return usuario;
    }

    @Override
    public UsuarioCreateDTO toUsuarioCreateDTO(Usuario usuario) {
        if(usuario == null) {
            return null;
        }

        UsuarioCreateDTO usuarioCreateDTO = new UsuarioCreateDTO();
        usuarioCreateDTO.setNombre(usuario.getNombre());
        usuarioCreateDTO.setApellido(usuario.getApellido());
        usuarioCreateDTO.setEmail(usuario.getEmail());
        usuarioCreateDTO.setPassword(usuario.getPassword());

        return usuarioCreateDTO;
    }

}
