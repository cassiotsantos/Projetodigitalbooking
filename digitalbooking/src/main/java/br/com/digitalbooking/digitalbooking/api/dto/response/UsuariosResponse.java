package br.com.digitalbooking.digitalbooking.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UsuariosResponse {
    private UUID id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;

    public UsuariosResponse() {

    }

    public UsuariosResponse(UUID id) {
        this.id = id;
    }

    public UsuariosResponse(String nome, String sobrenome, String email, String senha) {

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
    }
}
