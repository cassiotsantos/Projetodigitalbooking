package br.com.digitalbooking.digitalbooking.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CaracteristicasRequest {
  @NotBlank
  @Size(min =1, max = 60)
  private String nome;
  @NotBlank
  private String icone;
}