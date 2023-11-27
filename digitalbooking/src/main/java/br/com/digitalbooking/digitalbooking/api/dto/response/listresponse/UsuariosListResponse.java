package br.com.digitalbooking.digitalbooking.api.dto.response.listresponse;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UsuariosListResponse {
    private UUID id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
}
