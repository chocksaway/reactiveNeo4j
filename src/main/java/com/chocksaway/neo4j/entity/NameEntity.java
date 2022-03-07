package com.chocksaway.neo4j.entity;

import com.chocksaway.neo4j.relationship.Link;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

@Node("Name")
@Getter
public class NameEntity {

    @Id
    private String name;

    @Relationship(type = "LINK", direction = INCOMING)
    private List<Link> nameLinks;


    public NameEntity(final String name) {
        this.name = name;
    }

    public NameEntity() {}

    public void install(Link nameLink) {
        if (nameLinks == null) {
            nameLinks = new ArrayList<>();
        }
        nameLinks.add(nameLink);
    }
}