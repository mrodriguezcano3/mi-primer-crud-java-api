package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que este componente maneja el acceso a datos
public interface UserRepository extends BaseRepository<User, Long> {
    // Al extender de JpaRepository, ya tenemos: save(), findAll(), findById(), deleteById(), etc.
}