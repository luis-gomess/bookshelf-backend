package com.bookshelf.service;

import com.bookshelf.dto.autor.AutorRequestDTO;
import com.bookshelf.exception.AutorComLivrosException;
import com.bookshelf.exception.AutorNaoEncontradoException;
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

    public Autor atualizarAutor(Long id, AutorRequestDTO autorDto) {
        Autor autor = this.pegaAutorPoId(id);

        autor.setNome(autorDto.nome());
        autor.setNacionalidade(autorDto.nacionalidade());

        return this.autorRepository.save(autor);
    }

    public Autor pegaAutorPoId(Long id) {
        return this.autorRepository.findById(id).orElseThrow(AutorNaoEncontradoException::new);
    }

    public List<Autor> pegarTodosAutores() {
        return this.autorRepository.findAll();
    }

    public void deletarAutor(Long id) {
        Autor autor = this.pegaAutorPoId(id);

        List<Livro> livrosPorAutor = this.livroRepository.findLivrosByAutor_Id(id);

        if (!livrosPorAutor.isEmpty()) {
            throw new AutorComLivrosException();
        }

        this.autorRepository.delete(autor);
    }
}
