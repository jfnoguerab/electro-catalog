package com.egg.electro_catalog.mapper;

import com.egg.electro_catalog.model.dtos.UsuarioCreateDTO;
import com.egg.electro_catalog.model.entities.Usuario;

public interface UsuarioMapper {
    Usuario toUsuario(UsuarioCreateDTO usuarioCreateDTO);
    UsuarioCreateDTO toUsuarioCreateDTO(Usuario usuario);
}
