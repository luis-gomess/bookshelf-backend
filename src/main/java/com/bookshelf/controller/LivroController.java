package com.bookshelf.controller;

import com.bookshelf.dto.livro.LivroRequestDTO;
import com.bookshelf.dto.livro.LivroResponseDTO;
import com.bookshelf.model.Livro;
import com.bookshelf.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {
    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> addLivro(@RequestBody LivroRequestDTO livroDto, UriComponentsBuilder uriComponentsBuilder) {
        Livro novoLivro = this.livroService.addLivro(livroDto);

        URI uri = uriComponentsBuilder.path("/api/livros/{id}").buildAndExpand(novoLivro.getId()).toUri();

        return ResponseEntity.created(uri).body(LivroResponseDTO.fromEntity(novoLivro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> pegaLivroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(LivroResponseDTO.fromEntity(this.livroService.pegaLivroPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> pegarTodosLivros() {
        return ResponseEntity.ok(this.livroService.pegarTodosLivros().stream().map(LivroResponseDTO::fromEntity).toList());
    }
}
