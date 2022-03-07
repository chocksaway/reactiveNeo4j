package com.chocksaway.neo4j.entity;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.List;



@RelationshipProperties
public class Linked {

    @RelationshipId
    private Long id;

    private final List<String> roles;

    @TargetNode
    private final AssetEntity assetEntity;

    public Linked(AssetEntity assetEntity, List<String> roles) {
        this.assetEntity = assetEntity;
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }
}

