package com.example.speciesmvc.controllers;

import com.example.speciesmvc.entities.Species;
import com.example.speciesmvc.services.SpeciesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("species")
public class SpeciesController {
    @Autowired
    private SpeciesService speciesService;

    @GetMapping()
    public String listAllSpecies(Model model) {
        Iterable<Species> species = speciesService.species();
        model.addAttribute("listSpecies", species);
        return "species/list_species";
    }

    @GetMapping("{id}")
    public String getOneSpecies(@PathVariable Integer id, Model model) {
        Species species = speciesService.findSpeciesById(id);
        if (species == null) return "species/species_not_found";
        model.addAttribute("species", species);
        return "species/display_species";
    }

    @GetMapping("create")
    public String createSpecies(Model model) {
        model.addAttribute("species", new Species());
        return "species/create_species";
    }

    @GetMapping("delete/{id}")
    public String deleteSpecies(@PathVariable Integer id) {
        speciesService.deleteSpeciesById(id);
        return "redirect:/species";
    }

    @GetMapping("update/{id}")
    public String updateSpecies(@PathVariable Integer id, Model model) {
        Species species = speciesService.findSpeciesById(id);
        if (species == null) return "species/species_not_found";
        model.addAttribute("species", species);
        return "species/update_species";
    }

    @PostMapping("update")
    public String updateSpecies(@Valid Species species, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "species/update_species";
        speciesService.save(species);
        return "redirect:/species";
    }

    @PostMapping("create")
    public String createSpecies(@Valid Species species, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "species/create_species";
        speciesService.save(species);
        return "redirect:/species";
    }
}
