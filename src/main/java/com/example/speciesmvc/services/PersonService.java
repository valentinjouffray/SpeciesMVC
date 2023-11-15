package com.example.speciesmvc.services;


import com.example.speciesmvc.entities.Person;
import com.example.speciesmvc.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    public Iterable<Person> persons() {
        return personRepository.findAll();
    }

    public Person findPersonById(Integer id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public List<Person> findByLastNameContainsOrFirstNameContaining(String lastName, String firstName) {
        return personRepository.findByLastNameContainsOrFirstNameContaining(lastName, firstName);
    }

    public List<Person> findByAgeGreaterThanEqual(int age) {
        return personRepository.findByAgeGreaterThanEqual(age);
    }
    public List<Person> personsAgedBetween(int minAge, int maxAge) {
        return personRepository.personsAgedBetween(minAge, maxAge);
    }

    public List<Person> findPersonByAnimalName(String animalName) {
        return personRepository.findPersonByAnimalName(animalName);
    }

    public void deleteAllPersonWithNoAnimal() {
        personRepository.deleteAllPersonWithNoAnimal();
    }

    public void createEntities(int entitiesNb) {
        personRepository.createEntities(entitiesNb);
    }

    public void deletePersonById(Integer id) {
        personRepository.deleteById(id);
    }
}
