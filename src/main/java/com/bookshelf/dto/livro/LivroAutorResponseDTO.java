package com.bookshelf.dto.livro;

import com.bookshelf.model.Autor;

public record LivroAutorResponseDTO(
        Long id,
        String nome,
        String nacionalidade
) {
    public static LivroAutorResponseDTO fromEntity(Autor autor) {
        return new LivroAutorResponseDTO(
                autor.getId(),
                autor.getNome(),
                autor.getNacionalidade()
        );
    }
}
