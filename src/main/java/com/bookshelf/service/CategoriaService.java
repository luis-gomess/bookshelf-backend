package com.bookshelf.service;

import com.bookshelf.dto.categoria.CategoriaRequestDTO;
import com.bookshelf.exception.CategoriaComLivrosException;
import com.bookshelf.exception.CategoriaNaoEncontradaException;
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

    public Categoria atualizarCategoria(Long id, CategoriaRequestDTO categoriaDto) {
        Categoria categoria = this.pegarCategoriaPoId(id);

        categoria.setNome(categoriaDto.nome());
        categoria.setDescricao(categoriaDto.descricao());

        return this.categoriaRepository.save(categoria);
    }

    public Categoria pegarCategoriaPoId(Long id) {
        return this.categoriaRepository.findById(id).orElseThrow(CategoriaNaoEncontradaException::new);
    }

    public List<Categoria> pegarTodasCategorias() {
        return this.categoriaRepository.findAll();
    }

    public void deletarCategoria(Long id) {
        Categoria categoria = this.pegarCategoriaPoId(id);

        List<Livro> livrosPorCategoria = this.livroRepository.findLivrosByCategoria_Id(id);

        if (!livrosPorCategoria.isEmpty()) {
            throw new CategoriaComLivrosException();
        }

        this.categoriaRepository.delete(categoria);
    }
}
