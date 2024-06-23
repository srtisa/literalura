package com.alura.Literalura.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonAlias("title") String nomeDoLivro,
                         @JsonAlias("authors") List<DadosAutor> autor,
                         @JsonAlias ("download_count") Integer numeroDeDownloads,
                         @JsonAlias ("languages") List <String> idioma
                         ) {}

