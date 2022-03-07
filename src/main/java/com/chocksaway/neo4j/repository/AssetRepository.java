package com.chocksaway.neo4j.repository;

import com.chocksaway.neo4j.entity.AssetEntity;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface AssetRepository extends ReactiveNeo4jRepository<AssetEntity, String> {
    Mono<AssetEntity> findOneByName(String title);
}

