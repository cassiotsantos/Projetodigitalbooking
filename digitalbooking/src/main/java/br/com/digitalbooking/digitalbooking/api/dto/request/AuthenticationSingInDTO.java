package br.com.digitalbooking.digitalbooking.api.dto.request;

import br.com.digitalbooking.digitalbooking.domain.entity.TypeFuncoesUser;
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
