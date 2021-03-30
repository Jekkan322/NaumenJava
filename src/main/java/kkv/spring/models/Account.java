package kkv.spring.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Account {

    private AccountKey accountKey;

    private String name;

    private String surname;

    private String patronymic;

    private String dateOfBirth;

    private boolean isAdmin;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public AccountKey getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(AccountKey accountKey) {
        this.accountKey = accountKey;
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
}