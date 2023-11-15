package com.example.speciesmvc.repositories;

import com.example.speciesmvc.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer>, PersonRepositoryCustom {
    List<Person> findByLastNameContainsOrFirstNameContaining(String lastName, String firstName);
    List<Person> findByAgeGreaterThanEqual(int age);

    @Query("select p from Person p where p.age >= :minAge and p.age <= :maxAge")
    List<Person> personsAgedBetween(@Param("minAge") int minAge, @Param("maxAge") int maxAge);
    @Query("select p from Person p join p.animals a where a.name = :animalName")
    List<Person> findPersonByAnimalName(@Param("animalName") String animalName);
}
