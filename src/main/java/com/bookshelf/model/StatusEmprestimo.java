package com.bookshelf.model;

import lombok.Getter;

@Getter
public enum StatusEmprestimo {
    EMPRESTADO("emprestado"),
    DEVOLVIDO("devolvido");

    private final String status;

    StatusEmprestimo(String status) {
        this.status = status;
    }
}
