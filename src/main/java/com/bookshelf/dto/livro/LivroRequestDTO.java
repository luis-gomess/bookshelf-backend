package com.bookshelf.dto.livro;

import com.bookshelf.model.StatusLeitura;

public record LivroRequestDTO(
    String titulo,
    String isbn,
    Integer anoPublicacao,
    StatusLeitura statusLeitura,
    Integer nota,
    String observacao,
    Long autorId,
    Long categoriaId
) {
}
