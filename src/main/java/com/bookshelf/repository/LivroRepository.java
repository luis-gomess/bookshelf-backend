package com.bookshelf.repository;

import com.bookshelf.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findLivrosByAutor_Id(Long autorId);

    List<Livro> findLivrosByCategoria_Id(Long categoriaId);
}
