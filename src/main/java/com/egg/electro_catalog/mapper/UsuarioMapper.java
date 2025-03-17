package com.egg.electro_catalog.mapper;

import com.egg.electro_catalog.model.dtos.UsuarioCreateDTO;
import com.egg.electro_catalog.model.dtos.UsuarioUpdateDTO;
import com.egg.electro_catalog.model.entities.Usuario;

public interface UsuarioMapper {
    Usuario toUsuario(UsuarioCreateDTO usuarioCreateDTO);
    UsuarioCreateDTO toUsuarioCreateDTO(Usuario usuario);

    Usuario toUsuario(UsuarioUpdateDTO usuarioUpdateDTO);
    UsuarioUpdateDTO toUsuarioUpdateDTO(Usuario usuario);
}
