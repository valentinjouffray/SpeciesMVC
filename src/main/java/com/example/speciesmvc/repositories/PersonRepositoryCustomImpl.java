package com.example.speciesmvc.repositories;

import com.example.speciesmvc.entities.Person;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void deleteAllPersonWithNoAnimal() {
        List<Person> personList = entityManager.createQuery("select p from Person p left join p.animals a where a is null", Person.class).getResultList();
        System.out.println("Persons to delete : ");
        personList.forEach(System.out::println);
        personList.forEach(person -> entityManager.remove(person));
    }

    @Override
    @Transactional
    public void createEntities(int entitiesNb) {
        for (int i = 0; i < entitiesNb; i++) {
            createSingleEntity();
        }
    }

    @Transactional
    public void createSingleEntity() {
        String firstName = UUID.randomUUID().toString().substring(0, 10);
        String lastName = UUID.randomUUID().toString().substring(0, 10);
        Person newPerson = new Person();
        newPerson.setFirstName(firstName);
        newPerson.setLastName(lastName);
        entityManager.persist(newPerson);
    }
}
