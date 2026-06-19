package com.bookshelf.exception;

public class CategoriaComLivrosException extends RegraDeNegocioException {
    public CategoriaComLivrosException() {
        super("Ainda existem livros ligados a categoria");
    }
}
