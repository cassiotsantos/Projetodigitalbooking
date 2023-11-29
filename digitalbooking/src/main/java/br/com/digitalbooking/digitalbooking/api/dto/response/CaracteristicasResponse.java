package br.com.digitalbooking.digitalbooking.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CaracteristicasResponse {
  private UUID id;
  private String nome;
  private String icone;
}