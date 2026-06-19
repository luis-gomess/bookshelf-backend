package com.bookshelf.dto.autor;

import jakarta.validation.constraints.NotBlank;

public record AutorRequestDTO(
        @NotBlank
        String nome,
        String nacionalidade
) {
}
