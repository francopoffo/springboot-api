package com.francopoffo.myfirstapi.infra.exception;

import com.francopoffo.myfirstapi.domain.meetings.validations.MyValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity Error404Handler(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity Error400Handler(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(ErrorValidationData::new).toList());
    }

    @ExceptionHandler(MyValidationException.class)
    public ResponseEntity BusinessRulesErrorHandler(MyValidationException ex){

        return ResponseEntity.badRequest().body(ex.getMessage());
    }


    private record ErrorValidationData(String field, String message){
        public ErrorValidationData(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
