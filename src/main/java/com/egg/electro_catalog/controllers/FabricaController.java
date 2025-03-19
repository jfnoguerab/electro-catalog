package com.egg.electro_catalog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egg.electro_catalog.annotation.ViewBasePath;
import com.egg.electro_catalog.model.entities.Fabrica;
import com.egg.electro_catalog.services.FabricaService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
@RequestMapping("/fabrica")
@ViewBasePath("fabrica/")
public class FabricaController {

    private final FabricaService fabricaService;

    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("fabricas", fabricaService.listarTodos());
        return "list";
    }
    
    @GetMapping("/registrar")
    public String registrar(Model model) {
        model.addAttribute("fabrica", new Fabrica());
        return "form";
    }
    
}
