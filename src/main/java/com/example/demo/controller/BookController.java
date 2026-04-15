package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Biblioteca", description = "Operaciones para gestionar los libros")
public class BookController extends GenericController<Book>{

    @Autowired
    private BookRepository repository;

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
}