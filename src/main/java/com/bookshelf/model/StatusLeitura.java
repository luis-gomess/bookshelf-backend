package com.bookshelf.model;

import lombok.Getter;

@Getter
public enum StatusLeitura {
    NAO_LIDO("nao_lido"),
    LENDO("lendo"),
    LIDO("lido");

    private final String status;

    StatusLeitura(String status) {
        this.status = status;
    }
}
