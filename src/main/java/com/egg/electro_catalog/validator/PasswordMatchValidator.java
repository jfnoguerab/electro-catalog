package com.egg.electro_catalog.validator;

import com.egg.electro_catalog.annotation.PasswordMatch;
import com.egg.electro_catalog.model.dtos.UsuarioCreateDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UsuarioCreateDTO> {

    @Override
    public boolean isValid(UsuarioCreateDTO usuarioCreateDTO, ConstraintValidatorContext context) {
        if (usuarioCreateDTO.getPassword() == null || usuarioCreateDTO.getConfirmPassword() == null) {
            return true; // No se valida si los campos son nulos
        }
        return usuarioCreateDTO.getPassword().equals(usuarioCreateDTO.getConfirmPassword());
    }

}
