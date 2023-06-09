package com.rudykart.cigarrete.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.rudykart.cigarrete.dto.response.ErrorResponse;
import com.rudykart.cigarrete.dto.response.ValidateResponse;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ValidateResponse responseError = new ValidateResponse();
        responseError.setTimestamp(LocalDateTime.now());
        responseError.setStatus(HttpStatus.BAD_REQUEST.value());
        responseError.setMessage("Validation error");
        responseError.setErrors(new HashMap<>());

        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String fieldNameConvert = fieldName.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();

            String message = error.getDefaultMessage();
            List<String> fieldErrors = responseError.getErrors().get(fieldNameConvert);

            if (fieldErrors == null) {
                fieldErrors = new ArrayList<>();
                responseError.getErrors().put(fieldNameConvert, fieldErrors);
            }

            fieldErrors.add(message);
        });

        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Object> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException ex, WebRequest request) {
        // Anda dapat melakukan penanganan pengecualian di sini,
        // misalnya, mengirimkan pesan kesalahan yang sesuai atau
        // memberikan respon HTTP yang tepat.

        String errorMessage = ex.getMessage();
        ErrorResponse apiError = new ErrorResponse(LocalDateTime.now(), HttpStatus.CONFLICT.value(), errorMessage);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object> handleObjectNotFoundException(
            ObjectNotFoundException ex, WebRequest request) {

        String errorMessage = ex.getMessage();
        ErrorResponse apiError = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), errorMessage);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // @ExceptionHandler(NotFoundException.class)
    // public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    // }

    // @ExceptionHandler(AuthorizationException.class)
    // public ResponseEntity<String>
    // handleAuthorizationException(AuthorizationException ex) {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    // }
}
