package com.bookshelf.service;

import com.bookshelf.dto.livro.LivroRequestDTO;
import com.bookshelf.model.Autor;
import com.bookshelf.model.Categoria;
import com.bookshelf.model.Livro;
import com.bookshelf.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    private final LivroRepository livroRepository;
    private final AutorService autorService;
    private final CategoriaService categoriaService;

    public LivroService(
            LivroRepository livroRepository,
            AutorService autorService,
            CategoriaService categoriaService
    ) {
        this.livroRepository = livroRepository;
        this.autorService = autorService;
        this.categoriaService = categoriaService;
    }

    public Livro addLivro(LivroRequestDTO livroDto) {
        Autor autor = this.autorService.pegaAutorPoId(livroDto.autorId());
        Categoria categoria = this.categoriaService.pegarCategoriaPoId(livroDto.categoriaId());

        Livro novoLivro = new Livro(livroDto, autor, categoria);

        return this.livroRepository.save(novoLivro);
    }

    public Livro pegaLivroPorId(Long id) {
        return this.livroRepository.findById(id).orElseThrow(null);
    }

    public List<Livro> pegarTodosLivros() {
        return this.livroRepository.findAll();
    }

    public List<Livro> pegarLivrosPorAutor(Long id) {
        return this.livroRepository.findLivrosByAutor_Id(id);
    }

    public List<Livro> pegarLivrosPorCategoria(Long id) {
        return this.livroRepository.findLivrosByCategoria_Id(id);
    }
}
