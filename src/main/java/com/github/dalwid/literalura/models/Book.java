package com.github.dalwid.literalura.models;


import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    private List<String> languages;
    private Integer      downloadCount;

    @ManyToOne
    private Author author;

    public Book(){}

    public Book(BookData data) {
        this.title         = data.title();
        this.languages     = data.languages();
        this.downloadCount = data.downloadCount();
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", languages=" + languages +
                ", downloadCount=" + downloadCount +
                ", author=" + author.getName() +
                '}';
    }
}
