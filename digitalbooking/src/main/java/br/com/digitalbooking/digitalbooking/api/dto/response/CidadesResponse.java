package br.com.digitalbooking.digitalbooking.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class CidadesResponse {
    private UUID id;
    public String nome;
    public String pais;
}
