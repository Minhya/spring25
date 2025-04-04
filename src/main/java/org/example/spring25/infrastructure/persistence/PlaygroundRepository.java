package org.example.spring25.infrastructure.persistence;

import org.example.spring25.domain.entity.Playground;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PlaygroundRepository extends ListCrudRepository<Playground, Integer> {

    List<Playground> findByName(String name);



    @Query("SELECT p FROM Playground p WHERE p.name =:name")
    List<Playground> findAllByName(String name);



}
