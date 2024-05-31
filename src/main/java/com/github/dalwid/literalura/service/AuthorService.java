package com.github.dalwid.literalura.service;

import com.github.dalwid.literalura.models.Author;
import com.github.dalwid.literalura.models.Book;
import com.github.dalwid.literalura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    private Scanner leitura = new Scanner(System.in);

    public void salvar (Author author) {
        authorRepository.save(author);
    }


    public void listaLivrosPorAutor () {
        System.out.println("Por favor digite o nome do autor procurado: ");
        var nomeAutor = leitura.nextLine();
        var listaDeLivros = authorRepository.listaLivrosPorAutor(nomeAutor);
        listaDeLivros.forEach(l ->
                System.out.println(
                        /*"------------ LIVRO ---------------" + "\n" +
                                "Titulo: " + l.getTitulo() +"\n" +
                                "Autor: " + l.getAutor().getNome() +"\n" +
                                "Idioma: " + l.getIdioma() +"\n" +
                                "Numero de Downloads: " + l.getTotalDownloads() +"\n")*/l));
    }

    public void listarAutoresCadastrados () {
        authorRepository.findAll().forEach(a ->
                System.out.println(
                        "Nome: " + a.getNome() + "\n" +
                                "Ano de Nascimento: " + a.getAnoNascimento() + "\n" +
                                "Ano de Falecimento: : " + a.getAnoFalecimento() + "\n" +
                                "Livros: " + a.listaTituloLivros() + "\n"));
    }

    public void listarLivrosCadastrados () {
        authorRepository.findAll().stream()
                .forEach(a -> a.getLivros().stream()
                        .forEach(l ->
                                System.out.println(
                                        "------------ LIVRO ---------------" + "\n" +
                                                "Titulo: " + l.getTitulo() +"\n" +
                                                "Autor: " + l.getAutor().getNome() +"\n" +
                                                "Idioma: " + l.getIdioma() +"\n" +
                                                "Numero de Downloads: " + l.getTotalDownloads() +"\n")));
    }

    public void salvarLivroDeAutorJaCadastrado(Author autor, Book livro) {
        var autorProcurado = authorRepository.findByNomeContainingIgnoreCase(autor.getNome());
        if (autorProcurado.isPresent()) {
            var autorEncontrado = autorProcurado.get();
            autorEncontrado.getLivros().stream()
                    .forEach(l ->
                            System.out.println(l.getAutor().listaTituloLivros()));
            autorEncontrado.setLivros(livro);
            salvar(autorEncontrado);
        } else {
            salvar(autor);
        }
    }

    public void listarAutoresVivosEmDeterminadoAno() {
        System.out.println("Por favor digite o ano desejado: ");
        var anoProcurado = leitura.nextInt();
        leitura.nextLine();
        var listaDeAutoresProcurados = authorRepository.listaAutoresEmDeterminadoAno(anoProcurado);
        if (listaDeAutoresProcurados.isEmpty()) {
            System.out.println("Nenhum autor encontrado para o ano de " + anoProcurado);
        } else {
            listaDeAutoresProcurados.forEach(autor ->
                    System.out.println(autor.getNome() + " \n " + autor.getAnoNascimento() + " \n " +
                            autor.getAnoFalecimento() + " \n " +  autor.listaTituloLivros() + " \n "));
        }
    }

    public void ListaLivrosEmDeterminadoIdioma() {
        System.out.println("Por favor digite o idioma procurado: ");
        var idiomaProcurado = leitura.nextLine();
        var listaDeLivros = authorRepository.ListaLivrosEmDeterminadoIdioma(idiomaProcurado.toLowerCase().trim());

        if (listaDeLivros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma " + idiomaProcurado);
        } else {
            listaDeLivros.forEach(l->
                    System.out.println(l.getTitulo() + " \n " +"Autor: " + l.getAutor().getNome() + " \n " +
                            "Total de Downloads: " + l.getTotalDownloads() + " \n "));
        }
    }

    public void buscarTop5LivroMaisBaixados() {
        var top5List = authorRepository.buscarTop5LivrosMaisBaixados();
        top5List.forEach(l ->
                System.out.println(
                      "Titulo: " + l.getTitulo() + "\n" +
                                "Autor: " + l.getAutor().getNome() + "\n" +
                                "Total de Downloads: " + l.getTotalDownloads() + "\n"
                ));
    }
}
