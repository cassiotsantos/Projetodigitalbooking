package br.com.digitalbooking.digitalbooking.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoriasRequest {
  //  @NotBlank
  public String nome;
  //  @NotEmpty
  public String urlImage;
  // @NotBlank
  public String descricao;
  // @NotBlank
  public String qualificacao;
  // @NotBlank
  private LocalDateTime createdAt;
}