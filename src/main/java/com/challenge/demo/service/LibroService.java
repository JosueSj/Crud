package com.challenge.demo.service;

import org.apache.tomcat.jni.LibraryNotFoundError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.challenge.demo.api.exception.LibroNotFoundException;
import com.challenge.demo.api.model.Libro;
import com.challenge.demo.api.repository.LibroRepository;

@Service
public class LibroService {

    @Autowired
    LibroRepository libroRepository;

    public Libro getLibro(Integer id) {
        return null;
    }

    public Page<Libro> getAll(Pageable pageable) {
        return libroRepository.findAll(pageable);
    }

    public void saveLibro(Libro libro) {
        // Implementa la lógica para guardar un libro en la base de datos
        libroRepository.save(libro);
    }

    public Libro getLibroDetalles(Integer id)  {
        return libroRepository.findById(id).orElse( null);
    }

    public void actualizarLibro(Integer id, Libro nuevoLibro) {
        try {
            // Verifica si el libro con el ID dado existe en la base de datos
            Libro libroExistente = libroRepository.findById(id).orElseThrow(() -> new LibroNotFoundException("Libro no encontrado"));

            // Actualiza la información del libro existente con la nueva información
            libroExistente.setTitulo(nuevoLibro.getTitulo());
            libroExistente.setAutor(nuevoLibro.getAutor());
            libroExistente.setAnio(nuevoLibro.getAnio());
            libroExistente.setGenero(nuevoLibro.getGenero());

            // Guarda el libro actualizado en la base de datos
            libroRepository.save(libroExistente);
        } catch (LibraryNotFoundError e) {
            // Manejar la excepciónes
            e.printStackTrace(); //imprimir o registrar el error
        }
    
    }

    public void eliminarLibro(Integer id) {
        try {
            libroRepository.findById(id).ifPresent(libro -> {
                libroRepository.delete(libro);
            });
        } catch (DataAccessException e) {
            // Manejar la excepción
            e.printStackTrace(); //imprimir o registrar el error
        }
    }

}
