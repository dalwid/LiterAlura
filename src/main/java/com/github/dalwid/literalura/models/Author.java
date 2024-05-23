package com.github.dalwid.literalura.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  name;
    private Integer birthYear, deathYear;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> booksAuthor = new ArrayList<>();

    public Author() {}

    public Author(AuthorData data) {
        this.name      = data.name();
        this.birthYear = data.birthYear();
        this.deathYear = data.deathYear();
    }

    public String getName() {
        return name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public List<Book> getBooksAuthor() {
        return booksAuthor;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooksAuthor(List<Book> booksAuthor) {
        booksAuthor.forEach(books -> books.setAuthor(this));
        this.booksAuthor = booksAuthor;
    }

    @Override
    public String toString() {
        return "Author{" +
                "booksAuthor=" + booksAuthor +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                '}';
    }
}
