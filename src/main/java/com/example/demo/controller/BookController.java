package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Biblioteca", description = "Operaciones para gestionar los libros")
public class BookController {

    @Autowired
    private BookRepository repository;

    @GetMapping
    @Operation(summary = "Listar todos los libros")
    public List<Book> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un libro por su ID")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Añadir un nuevo libro")
    public Book create(@Valid @RequestBody Book book) {
        return repository.save(book);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un libro existente")
    public ResponseEntity<Book> update(@PathVariable Long id,@Valid @RequestBody Book bookDetails) {
        return repository.findById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setIsbn(bookDetails.getIsbn());
            book.setRead(bookDetails.isRead());
            return ResponseEntity.ok(repository.save(book));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un libro")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}