package com.egg.electro_catalog.controllers;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.egg.electro_catalog.annotation.ViewBasePath;
import com.egg.electro_catalog.exception.ElectroCatalogException;
import com.egg.electro_catalog.model.entities.Articulo;
import com.egg.electro_catalog.model.entities.Fabrica;
import com.egg.electro_catalog.services.FabricaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


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
    
    @PostMapping("/registrar")
    public String registrar(@Valid @ModelAttribute Fabrica fabrica,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "form"; // Vuelve a la vista con errores
        }

        try {
            fabricaService.registrar(fabrica);
            redirectAttributes.addFlashAttribute("exito", "La fábrica se guardó exitosamente.");
            return "redirect:/fabrica/lista";
        } catch (ElectroCatalogException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/fabrica/registrar"; // Vuelve al index con los errores
        }
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable UUID id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("fabrica", fabricaService.getOne(id));
            return "modificar";
        } catch (ElectroCatalogException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/fabrica/lista"; // Vuelve al index con los errores
        }
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable UUID id, 
                            @Valid @ModelAttribute Fabrica fabrica, 
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "modificar"; // Vuelve a la vista con errores
        }

        try {
            fabricaService.modificar(id, fabrica);
            redirectAttributes.addFlashAttribute("exito", "La fábrica se actualizó exitosamente.");
            return "redirect:/fabrica/lista"; 
        } catch (ElectroCatalogException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/fabrica/lista"; // Vuelve al index con los errores
        }
    }
    
}
