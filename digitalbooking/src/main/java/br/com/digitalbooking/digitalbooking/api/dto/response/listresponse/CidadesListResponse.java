package br.com.digitalbooking.digitalbooking.api.dto.response.listresponse;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CidadesListResponse {
    private UUID id;
    private String cidade;
    private String pais;
}
