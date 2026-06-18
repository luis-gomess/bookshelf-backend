package com.bookshelf.service;

import com.bookshelf.dto.emprestimo.EmprestimoRequestDTO;
import com.bookshelf.model.Emprestimo;
import com.bookshelf.model.StatusEmprestimo;
import com.bookshelf.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    public Emprestimo addEmprestimo(EmprestimoRequestDTO emprestimoDto) {
        Optional<Emprestimo> emprestimoNoBanco = this.emprestimoRepository.findEmprestimoByLivro_IdAndStatusEmprestimo(emprestimoDto.livroId(), StatusEmprestimo.EMPRESTADO);

        if (emprestimoNoBanco.isPresent()) {
            throw new RuntimeException("Livro já está emprestado para outra pessoa.");
        }

        Emprestimo novoEmprestimo = new Emprestimo(emprestimoDto);

        return this.emprestimoRepository.save(novoEmprestimo);
    }

    public Emprestimo pegarEmprestimoPorId(Long id) {
        return this.emprestimoRepository.findById(id).orElseThrow(null);
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
