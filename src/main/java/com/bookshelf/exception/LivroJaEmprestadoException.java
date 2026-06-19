package com.bookshelf.exception;

public class LivroJaEmprestadoException extends RegraDeNegocioException {
    public LivroJaEmprestadoException() {
        super("Livro já está emprestado para outra pessoa.");
    }
}
