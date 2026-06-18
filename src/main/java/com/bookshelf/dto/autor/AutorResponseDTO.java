package com.bookshelf.dto.autor;

import com.bookshelf.model.Autor;

public record AutorResponseDTO(
        Long id,
        String nome,
        String nacionalidade
) {
    public static AutorResponseDTO fromEntity(Autor autor) {
        return new AutorResponseDTO(
                autor.getId(),
                autor.getNome(),
                autor.getNacionalidade()
        );
    }
}
