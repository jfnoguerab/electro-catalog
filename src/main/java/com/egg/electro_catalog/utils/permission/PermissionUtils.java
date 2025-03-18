package com.egg.electro_catalog.utils.permission;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.egg.electro_catalog.model.enums.Rol;

public final class PermissionUtils {
    private PermissionUtils(){
        // Private constructor to prevent instantiation
    }

    public static List<GrantedAuthority> getPermissionsByRol(Rol rol) {
        return switch (rol) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("READ_PRIVILEGES"),
                    new SimpleGrantedAuthority("WRITE_PRIVILEGES"),
                    new SimpleGrantedAuthority("DELETE_PRIVILEGES")
            );
            case USER -> List.of(
                    new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("READ_PRIVILEGES")
            );
        };
    }
}
