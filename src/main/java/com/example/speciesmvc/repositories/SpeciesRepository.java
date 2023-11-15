package com.example.speciesmvc.repositories;

import com.example.speciesmvc.entities.Species;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends CrudRepository<Species, Integer> {
    Species findFirstByCommonName(String commonName);
    List<Species> findByLatinNameContainingIgnoreCase(String latinName);
    @Query("select s from Species s order by s.commonName asc")
    List<Species> speciesOrderedByCommonName();

    @Query("select s from Species s where s.commonName like %:commonName%")
    List<Species> speciesContainsCommonName(@Param("commonName") String commonName);
}
