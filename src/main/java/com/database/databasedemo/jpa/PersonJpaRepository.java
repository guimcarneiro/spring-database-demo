package com.database.databasedemo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.database.databasedemo.entity.Person;

//Repository
//Transaction
@Repository
@Transactional
public class PersonJpaRepository {
	
	//connect to the database
	//EntityManager gere as Entities. Todas as operações que se faz em uma sessão
	//são guardadas no EntityManager. EntityManager é uma interface do PersistenceContext.
	//Todas as operações acontecem através de um EntityManager.
	//Basta definir as entidades e os Mappings que JPA toma conta do resto.
	
	@PersistenceContext
	EntityManager entityManager;
	
	public List<Person> findAll(){
		TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_persons", Person.class);
		return namedQuery.getResultList();
	}
	
	public Person findById(int id){
		return entityManager.find(Person.class, id);//JPA
	}
	
	//O que o merge faz é: se já existir uma Entity com aquele id, o atualiza com
	//as novas infos, se não existir, o cria.
	public Person update(Person person) {
		return entityManager.merge(person);
	}
	
	//Logicamente, a implementação do update e do insert são as mesmas, dado o comment
	//do método anterior.
	public Person insert(Person person) {
		return entityManager.merge(person);
	}
	
	//pega a Person antes, para depois deletar
	public void deleteById(int id) {
		Person person = findById(id);
		entityManager.remove(person);
	}
	
}
