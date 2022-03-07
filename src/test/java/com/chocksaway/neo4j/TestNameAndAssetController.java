package com.chocksaway.neo4j;

import com.chocksaway.neo4j.entity.AssetEntity;
import com.chocksaway.neo4j.entity.NameEntity;
import com.chocksaway.neo4j.relationship.Link;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestNameAndAssetController {
    @LocalServerPort
    int randomServerPort;

    @SneakyThrows
    @Test
    public void testAddName() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:"+randomServerPort+"/name";

        final long currentTimeMills = System.currentTimeMillis();


        final Link nameEntity1 = new Link(new NameEntity("name001"), "one", currentTimeMills);
        final Link nameEntity2 = new Link(new NameEntity("name002"), "two", currentTimeMills);

        final NameEntity bob = new NameEntity("Bob");

        bob.install(nameEntity1);
        bob.install(nameEntity2);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final ResponseEntity<String> response =  restTemplate.postForEntity(baseUrl, bob, String.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());

        Assertions.assertTrue(response.hasBody());
        Assertions.assertTrue(response.getBody().contains("Bob"));
    }


    @SneakyThrows
    @Test
    public void testGetAsset() throws URISyntaxException {
        final String baseUrl = "http://localhost:"+randomServerPort+"/asset/Bob";
        final WebClient client = WebClient.builder().baseUrl(baseUrl).build();

        final Mono<AssetEntity> asset = client.get().retrieve().bodyToMono(AssetEntity.class);
        asset.subscribe(
                a -> assertEquals("Bob", a.getName()));
    }


    @SneakyThrows
    @Test
    public void testGetAllAssets() throws URISyntaxException {
        final String baseUrl = "http://localhost:"+randomServerPort+"/asset";

        final WebClient client = WebClient.builder().baseUrl(baseUrl).build();



        Flux<AssetEntity> assets = client.get().retrieve().bodyToFlux(AssetEntity.class);

        final StringBuffer buffer = new StringBuffer();

        assets.subscribe(
                asset -> {
                    System.out.println("asset: " +  asset);
                    buffer.append(asset.getName());
                });
                assets.blockLast();

        assertTrue(buffer.toString().contains("Bob"));

    }
}
