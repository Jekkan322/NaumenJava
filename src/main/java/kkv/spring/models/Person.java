package kkv.spring.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Min(value = 1900,message =  "you must be born after 1900")
    private int yearOfBirth;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String mail;

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

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Person(int id, String name, int yearOfBirth, String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.yearOfBirth = yearOfBirth;
    }

    public Person(){

    }
}
