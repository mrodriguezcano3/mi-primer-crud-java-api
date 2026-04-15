package com.example.demo.controller;

import com.example.demo.repository.BaseRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GenericController<T> {

    @Autowired
    protected BaseRepository<T, Long> repository;

    @GetMapping
    @Operation(summary = "Listar todos")
    public List<T> getAll() {
        return repository.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear nuevo")
    public T create(@Valid @RequestBody T entity) {
        return repository.save(entity);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener por ID")
    public ResponseEntity<T> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}