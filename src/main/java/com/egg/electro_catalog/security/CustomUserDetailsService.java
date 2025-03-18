package com.egg.electro_catalog.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.egg.electro_catalog.model.entities.Usuario;
import com.egg.electro_catalog.repositories.UsuarioRepository;
import com.egg.electro_catalog.utils.permission.PermissionUtils;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo: " + email));

        List<GrantedAuthority> permissions = PermissionUtils.getPermissionsByRol(usuario.getRol());

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("session_user", usuario);

        return new User(usuario.getEmail(), usuario.getPassword(), permissions);
    }

}
