package com.chocksaway.neo4j.repository;

import com.chocksaway.neo4j.entity.NameEntity;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface NameRepository extends ReactiveNeo4jRepository<NameEntity, String> {
    Mono<NameEntity> findOneByName(String title);
}

