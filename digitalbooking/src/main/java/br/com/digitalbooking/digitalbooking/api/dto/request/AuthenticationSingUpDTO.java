package br.com.digitalbooking.digitalbooking.api.dto.request;

import br.com.digitalbooking.digitalbooking.domain.entity.TypeFuncoesUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationSingUpDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    @NotNull
    private TypeFuncoesUser role;

}
