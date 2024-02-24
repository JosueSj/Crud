package com.challenge.demo.api.repository;
import com.challenge.demo.api.model.Libro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository < Libro, Integer>{
    Optional<Libro> findById(Integer id); //modificar Option (reducir options) *********
}