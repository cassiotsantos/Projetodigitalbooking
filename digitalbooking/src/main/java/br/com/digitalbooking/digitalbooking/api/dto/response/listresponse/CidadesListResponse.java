package br.com.digitalbooking.digitalbooking.api.dto.response.listresponse;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CidadesListResponse {
    private UUID id;
    public String nome;
    public String pais;
}
