package com.example.speciesmvc.repositories;

import com.example.speciesmvc.entities.Animal;
import com.example.speciesmvc.entities.Species;
import com.example.speciesmvc.enums.Sex;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Integer> {
    List<Animal> findBySpecies(Species species);

    List<Animal> findByColorIsIn(List<String> color);

    @Query("select count(a) from Animal a where a.sex = :sex")
    int numberOfAnimalsOfSex(@Param("sex") Sex sex);

    @Query("select case when count(p) > 0 then true else false end from Person p join p.animals a where a.name = :animalName")
    boolean isAnimalOwned(@Param("animalName") String animalName);
}
