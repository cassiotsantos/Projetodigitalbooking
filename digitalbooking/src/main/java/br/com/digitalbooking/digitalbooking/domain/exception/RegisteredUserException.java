package br.com.digitalbooking.digitalbooking.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RegisteredUserException extends RuntimeException{
    public RegisteredUserException(String email) {
        super("E-mail já existe");
    }

}
