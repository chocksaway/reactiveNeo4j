package com.chocksaway.neo4j;

import org.neo4j.driver.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.core.ReactiveDatabaseSelectionProvider;
import org.springframework.data.neo4j.core.transaction.ReactiveNeo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class Neo4jApplication {

	private final static Logger log = LoggerFactory.getLogger(Neo4jApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Neo4jApplication.class, args);
	}

	@Bean
	ReactiveNeo4jTransactionManager reactiveTransactionManager(Driver driver, ReactiveDatabaseSelectionProvider databaseSelectionProvider) {
		return new ReactiveNeo4jTransactionManager(driver, databaseSelectionProvider);
	}

//	@Bean
//	CommandLineRunner demo(MovieRepository movieRepository) {
//		log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//		return args -> {
//
//			movieRepository.deleteAll();
//
//
//
//			movieRepository
//					.save(new MovieEntity("Movie title", "", null, null))
//					.onErrorStop();
//
//			log.info("end xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//
//			//Mono<BusEntity> findit = busRepository.findById("123");
//
//			//findit.subscribe( result -> log.info("findAll: {}", result ));
//		};
//	}
}


//	@Bean
//	CommandLineRunner demo(PersonRepository personRepository) {
//		return args -> {
//
//			personRepository.deleteAll();
//
//			Person greg = new Person("Greg");
//			Person roy = new Person("Roy");
//			Person craig = new Person("Craig");
//
//			List<Person> team = Arrays.asList(greg, roy, craig);
//
//			log.info("Before linking up with Neo4j...");
//
//			team.stream().forEach(person -> log.info("\t" + person.toString()));
//
//			personRepository.save(greg);
//			personRepository.save(roy);
//			personRepository.save(craig);
//
//
//			// greg
//			greg = personRepository.findByName(greg.getName());
//			greg.worksWith(roy);
//			greg.worksWith(craig);
//			personRepository.save(greg);
//
//			// roy
//			roy = personRepository.findByName(roy.getName());
//			roy.worksWith(craig);
//			personRepository.save(roy);
//
//			craig = personRepository.findByName(craig.getName());
//			craig.worksWith(roy);
//			// We already know craig works with roy and greg
//			personRepository.save(craig);
//
//			log.info("Lookup each person by name...");
//			team.stream().forEach(person -> log.info(
//					"\t" + personRepository.findByName(person.getName()).toString()));
//
//
//
//			List<Person> teammates = personRepository.findByTeammatesName(greg.getName());
//			log.info("The following have Greg as a teammate...");
//			teammates.stream().forEach(person -> log.info("\t" + person.getName()));
//		};
//	}

