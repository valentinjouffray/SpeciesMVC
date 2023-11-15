package com.example.speciesmvc.services;


import com.example.speciesmvc.entities.Species;
import com.example.speciesmvc.repositories.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpeciesService {
    @Autowired
    private SpeciesRepository speciesRepository;
    public Iterable<Species> species() {
        return speciesRepository.findAll();
    }
    public Species findSpeciesById(int species_id) {
        Optional<Species> speciesFound = speciesRepository.findById(species_id);
        return speciesFound.orElse(null);
    }
    public Species save(Species species) {
        return speciesRepository.save(species);
    }

    public Species findFirstByCommonName(String commonName) {
        return speciesRepository.findFirstByCommonName(commonName);
    }

    public List<Species> findByLatinNameContainingIgnoreCase(String latinName) {
        return speciesRepository.findByLatinNameContainingIgnoreCase(latinName);
    }

    public List<Species> speciesOrderedByCommonName() {
        return speciesRepository.speciesOrderedByCommonName();
    }

    public List<Species> speciesContainsCommonName(String commonName) {
        return speciesRepository.speciesContainsCommonName(commonName);
    }

    public void deleteSpeciesById(Integer id) {
        speciesRepository.deleteById(id);
    }
}
