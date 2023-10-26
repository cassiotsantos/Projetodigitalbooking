package br.com.digitalbooking.digitalbooking.api.dto.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CategoriasRequest {
    @NotBlank
    private UUID id;
    @NotBlank
    public String nome;
    @NotEmpty
    public String urlImage;
    @NotBlank
    public String descricao;
    @NotBlank
    public String qualificacao;
    @NotBlank
    private LocalDateTime createdAt;
}
