package com.github.dalwid.literalura.main;

import com.github.dalwid.literalura.dto.LivroData;
import com.github.dalwid.literalura.library.ConvertData;
import com.github.dalwid.literalura.library.UsingAPI;
import com.github.dalwid.literalura.models.Autor;
import com.github.dalwid.literalura.models.Livro;
import com.github.dalwid.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Year;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RunMain {

    private Scanner     leitura   = new Scanner(System.in);

    private UsingAPI    usingAPI  = new UsingAPI();
    private ConvertData convert   = new ConvertData();

    private final String ADDRESS  = "https://gutendex.com/books?search=";

    @Autowired
    private LivroRepository service;

    public RunMain (LivroRepository service) {
        this.service = service;
    };

    public void exibeMenu(){
        int opcao = -1;
        while (opcao != 0){
            String menu = """
                \n-------------------------------------------
                       *** Escolha uma das opções ***
                -------------------------------------------
                1 - Buscar livro por título
                2 - Listar livros registrados
                3 - Listar Autores
                4 - Listar Autores vivos em determinado ano
                5 - Listar Livros em determinado Idioma
                6 - Exibir a quantidade de livros em um determinado idioma.
                0 - Sair
                -------------------------------------------
                """;
            try {
                System.out.println(menu);
                opcao = leitura.nextInt();
                leitura.nextLine();

                switch (opcao){
                    case 1:
                        buscarLivro();
                        break;
                    case 2:
                        listarLivros();
                        break;
                    case 3:
                        listarAutores();
                        break;
                    case 4:
                        listarAutoresVivosNoAno();
                        break;
                    case 5:
                        listarLivrosPorIdioma();
                        break;
                    case 6:
                        quantidadeLivrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            }catch (InputMismatchException e){
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                leitura.nextLine();
            }
        }
    }

    private void buscarLivro() {
        System.out.println("Digite o nome do Livro: ");
        var nomeLivro = leitura.nextLine();
        System.out.println("Buscando...");
        String enderecoBusca = ADDRESS.concat(nomeLivro.replace(" ", "+").toLowerCase().trim());

        String json = usingAPI.getData(enderecoBusca);
        String jsonLivro = convert.extractObjectJson(json, "results");

        List<LivroData> livrosDTO = convert.getList(jsonLivro, LivroData.class);

        if (livrosDTO.size() > 0) {
            Livro livro = new Livro(livrosDTO.get(0));

            //Verifica se o Autor já está cadastrado
            Autor autor = service.buscarAutorPeloNome(livro.getAutor().getAutor());
            if (autor != null) {
                livro.setAutor(null);
                service.save(livro);
                livro.setAutor(autor);
            }
            livro = service.save(livro);
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado");
        }
    }

    private void listarLivros() {
        List<Livro> livros = service.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutores() {
        List<Autor> autores = service.buscarAutores();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosNoAno() {
        try {
            System.out.println("Digite o ano:");
            int ano = leitura.nextInt();
            leitura.nextLine();

            List<Autor> autores = service.buscarAutoresVivosNoAno(Year.of(ano));
            autores.forEach(System.out::println);
        }catch (InputMismatchException e){
            System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            leitura.nextLine();
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("""
                Digite o idioma para busca
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """);
        String idioma = leitura.nextLine();
        List<Livro> livros = service.findByIdioma(idioma);
        if (!livros.isEmpty()){
            livros.forEach(System.out::println);
        }else{
            System.out.println("Não exite livros nesse idioma cadastrado");
        }
    }

    private void quantidadeLivrosPorIdioma() {
        System.out.println("""
                Digite o idioma para busca
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """);
        String idioma = leitura.nextLine();
        Integer quantidadeIdioma = service.countByIdioma(idioma);
        System.out.printf("O idioma %s tem %d livros cadastrado\n", idioma, quantidadeIdioma);
    }
}
