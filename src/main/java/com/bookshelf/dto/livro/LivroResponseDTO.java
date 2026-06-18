package com.bookshelf.dto.livro;

import com.bookshelf.model.Livro;
import com.bookshelf.model.StatusLeitura;

public record LivroResponseDTO(
        Long id,
        String titulo,
        String isbn,
        Integer anoPublicacao,
        StatusLeitura statusLeitura,
        Integer nota,
        String observacao,
        LivroAutorResponseDTO autor,
        LivroCategoriaResponseDTO categoria
) {
    public static LivroResponseDTO fromEntity(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getAnoPublicacao(),
                livro.getStatusLeitura(),
                livro.getNota(),
                livro.getObservacao(),
                LivroAutorResponseDTO.fromEntity(livro.getAutor()),
                LivroCategoriaResponseDTO.fromEntity(livro.getCategoria())
        );
    }
}
