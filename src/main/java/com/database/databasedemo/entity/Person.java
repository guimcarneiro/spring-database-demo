package com.database.databasedemo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

//Hibernate implementa JPA. JPA é uma interface de mapeamento de Objetos Relacionais
//em um database.

//Entity Person mapeia para uma table Person no database
//@Table(name="person") - alternativa para escolher o nome da table, mas normalmente
//mapeia para o nome da classe
@Entity
@NamedQuery(name="find_all_persons", query="select p from Person p")
public class Person {
	
	//define como pk esse atributo e o gera automaticamente
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	private String location;
	private Date birthDate;
	
	//Precisa ter um construtor sem parâmetros
	public Person() {
		
	}
	
	public Person(int id, String name, String location, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}
	
	//Construtor sem id como parâmetro, visto que esse será gerado pelo JPA
	public Person(String name, String location, Date birthDate) {
		super();
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "\nPerson [id=" + id + ", name=" + name + ", location=" + location + ", birthDate=" + birthDate + "]";
	}
	
}
