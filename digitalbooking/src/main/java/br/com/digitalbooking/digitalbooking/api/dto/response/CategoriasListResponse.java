package br.com.digitalbooking.digitalbooking.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CategoriasListResponse {
    private UUID id;
    public String nome;
    public String urlImage;
    public String descricao;
    public String qualificacao;
    private LocalDateTime createdAt;
}
