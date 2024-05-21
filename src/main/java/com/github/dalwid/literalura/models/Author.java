package com.github.dalwid.literalura.models;

import java.util.Date;
import java.util.List;

public class Author {

    private int id;

    private String name;
    private Date birthYear, deathYear;

    private List<Book> booksAuthor;

    public Author() {}

    public Author(String name, Date birthYear, Date deathYear, List<Book> booksAuthor) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.booksAuthor = booksAuthor;
    }

    public String getName() {
        return name;
    }

    public Date getBirthYear() {
        return birthYear;
    }

    public Date getDeathYear() {
        return deathYear;
    }

    public List<Book> getBooksAuthor() {
        return booksAuthor;
    }
}
