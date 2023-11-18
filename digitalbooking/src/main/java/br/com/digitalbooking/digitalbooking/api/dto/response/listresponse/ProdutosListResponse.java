package br.com.digitalbooking.digitalbooking.api.dto.response.listresponse;

import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter

public class ProdutosListResponse {


    private UUID id;
    private String nome;
    private String descricao;
    private Categorias categorias;
    private String latitude;
    private String longitude;
    private LocalDateTime createdAt;
}
