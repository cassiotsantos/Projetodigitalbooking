package br.com.digitalbooking.digitalbooking.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class SearchException extends RuntimeException{
    public SearchException(String search) {super("Não específicou os créditos de busca");
        }
}
