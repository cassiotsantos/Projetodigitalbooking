package br.com.digitalbooking.digitalbooking.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class UsuariosRequest {

    private UUID id;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}
