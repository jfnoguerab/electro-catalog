package com.egg.electro_catalog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.electro_catalog.model.dtos.UsuarioCreateDTO;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/")
public class PortalController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/registrar")
    public String registrar(Model model) {
        model.addAttribute("usuario", new UsuarioCreateDTO());
        return "login/form";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, Model model) {
        try {
            if (error != null) {
                model.addAttribute("error", "Usuario y/o contraseña incorrectos.");
            }
            return "login/index";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login/index";
        }
    }
    
}
