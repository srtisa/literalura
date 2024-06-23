package com.alura.Literalura.principal;

import com.alura.Literalura.modelos.Autor;
import com.alura.Literalura.modelos.DadosLivro;
import com.alura.Literalura.modelos.Livro;
import com.alura.Literalura.repositorio.LivroRepository;
import com.alura.Literalura.servico.ConsumoDeGutendex;
import com.alura.Literalura.servico.ConverteDados;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//Começo da classe ________________________________________________________________________
public class Principal {

    private final String ENDERECO = "http://gutendex.com/books/?search=";

    ConverteDados converteDados = new ConverteDados();

    ConsumoDeGutendex consumoDeApi = new ConsumoDeGutendex();

    Scanner scanner = new Scanner(System.in);

    private LivroRepository repositorio;

    public Principal(LivroRepository repositorio) {
        this.repositorio = repositorio;
    }
//Menu _____________________________________________________________________________________________
    public void exibeMenu(){
        var opcaoMenu = -1;

    var menu = """
            Qual a opção você quer escolher?
            1 - Busca por LIVRO
            2 - Listar por AUTOR 
            3 - Listar Livros Registrados
            4 - Buscar por Livros em Determinado Idioma
            5 - Buscar Autores Vivos em determinado ano
            
            0 - Sair 
            """;

        while (opcaoMenu != 0) {
        System.out.println(menu);
        opcaoMenu = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoMenu) {
            case 1:
                buscarPorLivro();
                break;
            case 2:
                buscarPorAutor();
                break;

            case 3:
                listarLivrosRegistrados();
                break;

            case 4:
                buscarPorIdioma();
                break;

            case 5:
                buscarAutoresVivosNoAno();
                break;

            case 0:
                System.out.println("Encerrando o programa...");
                break;
        }

    }


}
//Métodos ___________________________________________________________________________________

    private void buscarPorLivro() {
        System.out.println("Digite um livro para buscar: -> ");
        var tituloDoLivroParaBuscar = scanner.nextLine();

        var json = consumoDeApi.obterDados(ENDERECO + tituloDoLivroParaBuscar.replace(" ", "+"));

        System.out.println(json);

        String jsonObjeto = converteDados.extraiObjetoJson(json, "results");

        List <DadosLivro> dadosLivroEAutores = converteDados.obterLista(jsonObjeto, DadosLivro.class);

        if (dadosLivroEAutores.size() > 0) {
            Livro livro = new Livro(dadosLivroEAutores.get(0));

            Autor autor = repositorio.buscarPorAutor(livro.getAutor().getNomeDoAutor());
            if (autor != null){
                livro.setAutor(null);
                repositorio.save(livro);
                livro.setAutor(autor);
            }

                System.out.println(livro);
                repositorio.save(livro);

        }   else {
            System.out.println("Livro não encontrado.");
        }

    }

    private void buscarPorAutor() {
       List <Autor> autores =  repositorio.listarTodosOsAutores();
       autores.forEach(System.out::println);

    }

    private void listarLivrosRegistrados() {
        List <Livro> livros = repositorio.listarLivrosRegistrados();
        livros.forEach(System.out::println);
    }

    private void buscarPorIdioma(){
        System.out.println("Digite um idioma a procurar: en/pt");
        String idiomaEscolhido = scanner.nextLine();

        List <Livro> livrosPorIdioma = repositorio.listarPorIdioma(idiomaEscolhido);
        livrosPorIdioma.forEach(System.out::println);
    }

    private void buscarAutoresVivosNoAno () {
        System.out.println("Digite um ano para buscar autores vivos nesse ano: ");
        var ano = scanner.nextLine();


        List<Autor> autoresVivos = repositorio.buscarAutoresVivosNoAno(ano);
        if (autoresVivos.size() > 0) {
            autoresVivos.forEach(System.out::println);
        }   else {
            System.out.println("Não há autores vivos esse ano. :( ");
        }
    }
}


