package com.example.speciesmvc.controllers;

import com.example.speciesmvc.entities.Person;
import com.example.speciesmvc.services.PersonService;
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
@RequestMapping("persons")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping()
    public String listAllPersons(Model model) {
        Iterable<Person> persons = personService.persons();
        model.addAttribute("listPersons", persons);
        return "persons/list_persons";
    }

    @GetMapping("{id}")
    public String getOnePerson(@PathVariable Integer id, Model model) {
        Person person = personService.findPersonById(id);
        System.out.println("person = " + person);
        if (person == null) return "persons/person_not_found";
        model.addAttribute(person);
        return "persons/display_person";
    }

    @GetMapping("create")
    public String createPerson(Model model) {
        model.addAttribute(new Person());
            return "persons/create_person";
    }

    @GetMapping("delete/{id}")
    public String deletePerson(@PathVariable Integer id) {
        personService.deletePersonById(id);
        return "redirect:/persons";
    }

    @GetMapping("deleteAllPersonWithNoAnimal")
    public String deleteAllPersonWithNoAnimal() {
        personService.deleteAllPersonWithNoAnimal();
        return "redirect:/persons";
    }

    @GetMapping("update/{id}")
    public String updatePerson(@PathVariable Integer id, Model model) {
        Person person = personService.findPersonById(id);
        if (person == null) return "persons/person_not_found";
        model.addAttribute(person);
        return "persons/update_person";
    }

    @GetMapping("createRandomPerson")
    public String createRandomPerson() {
        personService.createEntities(15);
        return "redirect:/persons";
    }

    @PostMapping("create")
    public String createPerson(@Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "persons/create_person";
        personService.save(person);
        return "redirect:/persons";
    }

    @PostMapping("update")
    public String updatePerson(@Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "persons/update_person";
        personService.save(person);
        return "redirect:/persons";
    }
}
