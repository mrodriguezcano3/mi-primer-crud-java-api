package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity // Le dice a Spring que esta clase es una tabla de base de datos
@Data   // Genera automáticamente Getters, Setters y otros métodos útiles (de Lombok)
public class User extends BaseEntity{
    @NotBlank
    @Size (min = 2, max = 20, message = "El nombre debe tener entre 2 y 20 caracteres")
    private String nombre;

    @Email(message = "Debe ser un correo electrónico válido")
    @NotBlank(message = "El mail es obligatorio")
    private String mail;
}