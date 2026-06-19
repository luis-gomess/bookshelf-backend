package com.bookshelf.dto.livro;

import com.bookshelf.model.StatusLeitura;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroRequestDTO(
    @NotBlank
    String titulo,
    @NotBlank
    String isbn,
    Integer anoPublicacao,
    @NotNull
    StatusLeitura statusLeitura,
    Integer nota,
    String observacao,
    @NotNull
    Long autorId,
    @NotNull
    Long categoriaId
) {
}
