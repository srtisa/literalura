package com.alura.Literalura.modelos;

import jakarta.persistence.*;

@Entity
@Table (name = "livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeDoLivro;

    @ManyToOne (cascade = CascadeType.ALL)
    private Autor autor;

    //private String autor;

    private Integer numeroDeDownloads;

    private String idioma;

    public Livro (DadosLivro dadosLivro){
        Autor autor = new Autor(dadosLivro.autor().get(0));
        this.autor = autor;
        this.nomeDoLivro = dadosLivro.nomeDoLivro();
        this.numeroDeDownloads = dadosLivro.numeroDeDownloads();
        this.idioma = dadosLivro.idioma().get(0);
    }

    public Livro(String nomeDoLivro, Autor autor, Integer numeroDeDownloads, String idioma) {
        this.nomeDoLivro = nomeDoLivro;
        this.autor = autor;
        this.numeroDeDownloads = numeroDeDownloads;
        this.idioma = idioma;
    }

    public Livro (){}

    public String getNomeDoLivro() {
        return nomeDoLivro;
    }

    public void setNomeDoLivro(String nomeDoLivro) {
        this.nomeDoLivro = nomeDoLivro;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Integer getNumeroDeDownloads() {
        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(Integer numeroDeDownloads) {
        this.numeroDeDownloads = numeroDeDownloads;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
                "LIVRO -------------------------------" + "\n" +
                       "Nome do Livro: " + getNomeDoLivro() + "\n" +
                       "Autor do Livro: " + autor.getNomeDoAutor() + "\n" +
                       "Número de Downloads: " + getNumeroDeDownloads() + "\n" +
                       "Idioma Original: " + getIdioma() + "\n" +
                       "-------------------------------";





    }
}


