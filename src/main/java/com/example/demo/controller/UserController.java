package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "Operaciones para gestionar los usuarios")
public class UserController extends GenericController<User>{

    @Autowired
    private UserRepository repository;

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario existente")
    public ResponseEntity<User> update(@PathVariable Long id,@Valid @RequestBody User userDetails) {
        return repository.findById(id).map(user -> {
            user.setMail(userDetails.getMail());
            user.setNombre(userDetails.getNombre());
            return ResponseEntity.ok(repository.save(user));
        }).orElse(ResponseEntity.notFound().build());
    }
}