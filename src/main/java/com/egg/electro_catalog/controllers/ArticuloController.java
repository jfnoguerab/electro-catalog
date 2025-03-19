package com.egg.electro_catalog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.egg.electro_catalog.annotation.ViewBasePath;
import com.egg.electro_catalog.exception.ElectroCatalogException;
import com.egg.electro_catalog.model.entities.Articulo;
import com.egg.electro_catalog.model.entities.Fabrica;
import com.egg.electro_catalog.services.ArticuloService;
import com.egg.electro_catalog.services.FabricaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
@RequestMapping("/articulo")
@ViewBasePath("articulo/")
public class ArticuloController {

    private final ArticuloService articuloService;
    private final FabricaService fabricaService;

    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("articulos", articuloService.listarTodos());
        return "list";
    }
    
    @GetMapping("/registrar")
    public String registrar(Model model) {
        model.addAttribute("articulo", new Articulo());
        model.addAttribute("fabricas", fabricaService.listarTodos());
        return "form";
    }

    @PostMapping("/registrar")
    public String registrar(@Valid @ModelAttribute Articulo articulo,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "form"; // Vuelve a la vista con errores
        }

        try {
            articuloService.registrar(articulo);
            redirectAttributes.addFlashAttribute("exito", "El artículo se guardó exitosamente.");
            return "redirect:/";
        } catch (ElectroCatalogException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/"; // Vuelve al index con los errores
        }
    }
    
}
