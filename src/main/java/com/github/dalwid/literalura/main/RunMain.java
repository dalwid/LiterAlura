package com.github.dalwid.literalura.main;

import com.github.dalwid.literalura.dto.ResponseDTO;
import com.github.dalwid.literalura.library.ConvertData;
import com.github.dalwid.literalura.library.UsingAPI;
import com.github.dalwid.literalura.models.Author;
import com.github.dalwid.literalura.models.Book;
import com.github.dalwid.literalura.service.AuthorService;
import com.github.dalwid.literalura.supply.Supply;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class RunMain {

    private Scanner     leitura   = new Scanner(System.in);

    private UsingAPI    usingAPI  = new UsingAPI();
    private ConvertData convert   = new ConvertData();

    private final String ADDRESS  = "https://gutendex.com/books?search=";

    @Autowired
    private AuthorService service;

    public RunMain (AuthorService service) {
        this.service = service;
    };

    public void exibeMenu () {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em determinado ano
                5 - Listar livros em um determinado idioma
                6 - Listar top 5 livros mais baixados
                7 - Listar livros do autor buscado
                
                0 - Sair                                 
                """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    salvar();
                    break;
                case 2:
                    listaLivrosResgistrados();
                    break;
                case 3:
                    listaAutoresResgistrados();
                    break;

                case 4:
                    ListaAutoresVivosEmDeterminadoAno();
                    break;
                case 5:
                    ListaLivrosEmDeterminadoIdioma();
                    break;
                case 6:
                    listaTop5LivroMaisBaixados();
                    break;

                case 7:
                    listaLivrosPorAutor();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }

    }

    private void listaLivrosPorAutor() {
        service.listaLivrosPorAutor();
    }

    private void listaTop5LivroMaisBaixados() {
        service.buscarTop5LivroMaisBaixados();
    }

    private void ListaLivrosEmDeterminadoIdioma() {
        service.ListaLivrosEmDeterminadoIdioma();
    }

    private void ListaAutoresVivosEmDeterminadoAno() {
        service.listarAutoresVivosEmDeterminadoAno();
    }


    private void listaAutoresResgistrados() {
        service.listarAutoresCadastrados();
    }

    private void listaLivrosResgistrados() {
        service.listarLivrosCadastrados();
    }

    private Book getDadosLivroEAutor () {
        var responseDTO = getDados();
        var autor = new Author(responseDTO.results().get(0).authorDataList().get(0));
        var book = new Book(responseDTO.results().get(0));
        autor.setLivros(book);
        return book;
    }


    private ResponseDTO getDados() {
        System.out.println("Digite o titulo do livro que deseja buscar: ");
        var livroBuscado = leitura.nextLine();
        var json = usingAPI.getData(ADDRESS + livroBuscado.replace(" ", "%20"));
        ResponseDTO responseDTO = convert.toGetData(json, ResponseDTO.class);
        return responseDTO;
    }


    private void salvar () {
        var book = getDadosLivroEAutor();
        service.salvarLivroDeAutorJaCadastrado(book.getAutor(), book);
    }
}
