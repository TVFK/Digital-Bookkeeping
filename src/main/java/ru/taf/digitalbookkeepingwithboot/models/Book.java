package ru.taf.digitalbookkeepingwithboot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message =  "Name mustn't be empty")
    @Size(min = 2, max = 100, message = "name must be between 2 and 50 characters")
    @Column(name = "title")
    private String name;

    @NotEmpty(message = "Author name mustn't be empty")
    @Size(min = 2, max = 50, message = "Author name must be between 2 and 50 characters")
    @Column(name = "author_name")
    private String author;

    @Min(value = 0, message = "Year of creation must be greater than 0")
    @Column(name = "year_creation")
    private int creationYear;

    @ManyToOne()
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "book_take_timestamp")
    public LocalDateTime timeTakeBook;

    @Transient
    private boolean bookIsOverdue;

    public Book(){};

    public Book(String name, String author, int creationYear) {
        this.name = name;
        this.author = author;
        this.creationYear = creationYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int book_id) {
        this.id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public boolean isBookIsOverdue() {
        return (Duration.between(this.timeTakeBook, LocalDateTime.now()).getSeconds()/86400) > 10;
    }

    public void setBookIsOverdue(boolean bookIsOverdue) {
        this.bookIsOverdue = bookIsOverdue;
    }

    public LocalDateTime getTimeTakeBook() {
        return timeTakeBook;
    }

    public void setTimeTakeBook(LocalDateTime timeTakeBook) {
        this.timeTakeBook = timeTakeBook;
    }
}
