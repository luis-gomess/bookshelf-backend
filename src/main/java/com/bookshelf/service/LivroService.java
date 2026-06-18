package com.bookshelf.service;

import com.bookshelf.dto.livro.LivroRequestDTO;
import com.bookshelf.model.Autor;
import com.bookshelf.model.Categoria;
import com.bookshelf.model.Emprestimo;
import com.bookshelf.model.Livro;
import com.bookshelf.model.StatusEmprestimo;
import com.bookshelf.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private final LivroRepository livroRepository;
    private final AutorService autorService;
    private final CategoriaService categoriaService;
    private final EmprestimoService emprestimoService;

    public LivroService(
            LivroRepository livroRepository,
            AutorService autorService,
            CategoriaService categoriaService,
            EmprestimoService emprestimoService
    ) {
        this.livroRepository = livroRepository;
        this.autorService = autorService;
        this.categoriaService = categoriaService;
        this.emprestimoService = emprestimoService;
    }

    public Livro addLivro(LivroRequestDTO livroDto) {
        Autor autor = this.autorService.pegaAutorPoId(livroDto.autorId());
        Categoria categoria = this.categoriaService.pegarCategoriaPoId(livroDto.categoriaId());

        Livro novoLivro = new Livro(livroDto, autor, categoria);

        return this.livroRepository.save(novoLivro);
    }

    public Livro pegarLivroPorId(Long id) {
        return this.livroRepository.findById(id).orElseThrow(null);
    }

    public List<Livro> pegarTodosLivros() {
        return this.livroRepository.findAll();
    }

    public void deletarLivro(Long id) {
        Livro livro = this.pegarLivroPorId(id);

        this.livroRepository.delete(livro);
    }
}
