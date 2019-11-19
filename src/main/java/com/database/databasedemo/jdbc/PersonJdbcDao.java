package com.database.databasedemo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.database.databasedemo.entity.Person;

//Usando JDBC, a parte mais complexa é escrever as queries e passar os parâmetros,
//de resto é puro SQL. O problema disso é que não encapsula o código do database.

@Repository // Se comunica com o database, logo usa a annotation Repository
public class PersonJdbcDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//Cria uma innerclass pois essa classe só será utilizada pela PersonJdbcDao
	class PersonRowMapper implements RowMapper<Person>{
		
		//Mapeia uma query(row) para um objeto em específico
		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			//No resultSet é onde vem os valores da row
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getTimestamp("birth_date"));
			return person;
		}
		
	}
	
	//select * from person
	public List<Person> findAll(){
		return jdbcTemplate.query("select * from person",
				new PersonRowMapper());
	}
	
	//Retorna Um objeto resultado de uma query (queryForObject)
	public Person findById(int id){
		return jdbcTemplate.queryForObject
				("select * from person where id=?",
				new Object[]{id},
				new PersonRowMapper());
	}
	
	public List<Person> findByLocation(String location){
		return jdbcTemplate.query
				("select * from person where location=?",
				new Object[] {location},
				new PersonRowMapper());
	}
	
	//Para fazer um update ou um delete usando queries no jdbc, usa o método "update"
	//do jdbcTemplate.
	//Não precisa do Mapper pois no delete não mapeia para nenhuma classe.
	//o update retorna quantas rows foram alteradas. Nesse caso retorna quantas
	//rows foram deletadas.
	public int deleteById(int id){
		return jdbcTemplate.update
				("delete from person where id=?",
				new Object[]{id});
	}
	
	//Query SQL não aceita Date, deve ser um TimeStamp(importado do java.sql)
	public int insert(Person person){
		return jdbcTemplate.update
				("insert into person(id, name, location, birth_date) "
						+ "values(?,?,?,?)",
				new Object[]{person.getId(),
						person.getName(),
						person.getLocation(),
						new Timestamp(person.getBirthDate().getTime())});
	}
	
	//O array deve seguir a ordem das interrogações na query.
	public int update(Person person){
		return jdbcTemplate.update
				("update person "
						+ "set name=?, location=?, birth_date=? "
						+ "where id=?",
				new Object[] {
						person.getName(),
						person.getLocation(),
						new Timestamp(person.getBirthDate().getTime()),
						person.getId()});
	}
}
