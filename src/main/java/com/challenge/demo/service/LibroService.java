package com.challenge.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public Integer saveLibro(Libro libro) {
        validarLibro(libro);
        Libro libroGuardado = libroRepository.save(libro);
        return libroGuardado.getId();
    }
//metodo auxiliar para validar campo obligatorio.
    private void validarLibro(Libro libro) {
        if (libro.getTitulo() == null || libro.getTitulo().isEmpty()) {
            throw new LibroNotFoundException("El título del libro es obligatorio");
        }
    }

    public Libro getLibroDetalles(Integer id)  {
        return libroRepository.findById(id).orElseThrow(
            ()-> new LibroNotFoundException("Libro no encontrado"));
    }

    public Libro actualizarLibro(Integer id, Libro nuevoLibro) {
        Libro libroExistente = libroRepository.findById(id)
                .orElseThrow(() -> new LibroNotFoundException("Libro no encontrado"));

        libroExistente.setTitulo(nuevoLibro.getTitulo());
        libroExistente.setAutor(nuevoLibro.getAutor());
        libroExistente.setAnio(nuevoLibro.getAnio());
        libroExistente.setGenero(nuevoLibro.getGenero());

        return libroRepository.save(libroExistente);
    }


    public Integer eliminarLibro(Integer id) {
        Libro libroEliminado = libroRepository.findById(id).orElseThrow(() -> 
        new LibroNotFoundException("Libro no encontrado"));
        Integer libroId = libroEliminado.getId(); // Guardar el ID antes de eliminar el libro
        System.out.println("libro" + libroEliminado);
        libroRepository.delete(libroEliminado);
        return libroId; // Devolver el ID guardado
    }

}
