/*
package kkv.spring.dao;

import kkv.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("select * from people", new PersonMapper());
    }

    public Person show(int id){
        return jdbcTemplate.query("select * from people where id =?",new Object[]{id},new PersonMapper()).stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update(
                "insert into people(email,name_,year) values(?,?,?)",
                person.getMail(),person.getName(),person.getYearOfBirth()
                );
    }

    public void update(int id, Person newPerson){
        jdbcTemplate.update(
                "update people set email=?, name_=?, year=? where id =?",
                newPerson.getMail(),newPerson.getName(),newPerson.getYearOfBirth(),id
        );
    }

    public void delete(int id){
        jdbcTemplate.update(
          "delete from people where id=?",id
        );
    }
}
*/
