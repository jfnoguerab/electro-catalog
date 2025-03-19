package com.egg.electro_catalog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.egg.electro_catalog.model.dtos.UsuarioCreateDTO;
import com.egg.electro_catalog.services.UsuarioService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/")
@AllArgsConstructor
public class PortalController {

    private final UsuarioService usuarioService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/registrar")
    public String registrar(Model model) {
        model.addAttribute("usuario", new UsuarioCreateDTO());
        return "login/form";
    }

    @PostMapping("/registrar")
    public String registrar(@Valid @ModelAttribute("usuario") UsuarioCreateDTO usuarioDTO,
                            BindingResult result, 
                            RedirectAttributes redirectAttributes) {
                            System.out.println(result.getAllErrors());
        if (result.hasErrors()) {
            return "login/form";
        }
        try {
            usuarioService.registrar(usuarioDTO);
            redirectAttributes.addFlashAttribute("exito", 
                                            "El usuario se registró exitosamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/registrar"; // Vuelve al index con los errores
        }
        return "redirect:/login";
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
