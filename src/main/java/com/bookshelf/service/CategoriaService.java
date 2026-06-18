package com.bookshelf.service;

import com.bookshelf.dto.categoria.CategoriaRequestDTO;
import com.bookshelf.model.Categoria;
import com.bookshelf.model.Livro;
import com.bookshelf.repository.CategoriaRepository;
import com.bookshelf.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final LivroRepository livroRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, LivroRepository livroRepository) {
        this.categoriaRepository = categoriaRepository;
        this.livroRepository = livroRepository;
    }

    public Categoria addCategoria(CategoriaRequestDTO categoriaDto) {
        Categoria novaCategoria = new Categoria(categoriaDto);

        return this.categoriaRepository.save(novaCategoria);
    }

    public Categoria pegarCategoriaPoId(Long id) {
        return this.categoriaRepository.findById(id).orElseThrow(null);
    }

    public List<Categoria> pegarTodasCategorias() {
        return this.categoriaRepository.findAll();
    }

    public void deletarCategoria(Long id) {
        Categoria categoria = this.pegarCategoriaPoId(id);

        List<Livro> livrosPorCategoria = this.livroRepository.findLivrosByCategoria_Id(id);

        if (!livrosPorCategoria.isEmpty()) {
            throw new RuntimeException("Ainda existem livros ligados a categoria");
        }

        this.categoriaRepository.delete(categoria);
    }
}
