package br.com.digitalbooking.digitalbooking.api.dto.request;

import br.com.digitalbooking.digitalbooking.domain.entity.EnumQualificacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriasRequest {

  @NotBlank
  @Size(min = 2)
  private String nome;
  @NotEmpty
  private String urlImage;
  @NotBlank
  @Size(min = 10, max = 500)
  private String descricao;
  private EnumQualificacao qualificacao;
  private LocalDateTime createdAt;
}