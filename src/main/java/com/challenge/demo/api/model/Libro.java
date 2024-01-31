package com.challenge.demo.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 255, message = "El título debe tener como máximo 255 caracteres")
    private String titulo;

    @Size(max = 255, message = "El título debe tener como máximo 255 caracteres")
    private String autor;
    private int anio;
    private String genero;

}
