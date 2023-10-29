package br.com.digitalbooking.digitalbooking.api.dto.response;

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
    public String nome;
    public String urlImage;
    public String descricao;
    public String qualificacao;
    private LocalDateTime createdAt;

}
