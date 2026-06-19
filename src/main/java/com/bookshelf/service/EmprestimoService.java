package com.bookshelf.service;

import com.bookshelf.dto.emprestimo.EmprestimoRequestDTO;
import com.bookshelf.exception.EmprestimoNaoEncontradoException;
import com.bookshelf.exception.LivroJaEmprestadoException;
import com.bookshelf.model.Emprestimo;
import com.bookshelf.model.Livro;
import com.bookshelf.model.StatusEmprestimo;
import com.bookshelf.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;
    private final LivroService livroService;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, LivroService livroService) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroService = livroService;
    }

    public Emprestimo addEmprestimo(EmprestimoRequestDTO emprestimoDto) {
        Optional<Emprestimo> emprestimoNoBanco = this.pegarEmprestimoPorLivroEStatus(emprestimoDto.livroId(), StatusEmprestimo.EMPRESTADO);

        if (emprestimoNoBanco.isPresent()) {
            throw new LivroJaEmprestadoException();
        }

        Livro livro = this.livroService.pegarLivroPorId(emprestimoDto.livroId());
        Emprestimo novoEmprestimo = new Emprestimo(emprestimoDto, livro);

        return this.emprestimoRepository.save(novoEmprestimo);
    }

    public Emprestimo atualizarEmprestimo(Long id, EmprestimoRequestDTO emprestimoDto) {
        Emprestimo emprestimo = this.pegarEmprestimoPorId(id);

        this.pegarEmprestimoPorLivroEStatus(emprestimoDto.livroId(), StatusEmprestimo.EMPRESTADO)
                .filter(emprestimoNoBanco -> !emprestimoNoBanco.getId().equals(id))
                .ifPresent(emprestimoNoBanco -> {
                    throw new LivroJaEmprestadoException();
                });

        Livro livro = this.livroService.pegarLivroPorId(emprestimoDto.livroId());

        emprestimo.setLivro(livro);
        emprestimo.setNomePessoa(emprestimoDto.nomePessoa());
        emprestimo.setDataEmprestimo(emprestimoDto.dataEmprestimo());
        emprestimo.setDataDevolucaoPrevista(emprestimoDto.dataDevolucaoPrevista());

        if (emprestimo.getStatusEmprestimo() == null) {
            emprestimo.setStatusEmprestimo(StatusEmprestimo.EMPRESTADO);
        }

        return this.emprestimoRepository.save(emprestimo);
    }

    public Emprestimo pegarEmprestimoPorId(Long id) {
        return this.emprestimoRepository.findById(id).orElseThrow(EmprestimoNaoEncontradoException::new);
    }

    public List<Emprestimo> pegarTodosEmprestimos() {
        return this.emprestimoRepository.findAll();
    }

    public Optional<Emprestimo> pegarEmprestimoPorLivroEStatus(Long id, StatusEmprestimo statusEmprestimo) {
        return this.emprestimoRepository.findEmprestimoByLivro_IdAndStatusEmprestimo(id, statusEmprestimo);
    }

    public void deletarEmprestimo(Long id) {
        Emprestimo emprestimo = this.pegarEmprestimoPorId(id);

        this.emprestimoRepository.delete(emprestimo);
    }
}
