package com.bookshelf.dto.emprestimo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record EmprestimoRequestDTO(
        @NotNull
        @Positive
        Long livroId,
        @NotBlank
        String nomePessoa,
        @NotNull
        LocalDate dataEmprestimo,
        @NotNull
        LocalDate dataDevolucaoPrevista
) {
}
