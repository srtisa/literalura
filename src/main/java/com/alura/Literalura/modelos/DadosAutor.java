package com.alura.Literalura.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosAutor(
        @JsonAlias("name") String nomeDoAutor,
        @JsonAlias("birth_year") Integer anoDeNascimento,
        @JsonAlias ("death_year") Integer anoDeFalecimento
        ) {
}
