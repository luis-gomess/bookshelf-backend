package com.bookshelf.dto.emprestimo;

import com.bookshelf.model.StatusEmprestimo;

import java.time.LocalDate;

public record EmprestimoRequestDTO(
        Long livroId,
        String nomePessoa,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucaoPrevista
) {
}
