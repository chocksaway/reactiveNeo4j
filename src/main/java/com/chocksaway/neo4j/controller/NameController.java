package com.chocksaway.neo4j.controller;

import com.chocksaway.neo4j.entity.NameEntity;
import com.chocksaway.neo4j.repository.NameRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/name")
public class NameController {
    private final NameRepository nameRepository;
    public NameController(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }


    @PostMapping
    Mono<NameEntity> createOrUpdateName(@RequestBody NameEntity nameEntity) {
        return nameRepository.save(nameEntity);
    }
}