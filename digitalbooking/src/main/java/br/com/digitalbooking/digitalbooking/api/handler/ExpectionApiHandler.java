package br.com.digitalbooking.digitalbooking.api.handler;

import br.com.digitalbooking.digitalbooking.domain.exception.NotFoundException;
import br.com.digitalbooking.digitalbooking.domain.exception.RegisteredUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExpectionApiHandler {
    @ExceptionHandler(NotFoundException.class)

    public ResponseEntity<Problem> notFoundExceptionHandler(NotFoundException e) {
    String message = "ID da categoria não encontrado no sistema.";
    Problem problem = new Problem(HttpStatus.NOT_FOUND.value(), message, e.getMessage());
    return ResponseEntity.ok().body(problem);
    }
    @ExceptionHandler(RegisteredUserException.class)
    public ResponseEntity<Problem> registeredUserException(RegisteredUserException e){
        String message = "E-mail cadastrado";
        Problem problem = new Problem(HttpStatus.BAD_REQUEST.value(), message, e.getMessage());
        return ResponseEntity.ok().body(problem);

    }
}
