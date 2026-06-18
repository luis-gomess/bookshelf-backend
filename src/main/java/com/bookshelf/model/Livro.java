package com.bookshelf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String isbn;

    private Integer anoPublicacao;
    private Integer nota;
    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusLeitura statusLeitura;

    @ManyToOne(optional = false)
    private Autor autor;

    @ManyToOne(optional = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "livro")
    private List<Emprestimo> emprestimos;
}
