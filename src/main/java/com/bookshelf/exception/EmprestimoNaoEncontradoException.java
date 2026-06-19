package com.bookshelf.exception;

public class EmprestimoNaoEncontradoException extends RecursoNaoEncontradoException {
    public EmprestimoNaoEncontradoException() {
        super("Empréstimo não encontrado");
    }
}
