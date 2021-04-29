package kkv.spring.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.management.relation.Role;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="account")
public class Account {

    @Id
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    @Column(name = "login")
    private String login;

    @Size(min = 4, max = 16, message = "Password should be between 4 and 16 characters")
    private String password;


    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @OneToMany(mappedBy="userAccount")
    private List<Request> requests;





    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 1, max = 30, message = "Surname should be between 1 and 30 characters")
    private String surname;

    @NotEmpty(message = "Patronymic should not be empty")
    @Size(min = 2, max = 30, message = "Patronymic should be between 2 and 30 characters")
    private String patronymic;

    private String dateOfBirth;



    public List<Roles> getRolesSet() {
        return rolesSet;
    }

    public void setRolesSet(List<Roles> rolesSet) {
        this.rolesSet = rolesSet;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /*public Date getDateOfBirth() {
        return dateOfBirth;
    }
    *//*return dateOfBirth.getDate()+"."+(dateOfBirth.getMonth()+1)+"."+(dateOfBirth.getYear()+1900);*//*

    public void setDateOfBirth(String dateOfBirth) {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date=null;
        try {
            date = simpleDateFormat.parse(dateOfBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.dateOfBirth.setDate(date.getDate());
        this.dateOfBirth.setMonth(date.getMonth());
        this.dateOfBirth.setYear(date.getYear());

    }*/
    public String getDateOfBirth() {
        return dateOfBirth;
    }



    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth=dateOfBirth;
    }

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name = "login"))
    @Enumerated(EnumType.STRING)
    private List<Roles> rolesSet;

   /* @ElementCollection(targetClass = Bid.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "bid",joinColumns = @JoinColumn(name = "login"))
    private List<Bid> bidSet;*/
}