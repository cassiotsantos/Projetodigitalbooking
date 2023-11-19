package br.com.digitalbooking.digitalbooking.api.dto.response;

import br.com.digitalbooking.digitalbooking.domain.entity.EnumQualificacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CategoriasResponse {
    private UUID id;
    private String nome;
    private String urlImage;
    private String descricao;
    private EnumQualificacao qualificacao;
    private LocalDateTime createdAt;

}
