package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class User {

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private Date birthday;

    public User(int id, String firstname, String lastname, String email, Date birthday) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + this.id +
                ", firstname='" + this.firstname + '\'' +
                ", lastname='" + this.lastname + '\'' +
                ", email='" + this.email + '\'' +
                ", birthday=" + this.birthday +
                '}';
    }

    public int getAge() {
        if ( this.birthday == null ) return -1;

        Date input = new Date();
        LocalDate today =
                input.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

        LocalDate birthDate =
                this.birthday.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

        return
                Period.between( birthDate, today )
                        .getYears();
    }
}
