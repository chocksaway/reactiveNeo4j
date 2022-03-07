package com.chocksaway.neo4j.relationship;

import com.chocksaway.neo4j.entity.NameEntity;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Getter
public class Link {

    @RelationshipId
    private Long id;

    private String value;
    private long currentTimeMillis;


    @TargetNode
    private NameEntity nameEntity;

    public Link(NameEntity nameEntity, String value, long currentTimeMillis) {
        this.nameEntity = nameEntity;
        this.value = value;
        this.currentTimeMillis = currentTimeMillis;
    }

    public Link() {}

    public NameEntity getNameEntity() {
        return nameEntity;
    }

    public String getValue() {
        return value;
    }

    public long getCurrenTimeMillis() {
        return currentTimeMillis;
    }
}