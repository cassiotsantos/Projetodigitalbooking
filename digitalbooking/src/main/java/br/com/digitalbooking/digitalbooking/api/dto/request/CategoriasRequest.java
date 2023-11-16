package br.com.digitalbooking.digitalbooking.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoriasRequest {

  @NotBlank
  private String nome;
  @NotEmpty
  private String urlImage;
  @NotBlank
  private String descricao;
  @NotBlank
  private String qualificacao;
  private LocalDateTime createdAt;
}