package br.com.digitalbooking.digitalbooking.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter

public class ProdutosRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;
    private String latitude;
    private String longitude;
    private LocalDateTime createdAt;

}
