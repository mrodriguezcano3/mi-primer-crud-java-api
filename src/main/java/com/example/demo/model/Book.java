package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity // Le dice a Spring que esta clase es una tabla de base de datos
@Data   // Genera automáticamente Getters, Setters y otros métodos útiles (de Lombok)
public class Book {

    @Id // Define que este campo es la "Llave Primaria" (el ID único)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    @Size
    private String author;

    @NotBlank
    private String isbn;
    private boolean read;
}