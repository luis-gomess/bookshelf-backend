package com.bookshelf.controller;

import com.bookshelf.dto.categoria.CategoriaRequestDTO;
import com.bookshelf.dto.categoria.CategoriaResponseDTO;
import com.bookshelf.model.Categoria;
import com.bookshelf.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> addCategoria(@RequestBody CategoriaRequestDTO categoriaDto, UriComponentsBuilder uriComponentsBuilder) {
        Categoria novaCategoria = this.categoriaService.addCategoria(categoriaDto);

        URI uri = uriComponentsBuilder.path("/api/categorias/{id}").buildAndExpand(novaCategoria.getId()).toUri();

        return ResponseEntity.created(uri).body(CategoriaResponseDTO.fromEntity(novaCategoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> pegarCategoriaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(CategoriaResponseDTO.fromEntity(this.categoriaService.pegarCategoriaPoId(id)));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> pegarTodasCategorias() {
        return ResponseEntity.ok(this.categoriaService.pegarTodasCategorias().stream().map(CategoriaResponseDTO::fromEntity).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        this.categoriaService.deletarCategoria(id);

        return ResponseEntity.noContent().build();
    }
}
