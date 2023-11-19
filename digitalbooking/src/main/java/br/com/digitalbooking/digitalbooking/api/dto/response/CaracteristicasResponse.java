package br.com.digitalbooking.digitalbooking.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;



@Getter
@Setter
public class CaracteristicasResponse {
    private UUID id;
    private String nome;
    private String icone;

}
