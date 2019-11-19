package com.database.databasedemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.jdbc.PersonJdbcDao;

//@SpringBootApplication
public class SpringJdbcDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired	//Para se comunicar com o db, o PersonDao é necessário. O Spring o busca através do Autowired
	PersonJdbcDao dao;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}", dao.findAll());
		
		logger.info("User id 10001 -> {}", dao.findById(10001));
		
		logger.info("Users from Campina Grande -> {}", dao.findByLocation("Campina Grande"));
		
		logger.info("Deleting 10005 -> Nº of rows deleted - {}", dao.deleteById(10005));
		
		logger.info("Inserting 10006 -> {}", 
				dao.insert(new Person(10006, "Tamara Maranello", "Belgrado", new Date())));
		
		logger.info("Update 10004 -> {}", 
				dao.update(new Person(10004, "Mário", "Patos", new Date())));

	}

}
