package com.github.dalwid.literalura.repository;

import com.github.dalwid.literalura.models.Author;
import com.github.dalwid.literalura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT a FROM Author a WHERE a.anoFalecimento >= :anoProcurado")
    List<Author> listaAutoresEmDeterminadoAno(Integer anoProcurado);

    @Query("SELECT l FROM Book l WHERE l.idioma ILIKE %:idioma%")
    List<Author> ListaLivrosEmDeterminadoIdioma(String idioma);

    @Query("SELECT l from Livro l ORDER BY l.totalDownloads DESC LIMIT 5")
    List<Author> buscarTop5LivrosMaisBaixados();

    @Query("SELECT l " +
            "FROM Book l " +
            "JOIN Author a ON l.autor.id = a.id " +
            "WHERE a.nome ILIKE %:nomeAutor%")
    List<Author> listaLivrosPorAutor(String nomeAutor);
}
