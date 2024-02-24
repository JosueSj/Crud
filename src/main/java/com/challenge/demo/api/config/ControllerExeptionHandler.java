package com.challenge.demo.api.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.challenge.demo.api.exception.ApiError;
import com.challenge.demo.api.exception.LibroNotFoundException;

@ControllerAdvice
public class ControllerExeptionHandler {
    @ExceptionHandler(value = {LibroNotFoundException.class})
    protected ResponseEntity<ApiError> handleNotFoundException(LibroNotFoundException e, HttpServletRequest request) {
        String path = request.getRequestURI();
        String[] pathParts = path.split("/");
        Integer id = Integer.parseInt(pathParts[pathParts.length - 1]);

        String mensaje = "id: " + id + " - " + e.getMessage();

        ApiError apiError = new ApiError("Not found", mensaje, HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

}

