package com.bookshelf.exception;

public class LivroNaoEncontradoException extends RecursoNaoEncontradoException {
    public LivroNaoEncontradoException() {
        super("Livro não encontrado");
    }
}
