package ru.taf.digitalbookkeepingwithboot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name mustn't be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Column(name = "name")
    private String name;

    @Min(value = 1920, message = "Year of birth must be greater than 1920")
    @Max(value = 2024, message = "Year of birth must be less than 2024")
    @Column(name = "birth_year")
    private int birthYear;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {}

    public Person(int personId, String name, int birthYear) {
        this.id = personId;
        this.name = name;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public List<Book> getBooks() {
        if (this.books == null) {
            books = new ArrayList<>();
        } else {
            return books;
        }
        return null;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
