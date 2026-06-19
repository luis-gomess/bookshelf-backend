package com.bookshelf.model;

import com.bookshelf.dto.autor.AutorRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    private String nacionalidade;

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros;

    public Autor() {

    }

    public Autor(AutorRequestDTO autorDto) {
        this.nome = autorDto.nome();
        this.nacionalidade = autorDto.nacionalidade();
    }
}
