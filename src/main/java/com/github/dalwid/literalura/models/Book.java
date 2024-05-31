package com.github.dalwid.literalura.models;


import com.github.dalwid.literalura.dto.BookData;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private Double totalDownloads;

    @ManyToOne
    private Author author;

    public Book (BookData bookData) {
        this.titulo = bookData.titulo();
        this.idioma = bookData.idioma().get(0);
        try {
            this.totalDownloads = Double.parseDouble(bookData.totalDownloads());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public String toString() {
        return
                "-------------Livro----------------" + "\n" +
                        " titulo: " + titulo + '\n' +
                        " Autor: " + author.getNome() + '\n' +
                        " idioma: " + idioma + '\n' +
                        " total de Downloads: " + totalDownloads;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getTotalDownloads() {
        return totalDownloads;
    }

    public void setTotalDownloads(Double totalDownloads) {
        this.totalDownloads = totalDownloads;
    }

    public Author getAutor() {
        return author;
    }

    public void setAutor(Author autor) {
        this.author = autor;
    }
}
