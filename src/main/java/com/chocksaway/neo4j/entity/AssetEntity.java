package com.chocksaway.neo4j.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

@Getter
@Node("Asset")
public class AssetEntity {
    @Id
    private String name;

    @Relationship(type = "LINKED", direction = INCOMING)
    private Set<Linked> assetEntities;


    public AssetEntity(String name) {
        this.name = name;
        assetEntities = new HashSet<>();
    }

    public AssetEntity() {
    }

    public Set<Linked> getAssetEntities() {
        return assetEntities;
    }


    public void install(Linked assetEntity) {
        assetEntities.add(assetEntity);
    }

    public void setAssetEntities(final Set<Linked> assetEntities) {
        this.assetEntities = assetEntities;
    }
}

