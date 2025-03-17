package com.egg.electro_catalog.mapper;
import org.springframework.stereotype.Component;

import com.egg.electro_catalog.model.dtos.UsuarioCreateDTO;
import com.egg.electro_catalog.model.dtos.UsuarioUpdateDTO;
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

    @Override
    public Usuario toUsuario(UsuarioUpdateDTO usuarioUpdateDTO) {
        if ( usuarioUpdateDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNombre( usuarioUpdateDTO.getNombre() );
        usuario.setApellido( usuarioUpdateDTO.getApellido() );
        usuario.setEmail( usuarioUpdateDTO.getEmail() );
        

        return usuario;
    }

    @Override
    public UsuarioUpdateDTO toUsuarioUpdateDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioUpdateDTO usuarioUpdateDTO = new UsuarioUpdateDTO();

        usuarioUpdateDTO.setNombre( usuario.getNombre() );
        usuarioUpdateDTO.setApellido( usuario.getApellido() );
        usuarioUpdateDTO.setEmail( usuario.getEmail() );


        return usuarioUpdateDTO;
    }

}
