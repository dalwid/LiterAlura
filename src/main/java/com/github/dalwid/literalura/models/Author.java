package com.github.dalwid.literalura.models;

import com.github.dalwid.literalura.dto.AuthorData;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nome;
    @Column(nullable = false)
    private Integer anoNascimento;
    private Integer anoFalecimento;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> livros = new ArrayList<>();


    public Author (AuthorData authorData) {
        this.nome = authorData.nome();
        this.anoNascimento = Integer.valueOf(authorData.dataNascimento());

        try {
            this.anoFalecimento = Integer.valueOf(authorData.dataFalecimento());
        } catch (NullPointerException e) {
            this.anoFalecimento = null;
        }
    }

    public List<String> listaTituloLivros () {
        return livros.stream()
                .map(Book::getTitulo)
                .collect(Collectors.toList());
    }

    public List<Book> getLivros() {
        return livros;
    }

    public void setLivros(Book livro) {
        livros.add(livro);
        livros.forEach(l -> l.setAutor(this));
        this.livros = livros;
    }

    public String getNome() {
        return nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public Long getId() {
        return id;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }
}
