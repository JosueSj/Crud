    package com.challenge.demo.api.exception;

    public class LibroNotFoundException extends RuntimeException {
        
        public LibroNotFoundException(String mensaje) {
            super(mensaje);
        }
    }
