package br.com.digitalbooking.digitalbooking.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DatasIncorrectException extends RuntimeException{
    public DatasIncorrectException(String message) {super(message);}
}
