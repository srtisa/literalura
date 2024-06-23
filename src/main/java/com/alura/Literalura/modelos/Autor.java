package com.alura.Literalura.modelos;

//public void Autor (){}

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table (name = "autor")
public class Autor {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true)
    private String nomeDoAutor;

    private Integer anoDeNascimento;

    private Integer anoDeFalecimento;

    private Integer idadeDoAutor;

    @OneToMany (mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Livro> livros = new ArrayList<>();

    public Autor() {
    }

    public Autor (DadosAutor dadosAutor) {
        this.nomeDoAutor = dadosAutor.nomeDoAutor();
        this.anoDeNascimento = dadosAutor.anoDeNascimento();
        this.anoDeFalecimento = dadosAutor.anoDeFalecimento();
}

    public Autor(String nomeDoAutor, Integer anoDeNascimento, Integer anoDeFalecimento, Integer idadeDoAutor) {
        this.nomeDoAutor = nomeDoAutor;
        this.anoDeNascimento = anoDeNascimento;
        this.anoDeFalecimento = anoDeFalecimento;
        this.idadeDoAutor = calcularIdadeAutor();
    }

    public Integer getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setAnoDeNascimento(Integer anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }

    public String getNomeDoAutor() {
        return nomeDoAutor;
    }

    public void setNomeDoAutor(String nomeDoAutor) {
        this.nomeDoAutor = nomeDoAutor;
    }

    public Integer getAnoDeFalecimento() {
        return anoDeFalecimento;
    }

    public void setAnoDeFalecimento(Integer anoDeFalecimento) {
        this.anoDeFalecimento = anoDeFalecimento;
    }

    public Integer getIdadeDoAutor() {
        return  idadeDoAutor;
    }

    public void setIdadeDoAutor(Integer idadeDoAutor) {
        calcularIdadeAutor();
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros ) {this.livros = livros;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Integer calcularIdadeAutor () {
            var idade =  anoDeFalecimento - this.anoDeNascimento;
            return idade;
    }


    @Override
    public String toString() {
        return
                "AUTOR -------------------------------" +
                        "Autor: " + getNomeDoAutor() + "\n" +
                        "Data de Nascimento: " + getAnoDeNascimento() + "\n" +
                        "Data de Falecimento: " + getAnoDeFalecimento() + "\n" +
                        "Viveu: " + getIdadeDoAutor() + " anos\n" +
                        "Livros: " + livros.stream().map(l -> l.getNomeDoLivro()).collect(Collectors.toList()) + "\n" +
                        "-------------------------------";


    }
}


