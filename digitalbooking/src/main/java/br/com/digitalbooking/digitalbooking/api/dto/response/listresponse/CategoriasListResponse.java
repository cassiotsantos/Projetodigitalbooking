package br.com.digitalbooking.digitalbooking.api.dto.response.listresponse;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CategoriasListResponse {
    private UUID id;
    private String nome;
    private String urlImage;
    private String descricao;
    private String qualificacao;
    private LocalDateTime createdAt;
}
