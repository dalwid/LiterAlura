package com.github.dalwid.literalura.models;



public class Book {

    private int id;

    private String title;

    private Author author;

    public Book(){}

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }
}
