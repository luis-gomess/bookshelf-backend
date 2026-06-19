package com.bookshelf.controller;

import com.bookshelf.dto.emprestimo.EmprestimoRequestDTO;
import com.bookshelf.dto.emprestimo.EmprestimoResponseDTO;
import com.bookshelf.model.Emprestimo;
import com.bookshelf.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/emprestimos")
public class EmprestimoController {
    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    public ResponseEntity<EmprestimoResponseDTO> addEmprestimo(@RequestBody @Valid EmprestimoRequestDTO emprestimoDto, UriComponentsBuilder uriComponentsBuilder) {
        Emprestimo novoEmprestimo = this.emprestimoService.addEmprestimo(emprestimoDto);

        URI uri = uriComponentsBuilder.path("/api/emprestimos/{id}").buildAndExpand(novoEmprestimo.getId()).toUri();

        return ResponseEntity.created(uri).body(EmprestimoResponseDTO.fromEntity(novoEmprestimo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoResponseDTO> pegarEmprestimoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(EmprestimoResponseDTO.fromEntity(this.emprestimoService.pegarEmprestimoPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<EmprestimoResponseDTO>> pegarTodosEmprestimos() {
        return ResponseEntity.ok(this.emprestimoService.pegarTodosEmprestimos().stream().map(EmprestimoResponseDTO::fromEntity).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoResponseDTO> atualizarEmprestimo(@PathVariable Long id, @RequestBody @Valid EmprestimoRequestDTO emprestimoDto) {
        return ResponseEntity.ok(EmprestimoResponseDTO.fromEntity(this.emprestimoService.atualizarEmprestimo(id, emprestimoDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable Long id) {
        this.emprestimoService.deletarEmprestimo(id);

        return ResponseEntity.noContent().build();
    }
}
