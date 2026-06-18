package com.bookshelf.dto.emprestimo;

import com.bookshelf.dto.livro.LivroResponseDTO;
import com.bookshelf.model.Emprestimo;
import com.bookshelf.model.StatusEmprestimo;

import java.time.LocalDate;

public record EmprestimoResponseDTO(
        Long id,
        LivroResponseDTO livro,
        String nomePessoa,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucaoPrevista,
        LocalDate dataDevolucaoReal,
        StatusEmprestimo statusEmprestimo
) {
    public static EmprestimoResponseDTO fromEntity(Emprestimo emprestimo) {
        return new EmprestimoResponseDTO(
                emprestimo.getId(),
                LivroResponseDTO.fromEntity(emprestimo.getLivro()),
                emprestimo.getNomePessoa(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucaoPrevista(),
                emprestimo.getDataDevolucaoReal(),
                emprestimo.getStatusEmprestimo()
        );
    }
}
