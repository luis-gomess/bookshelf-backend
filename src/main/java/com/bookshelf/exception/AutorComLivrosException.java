package com.bookshelf.exception;

public class AutorComLivrosException extends RegraDeNegocioException {
    public AutorComLivrosException() {
        super("Ainda existem livros ligados ao autor");
    }
}
