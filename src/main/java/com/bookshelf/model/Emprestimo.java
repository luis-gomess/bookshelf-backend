package com.bookshelf.model;

import com.bookshelf.dto.emprestimo.EmprestimoRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomePessoa;

    @Column(nullable = false)
    private LocalDate dataEmprestimo;

    @Column(nullable = false)
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEmprestimo statusEmprestimo;

    @ManyToOne(optional = false)
    private Livro livro;

    public Emprestimo(EmprestimoRequestDTO emprestimoDto) {
        this.nomePessoa = emprestimoDto.nomePessoa();
        this.dataEmprestimo = emprestimoDto.dataEmprestimo();
        this.dataDevolucaoPrevista = emprestimoDto.dataDevolucaoPrevista();
        this.statusEmprestimo = StatusEmprestimo.EMPRESTADO;
    }

    public Emprestimo(EmprestimoRequestDTO emprestimoDto, Livro livro) {
        this(emprestimoDto);
        this.livro = livro;
    }
}
