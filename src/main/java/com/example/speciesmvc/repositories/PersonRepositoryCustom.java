package com.example.speciesmvc.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositoryCustom {
    public void deleteAllPersonWithNoAnimal();
    public void createEntities(int entitiesNb);
}
