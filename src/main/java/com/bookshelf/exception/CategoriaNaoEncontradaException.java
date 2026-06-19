package com.bookshelf.exception;

public class CategoriaNaoEncontradaException extends RecursoNaoEncontradoException {
    public CategoriaNaoEncontradaException() {
        super("Categoria não encontrada");
    }
}
