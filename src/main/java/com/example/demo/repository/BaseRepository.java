package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean // Esta anotación evita que Spring intente crear una instancia de esto
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
}