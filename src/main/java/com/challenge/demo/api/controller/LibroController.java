package com.challenge.demo.api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
import com.challenge.demo.service.LibroService;



@RestController
@RequestMapping("/api")
public class LibroController {
    
    @Autowired
    private LibroService libroService;

    //Devuelve lista de paginacion de libros
     @GetMapping("/libros") /// ejemplo de paginacion /libros?page=0&size=2
    public Page<Libro> listarLibrosConPaginacion(Pageable pageable) {
        return libroService.getAll(pageable);
    }

    //crea un libro
    @PostMapping("/libros")
   public ResponseEntity<String> saveLibro(@RequestBody Libro libro) {
    try {
        Integer id = libroService.saveLibro(libro); // Suponiendo que el método saveLibro devuelve el ID del libro guardado
        String mensaje = String.format("id: %d - Libro guardado exitosamente", id);
        return ResponseEntity.ok(mensaje);
    } catch (LibroNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    } catch (DataAccessException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al interactuar con la base de datos");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
    }
}

    //busca un libro x ID
    @GetMapping("/libros/{id}")
    public ResponseEntity<Libro> getLibroDetalles(@PathVariable(value = "id") Integer id){
        return new ResponseEntity<>(libroService.getLibroDetalles(id),HttpStatus.OK);
    }


    // Actualizar libro
    /*@PutMapping("/libros/{id}")
    public ResponseEntity<Object> actualizarLibro(@PathVariable Integer id, @RequestBody Libro nuevoLibro) {
        try {
            Libro libroActualizado = libroService.actualizarLibro(id, nuevoLibro);
            return ResponseEntity.ok().body(libroActualizado);
        } catch (LibroNotFoundException e) {
            // responseObj = new JsonObject();
            responseObj.addProperty("id", id);
            responseObj.addProperty("mensaje", "Libro no encontrado");
            // Devolver un ResponseEntity con el objeto JSON y el estado NOT_FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al actualizar el libro");
        }
    }*/
    

    @DeleteMapping("/libros/{id}")
    public ResponseEntity<String> eliminarLibro(Integer id) {
        try {
            Integer libroId = libroService.eliminarLibro(id);
            return ResponseEntity.ok("Libro eliminado exitosamente con ID: " + libroId);
        } catch (LibroNotFoundException e) {
            System.out.println("Error: Libro no encontrado");
            e.printStackTrace(); // Imprimir la traza de la excepción para obtener más detalles
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado");
        } catch (Exception e) {
            System.out.println("Error: Excepción desconocida al eliminar el libro");
            e.printStackTrace(); // Imprimir la traza de la excepción para obtener más detalles
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al eliminar el libro");
        }
    }
   }
