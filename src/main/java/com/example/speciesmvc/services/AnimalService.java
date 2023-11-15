package com.example.speciesmvc.services;

import com.example.speciesmvc.entities.Animal;
import com.example.speciesmvc.entities.Species;
import com.example.speciesmvc.enums.Sex;
import com.example.speciesmvc.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    public Iterable<Animal> animals() {
        return animalRepository.findAll();
    }

    public Animal findAnimalById(Integer id) {
        return animalRepository.findById(id).orElse(null);
    }

    public Animal save(Animal animal) {
        return animalRepository.save(animal);
    }

    public List<Animal> findBySpecies(Species species) {
        return animalRepository.findBySpecies(species);
    }

    public List<Animal> findByColorIsIn(List<String> color) {
        return animalRepository.findByColorIsIn(color);
    }

    public int numberOfAnimalsOfSex(Sex sex) {
        return animalRepository.numberOfAnimalsOfSex(sex);
    }

    public boolean isAnimalOwned(String animalName) {
        return animalRepository.isAnimalOwned(animalName);
    }

    public void deleteAnimalById(Integer id) {
        animalRepository.deleteById(id);
    }
}
