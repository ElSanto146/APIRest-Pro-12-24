package com.apirest.config;

import com.apirest.exception.AppExceptions;
import com.apirest.model.dto.ErrorDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppExceptions.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> response(AppExceptions e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(new ErrorDto(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(
                                FieldError::getField,
                                FieldError::getDefaultMessage
                        )));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> responseEntity(ConstraintViolationException ex) {
        String errorMensaje = "Error de validación: "+ ex.getConstraintViolations()
                .stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath()+ " "
                        + constraintViolation.getMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity
                .badRequest()
                .body(new ErrorDto(errorMensaje));
    }
}
