package com.bookshelf.service;

import com.bookshelf.dto.livro.LivroRequestDTO;
import com.bookshelf.exception.LivroNaoEncontradoException;
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

    public Livro atualizarLivro(Long id, LivroRequestDTO livroDto) {
        Livro livro = this.pegarLivroPorId(id);
        Autor autor = this.autorService.pegaAutorPoId(livroDto.autorId());
        Categoria categoria = this.categoriaService.pegarCategoriaPoId(livroDto.categoriaId());

        livro.setTitulo(livroDto.titulo());
        livro.setIsbn(livroDto.isbn());
        livro.setAnoPublicacao(livroDto.anoPublicacao());
        livro.setNota(livroDto.nota());
        livro.setObservacao(livroDto.observacao());
        livro.setStatusLeitura(livroDto.statusLeitura());
        livro.setAutor(autor);
        livro.setCategoria(categoria);

        return this.livroRepository.save(livro);
    }

    public Livro pegarLivroPorId(Long id) {
        return this.livroRepository.findById(id).orElseThrow(LivroNaoEncontradoException::new);
    }

    public List<Livro> pegarTodosLivros() {
        return this.livroRepository.findAll();
    }

    public void deletarLivro(Long id) {
        Livro livro = this.pegarLivroPorId(id);

        this.livroRepository.delete(livro);
    }
}
