/*
package kkv.spring.dao;

import kkv.spring.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        var person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setYearOfBirth(resultSet.getInt("year"));
        person.setMail(resultSet.getString("email"));
        person.setName(resultSet.getString("name_"));

        return person;
    }
}
*/
