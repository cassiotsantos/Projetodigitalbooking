package br.com.digitalbooking.digitalbooking.api.dto.response.listresponse;

import br.com.digitalbooking.digitalbooking.domain.entity.EnumQualificacao;
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
    private EnumQualificacao qualificacao;
    private LocalDateTime createdAt;
}
