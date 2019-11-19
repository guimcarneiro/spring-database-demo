package com.database.databasedemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.jpa.PersonJpaRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PersonJpaRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		logger.info("User id 10001 -> {}", repository.findById(10001));
		
		//Hibernate ignora o id que passo no insert, então melhor deixar sem
		logger.info("Inserting Tamara Maranello -> {}", 
				repository.insert(new Person("Tamara Maranello", "Belgrado", new Date())));
	
		logger.info("Update 10004 -> {}", 
				repository.update(new Person(10004, "Mário", "Patos", new Date())));
		
		repository.deleteById(10005);
		
		logger.info("All users -> {}", repository.findAll());
		/*
		
		logger.info("Users from Campina Grande -> {}", repository.findByLocation("Campina Grande"));
		
		logger.info("Deleting 10005 -> Nº of rows deleted - {}", repository.deleteById(10005));
		*/	
	}

}
