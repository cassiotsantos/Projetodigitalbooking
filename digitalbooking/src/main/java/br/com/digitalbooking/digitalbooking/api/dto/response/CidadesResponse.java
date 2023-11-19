package br.com.digitalbooking.digitalbooking.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class CidadesResponse {
    private UUID id;
    private String nome;
    private String pais;
}
