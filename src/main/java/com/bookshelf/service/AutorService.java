package com.bookshelf.service;

import com.bookshelf.dto.autor.AutorRequestDTO;
import com.bookshelf.model.Autor;
import com.bookshelf.model.Livro;
import com.bookshelf.repository.AutorRepository;
import com.bookshelf.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;

    public AutorService(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    public Autor addAutor(AutorRequestDTO autorDto) {
        Autor novoAutor = new Autor(autorDto);

        return this.autorRepository.save(novoAutor);
    }

    public Autor pegaAutorPoId(Long id) {
        return this.autorRepository.findById(id).orElseThrow(null);
    }

    public List<Autor> pegarTodosAutores() {
        return this.autorRepository.findAll();
    }

    public void deletarAutor(Long id) {
        Autor autor = this.pegaAutorPoId(id);

        List<Livro> livrosPorAutor = this.livroRepository.findLivrosByAutor_Id(id);

        if (!livrosPorAutor.isEmpty()) {
            throw new RuntimeException("Ainda existem livros ligados ao autor");
        }

        this.autorRepository.delete(autor);
    }
}
