package br.com.digitalbooking.digitalbooking.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter

public class ProdutosResponse {
    private UUID id;
    public String nome;
    public String descricao;
    public String latitude;
    public String longitude;
    private LocalDateTime createdAt;
}
