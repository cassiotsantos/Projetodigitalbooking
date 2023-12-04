package br.com.digitalbooking.digitalbooking.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Setter
@Getter
public class AuthenticationSingInDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
}
