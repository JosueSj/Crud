package com.challenge.demo.api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.demo.api.exception.LibroNotFoundException;
import com.challenge.demo.api.model.Libro;
import com.challenge.demo.api.repository.LibroRepository;
import com.challenge.demo.service.LibroService;



@RestController
@RequestMapping("/api")
public class LibroController {
    
    @Autowired
    private LibroService libroService;

    //Devuelve lista de paginacion de libros
     @GetMapping("/listar")
    public Page<Libro> listarLibrosConPaginacion(Pageable pageable) {
        return libroService.getAll(pageable);
    }

    //crea un libro
    @PostMapping("/libros")
    public String saveLibro(@RequestBody Libro libro) {
        libroService.saveLibro(libro);
        return "OK - Saved libro";
    }

    //busca un libro x ID


    @GetMapping("/libros/{id}")
public ResponseEntity<Libro> getUsersById(@PathVariable(value = "id") Integer id) {
    Libro libro = libroRepository.findById(id)
            .orElseThrow(() -> new LibroNotFoundException("User not found on :: " + id));
    return ResponseEntity.ok().body(libro);
}
    /*@GetMapping("/libros/{id}")
    public ResponseEntity<Object> getDetalleLibro(@PathVariable Integer id) {
        try {
            Libro libro = libroService.getLibroDetalles(id);
            if (libro == null) {
                // Si el libro no se encuentra, devuelve un ResponseEntity con HttpStatus.NOT_FOUND
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado");
            } else {
                // Si el libro se encuentra, devuelve un ResponseEntity con HttpStatus.OK
                return ResponseEntity.ok(libro);
            }
        } catch (Exception e) {
            // Si ocurre algún error interno, devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al obtener el libro");
        }
    }*/


     @PutMapping("/libros/{id}")
    public String actualizarLibro(@PathVariable Integer id, @RequestBody Libro nuevoLibro) {
        libroService.actualizarLibro(id, nuevoLibro);
        return "OK - Libro actualizado";
    }

    @DeleteMapping("/libros/{id}")
    public ResponseEntity<String> eliminarLibro(@PathVariable Integer id) {
        try {
            libroService.eliminarLibro(id);
            return new ResponseEntity<>("Libro eliminado exitosamente", HttpStatus.OK);
        } catch (LibroNotFoundException e) {
            return new ResponseEntity<>("Libro no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
}
