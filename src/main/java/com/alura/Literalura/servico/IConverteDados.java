package com.alura.Literalura.servico;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
