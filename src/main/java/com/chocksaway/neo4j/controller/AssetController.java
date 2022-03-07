package com.chocksaway.neo4j.controller;

import com.chocksaway.neo4j.entity.AssetEntity;
import com.chocksaway.neo4j.repository.AssetRepository;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/asset")
public class AssetController {
    private final AssetRepository assetRepository;
    public AssetController(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @PostMapping
    Mono<AssetEntity> createOrUpdateBus(@RequestBody AssetEntity assetEntity) {
        return assetRepository.save(assetEntity);
    }

    @GetMapping("/{name}")
    Mono<AssetEntity> getNamedAsset(@PathVariable(value="name") final String name) {
        return assetRepository.findOneByName(name);
    }

    @GetMapping(value = { "", "/" }, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Transactional
    Flux<AssetEntity> getAssets() {
        return assetRepository.findAll();
    }
}