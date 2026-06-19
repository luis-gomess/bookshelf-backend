package com.bookshelf.exception;

public class AutorNaoEncontradoException extends RecursoNaoEncontradoException {
    public AutorNaoEncontradoException() {
        super("Autor não encontrado");
    }
}
