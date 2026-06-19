package com.bookshelf.controller;

import com.bookshelf.dto.autor.AutorRequestDTO;
import com.bookshelf.dto.autor.AutorResponseDTO;
import com.bookshelf.model.Autor;
import com.bookshelf.service.AutorService;
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
@RequestMapping("/api/autores")
public class AutorController {
    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<AutorResponseDTO> addAutor(@RequestBody @Valid AutorRequestDTO autorDto, UriComponentsBuilder uriComponentsBuilder) {
        Autor novoAutor = this.autorService.addAutor(autorDto);

        URI uri = uriComponentsBuilder.path("/api/autores/{id}").buildAndExpand(novoAutor.getId()).toUri();

        return ResponseEntity.created(uri).body(AutorResponseDTO.fromEntity(novoAutor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> pegarAutorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(AutorResponseDTO.fromEntity(this.autorService.pegaAutorPoId(id)));
    }

    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> pegarTodosAutores() {
        return ResponseEntity.ok(this.autorService.pegarTodosAutores().stream().map(AutorResponseDTO::fromEntity).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> atualizarAutor(@PathVariable Long id, @RequestBody @Valid AutorRequestDTO autorDto) {
        return ResponseEntity.ok(AutorResponseDTO.fromEntity(this.autorService.atualizarAutor(id, autorDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable Long id) {
        this.autorService.deletarAutor(id);

        return ResponseEntity.noContent().build();
    }
}
