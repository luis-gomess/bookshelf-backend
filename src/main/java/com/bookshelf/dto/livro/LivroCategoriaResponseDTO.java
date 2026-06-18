package com.bookshelf.dto.livro;

import com.bookshelf.model.Categoria;

public record LivroCategoriaResponseDTO(
        Long id,
        String nome,
        String descricao
) {
    public static LivroCategoriaResponseDTO fromEntity(Categoria categoria) {
        return new LivroCategoriaResponseDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao()
        );
    }
}
