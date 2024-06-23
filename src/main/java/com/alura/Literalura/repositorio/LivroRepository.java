package com.alura.Literalura.repositorio;

import com.alura.Literalura.modelos.Autor;
import com.alura.Literalura.modelos.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository <Livro, Long> {

    @Query ("SELECT a FROM Livro l JOIN l.autor a  WHERE a.nomeDoAutor = :autorParaBusca ")
    Autor buscarPorAutor(String autorParaBusca);

    @Query ("SELECT a FROM Livro l JOIN l.autor a")
    List<Autor> listarTodosOsAutores();

    @Query ("SELECT l FROM Livro l")
    List<Livro> listarLivrosRegistrados();

    @Query ("SELECT l FROM Livro l WHERE l.idioma = :idiomaEscolhido")
    List<Livro> listarPorIdioma(String idiomaEscolhido);

    @Query ("SELECT a FROM Autor a WHERE a.anoDeFalecimento >= :ano")
    List<Autor> buscarAutoresVivosNoAno(String ano);
}
