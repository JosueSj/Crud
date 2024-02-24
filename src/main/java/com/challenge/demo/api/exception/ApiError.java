package com.challenge.demo.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ApiError {
    private String error;
    private String message;
    private Integer status;


     // Constructor sin el campo id
     public ApiError(String error, String message, Integer status) {
        super();
        this.error = error;
        this.message = message;
        this.status = status;
    }
}
