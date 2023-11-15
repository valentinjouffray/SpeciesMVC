package com.example.speciesmvc.controllers;

import com.example.speciesmvc.entities.Animal;
import com.example.speciesmvc.services.AnimalService;
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
@RequestMapping("animals")
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @Autowired
    private SpeciesService speciesService;

    @GetMapping()
    public String listAllAnimals(Model model) {
        Iterable<Animal> animals = animalService.animals();
        model.addAttribute("listAnimals", animals);
        return "animals/list_animals";
    }

    @GetMapping("{id}")
    public String getOneAnimal(@PathVariable Integer id, Model model) {
        Animal animal = animalService.findAnimalById(id);
        if (animal == null) return "animals/animal_not_found";
        model.addAttribute(animal);
        return "animals/display_animal";
    }

    @GetMapping("create")
    public String createAnimal(Model model) {
        model.addAttribute(new Animal());
        model.addAttribute("speciesList", speciesService.species());
        return "animals/create_animal";
    }

    @GetMapping("delete/{id}")
    public String deleteAnimal(@PathVariable Integer id) {
        animalService.deleteAnimalById(id);
        return "redirect:/animals";
    }

    @PostMapping("create")
    public String createAnimal(@Valid Animal animal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "animals/create_animal";
        animalService.save(animal);
        return "redirect:/animals";
    }

    @GetMapping("update/{id}")
    public String updateAnimal(@PathVariable Integer id, Model model) {
        Animal animal = animalService.findAnimalById(id);
        if (animal == null) return "animals/animal_not_found";
        model.addAttribute("animal", animal);
        model.addAttribute("speciesList", speciesService.species());
        return "animals/update_animal";
    }

    @PostMapping("update")
    public String updateAnimal(@Valid Animal animal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "animals/update_animal";
        animalService.save(animal);
        return "redirect:/animals";
    }
}
