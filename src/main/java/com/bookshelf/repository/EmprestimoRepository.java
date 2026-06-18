package com.bookshelf.repository;

import com.bookshelf.model.Emprestimo;
import com.bookshelf.model.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    Optional<Emprestimo> findEmprestimoByLivro_IdAndStatusEmprestimo(Long livroId, StatusEmprestimo statusEmprestimo);
}
